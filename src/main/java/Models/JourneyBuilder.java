package Models;

import java.time.LocalDateTime;

public class JourneyBuilder {
    private LocalDateTime started;
    private LocalDateTime finished;
    private Long durationSecs;
    private String fromStopId;
    private String toStopId;
    private String chargeAmount;
    private String companyId;
    private String busID;
    private String pan;
    private JourneyStatus status;

    public JourneyBuilder setStarted(LocalDateTime started) {
        this.started = started;
        return this;
    }

    public JourneyBuilder setFinished(LocalDateTime finished) {
        this.finished = finished;
        return this;
    }

    public JourneyBuilder setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
        return this;
    }

    public JourneyBuilder setFromStopId(StopId fromStopId) {
        this.fromStopId = fromStopId.name();
        return this;
    }

    public JourneyBuilder setToStopId(StopId toStopId) {
        this.toStopId = toStopId.name();
        return this;
    }

    public JourneyBuilder setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    public JourneyBuilder setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public JourneyBuilder setBusID(String busID) {
        this.busID = busID;
        return this;
    }

    public JourneyBuilder setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public JourneyBuilder setStatus(JourneyStatus status) {
        this.status = status;
        return this;
    }

    public Journey createJourney() {
        return new Journey(started, finished, durationSecs, fromStopId, toStopId, chargeAmount, companyId, busID, pan, status);
    }
}
