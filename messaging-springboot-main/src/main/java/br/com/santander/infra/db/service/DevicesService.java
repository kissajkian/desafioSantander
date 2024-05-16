package br.com.santander.infra.db.service;

import br.com.santander.infra.db.model.Devices;
import br.com.santander.infra.db.repository.DevicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesService {

    @Autowired
    private DevicesRepo devicesRepo;

    public Devices saveDetails(Devices devices){
        return devicesRepo.save(devices);
    }
}