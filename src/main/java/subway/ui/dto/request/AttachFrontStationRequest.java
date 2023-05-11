package subway.ui.dto.request;

public class AttachFrontStationRequest {
    private final String standardStation;
    private final String newStation;
    private final int distance;

    public AttachFrontStationRequest(final String standardStation, final String newStation, final int distance) {
        this.standardStation = standardStation;
        this.newStation = newStation;
        this.distance = distance;
    }

    public String getStandardStation() {
        return standardStation;
    }

    public String getNewStation() {
        return newStation;
    }

    public int getDistance() {
        return distance;
    }
}
