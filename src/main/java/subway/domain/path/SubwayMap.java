package subway.domain.path;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.line.Line;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.domain.station.Station;

public class SubwayMap {
    private final WeightedMultigraph<Station, DefaultWeightedEdge> stationsGraph;

    private SubwayMap(final WeightedMultigraph<Station, DefaultWeightedEdge> stationsGraph) {
        this.stationsGraph = stationsGraph;
    }

    public static SubwayMap from(final List<Line> allLines) {
        final WeightedMultigraph<Station, DefaultWeightedEdge> stationsGraph =
                new WeightedMultigraph<>(DefaultWeightedEdge.class);

        for (final Line allLine : allLines) {
            final Sections sections = allLine.getSections();
            addSection(stationsGraph, sections);
        }

        return new SubwayMap(stationsGraph);
    }

    private static void addSection(
            final WeightedMultigraph<Station, DefaultWeightedEdge> graph,
            final Sections sections
    ) {
        for (final Section section : sections.getSections()) {
            final Station firstStation = section.getFirstStation();
            final Station secondStation = section.getSecondStation();
            graph.addVertex(firstStation);
            graph.addVertex(secondStation);

            graph.setEdgeWeight(graph.addEdge(firstStation, secondStation), section.getDistance().getDistance());
        }
    }

    public WeightedMultigraph<Station, DefaultWeightedEdge> getStationsGraph() {
        return stationsGraph;
    }
}
