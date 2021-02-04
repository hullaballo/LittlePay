package Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Trip {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("DateTimeUTC")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;
    @JsonProperty("Models.TapType")
    private TapType tapType;
    @JsonProperty("Models.StopId")
    private StopId stopId;
    @JsonProperty("CompanyId")
    private String companyId;
    @JsonProperty("BusID")
    private String busId;
    @JsonProperty("PAN")
    private String PAN;

    public Trip() {
    }

    public Trip(int id, LocalDateTime dateTime, TapType tapType, StopId stopId, String companyId, String busId, String PAN) {
        this.id = id;
        this.dateTime = dateTime;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.PAN = PAN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TapType getTapType() {
        return tapType;
    }

    public void setTapType(TapType tapType) {
        this.tapType = tapType;
    }

    public StopId getStopId() {
        return stopId;
    }

    public void setStopId(StopId stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    @Override
    public String toString() {
        return "Models.Journey{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", tapType=" + tapType +
                ", stopId='" + stopId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", busId='" + busId + '\'' +
                ", PAN='" + PAN + '\'' +
                '}';
    }


}
