package Models;

import java.time.LocalDateTime;

public class Journey
{
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

    public Journey() {
    }

    public Journey(LocalDateTime started, LocalDateTime finished, Long durationSecs, String fromStopId, String toStopId, String chargeAmount, String companyId, String busID, String pan, JourneyStatus status) {
        this.started = started;
        this.finished = finished;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busID = busID;
        this.pan = pan;
        this.status = status;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public JourneyStatus getStatus() {
        return status;
    }

    public void setStatus(JourneyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Models.Journey{" +
                "started=" + started +
                ", Finished=" + finished +
                ", durationSecs=" + durationSecs +
                ", fromStopId='" + fromStopId + '\'' +
                ", toStopId='" + toStopId + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", companyId='" + companyId + '\'' +
                ", busID='" + busID + '\'' +
                ", pan='" + pan + '\'' +
                ", status=" + status +
                '}';
    }

    public String[] toStringArray()
    {
        return new String[]{started.toString(), finished.toString(), durationSecs.toString(), fromStopId, toStopId,
                chargeAmount, companyId, busID, pan, status.toString()};
    }
}
