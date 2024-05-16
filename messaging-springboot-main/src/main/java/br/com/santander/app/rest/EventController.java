package br.com.santander.app.rest;

import br.com.santander.app.service.EventProducerService;
import br.com.santander.domain.entity.DeviceContractedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventProducerService service;

    @GetMapping("/contraction")
    public ResponseEntity<String> hello(@RequestBody DeviceContractedDTO device) {
        logger.info("Recebendo dados de Contraction. Device: ".concat(device.getDevice())
                .concat(" ReleaseDate: ".concat(device.getReleaseDate())
                .concat(" ClientName: ".concat(device.getClientName()))));

        service.sendMessage(device);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}