package com.desafio.santander.app.rest;

import com.desafio.santander.app.dto.DeviceRequest;
import com.desafio.santander.domain.entity.ProducerDTO;
import com.desafio.santander.app.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("device")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<ProducerDTO> post(@RequestBody DeviceRequest request){

        try{
            logger.info("Recebendo dados de Producer. Device: ".concat(request.getDevice()).concat(" Released: ".concat(request.getReleased().toString())));
            ProducerDTO producerDTO = ProducerDTO.builder().device(UUID.randomUUID().toString()).build();
            deviceService.send(producerDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(producerDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}