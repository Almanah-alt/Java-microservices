package kz.iitu.jd3.notifyservice;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinishedDevices {

    private Long id;
    private LocalDate date;
    private Boolean isTaken;
    private Long deviceId;
    private String year;
    private String manufacturer;
    private String explanation;
    private String deviceOwnerPhone;
    private String deviceOwnerName;
    private String name;
    private Status status;
    private String phone;

    @Override
    public String toString() {
        return "FinishedDevices{" +
                "id=" + id +
                ", date=" + date +
                ", isTaken=" + isTaken +
                ", deviceId=" + deviceId +
                ", year='" + year + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", deviceOwnerPhone='" + deviceOwnerPhone + '\'' +
                ", deviceOwnerName='" + deviceOwnerName + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }
}
