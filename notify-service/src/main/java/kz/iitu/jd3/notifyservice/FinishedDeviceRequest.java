package kz.iitu.jd3.notifyservice;

import lombok.Data;

@Data
public class FinishedDeviceRequest {

    private String userId;
    private FinishedDevices finishedDevices;

    public FinishedDeviceRequest() {
    }

    public FinishedDeviceRequest(String userId, FinishedDevices finishedDevices) {
        this.userId = userId;
        this.finishedDevices = finishedDevices;
    }
}
