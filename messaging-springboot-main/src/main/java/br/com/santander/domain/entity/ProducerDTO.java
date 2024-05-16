package br.com.santander.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

public class ProducerDTO {

    private String device;
    private boolean released;

    public ProducerDTO()
    {

    }

    public ProducerDTO(String device, boolean released)
    {
        this.device = device;
        this.released = released;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }
}