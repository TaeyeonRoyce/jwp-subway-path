package subway.application.path.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.application.path.usecase.FindShortestPathUseCase;
import subway.domain.fare.DistanceProportionFarePolicy;
import subway.domain.fare.Fare;
import subway.domain.fare.FarePolicy;
import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.path.SubwayMap;
import subway.domain.path.SubwayPath;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.NoDataFoundException;
import subway.ui.dto.response.PathResponse;
import subway.ui.dto.response.StationResponse;

@Transactional(readOnly = true)
@Service
public class FindShortestPathService implements FindShortestPathUseCase {
    private final FarePolicy farePolicy = new DistanceProportionFarePolicy();
    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public FindShortestPathService(final LineRepository lineRepository, final StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public PathResponse findShortestPath(final Long startStationId, final Long arrivalStationId) {
        final Station startStation = stationRepository.findById(startStationId)
                .orElseThrow(NoDataFoundException::new);
        final Station endStation = stationRepository.findById(arrivalStationId)
                .orElseThrow(NoDataFoundException::new);
        final List<Line> allLines = lineRepository.findAll();

        final SubwayMap subwayMap = SubwayMap.from(allLines);
        final SubwayPath shortestPath = subwayMap.findShortestPath(startStation, endStation);
        final int pathDistance = shortestPath.getDistance();

        return toPathResponse(shortestPath, farePolicy.calculate(pathDistance));
    }

    private PathResponse toPathResponse(final SubwayPath subwayPath, final Fare fare) {
        final List<StationResponse> stationResponses = subwayPath.getStations().stream()
                .map(StationResponse::new)
                .collect(Collectors.toList());

        return new PathResponse(
                stationResponses,
                subwayPath.getDistance(),
                fare.getFare()
        );
    }
}
