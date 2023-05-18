package subway.domain.path;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;
import subway.domain.line.Line;
import subway.domain.line.LineColor;
import subway.domain.line.LineName;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.domain.station.Station;
import subway.domain.station.StationDistance;

@SuppressWarnings("NonAsciiCharacters")
class SubwayMapTest {

    @Test
    void 여러_노선으로_지하철_전체_지도_생성_테스트() {
        //given
        final Sections sectionsA = new Sections(List.of(
                new Section(new Station("강남"), new Station("역삼"), new StationDistance(3)),
                new Section(new Station("역삼"), new Station("선릉"), new StationDistance(2))
        ));
        final Line lineA = new Line(sectionsA, new LineName("1호선"), new LineColor("파랑색"));

        final Sections sectionsB = new Sections(List.of(
                new Section(new Station("강남구청"), new Station("선정릉"), new StationDistance(5)),
                new Section(new Station("선정릉"), new Station("선릉"), new StationDistance(8))
        ));
        final Line lineB = new Line(sectionsB, new LineName("2호선"), new LineColor("청록색"));

        //when
        final SubwayMap subwayMap = SubwayMap.from(List.of(lineA, lineB));

        final WeightedMultigraph<Station, DefaultWeightedEdge> stationsGraph = subwayMap.getStationsGraph();
        assertThat(stationsGraph.containsEdge(new Station("강남"), new Station("역삼"))).isTrue();
        assertThat(stationsGraph.containsEdge(new Station("역삼"), new Station("선릉"))).isTrue();
        assertThat(stationsGraph.vertexSet()).hasSize(5);
        assertThat(stationsGraph.edgeSet()).hasSize(4);
    }
}
