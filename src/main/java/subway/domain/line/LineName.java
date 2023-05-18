package subway.domain.line;

public class LineName {
    private final String name;

    public LineName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("노선 이름은 공백일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
