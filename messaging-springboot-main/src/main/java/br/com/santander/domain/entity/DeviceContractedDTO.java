package br.com.santander.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceContractedDTO {

    private String device;
    private String releaseDate;
    private String clientName;

    public DeviceContractedDTO()
    {

    }

    public DeviceContractedDTO(String device, String releaseDate, String clientName)
    {
        this.device = device;
        this.releaseDate = releaseDate;
        this.clientName = clientName;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}