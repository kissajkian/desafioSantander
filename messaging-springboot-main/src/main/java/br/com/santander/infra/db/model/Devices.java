package br.com.santander.infra.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "DEVICES")
public class Devices {

    @Id
    @Column(name = "DEVICE")
    private String device;

    @Column(name = "CLIENTNAME")
    private String clientname;

    @Column(name = "RELEASEDATE")
    private Date releasedate;
}
