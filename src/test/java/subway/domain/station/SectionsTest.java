package subway.domain.station;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import subway.domain.section.Section;
import subway.domain.section.Sections;

@SuppressWarnings("NonAsciiCharacters")
class SectionsTest {

    @Test
    void 최초_역_등록_테스트() {
        final Sections sections = new Sections();
        final Section section = new Section(
                new Station("from"),
                new Station("to"),
                new StationDistance(5)
        );

        sections.addInitialStations(section);

        assertThat(sections.getSections()).hasSize(1);
    }

    @Test
    void 역이_존재_하는_경우_최초_역_등록을_할_수_없다() {
        final Sections sections = new Sections();
        final Section section = new Section(
                new Station("from"),
                new Station("to"),
                new StationDistance(5)
        );

        sections.addInitialStations(section);

        assertThatThrownBy(() -> sections.addInitialStations(section))
                .isInstanceOf(IllegalStateException.class);
    }
}