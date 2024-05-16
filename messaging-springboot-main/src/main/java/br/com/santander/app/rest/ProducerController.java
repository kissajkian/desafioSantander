package br.com.santander.app.rest;

import br.com.santander.domain.entity.DeviceContractedDTO;
import br.com.santander.domain.entity.ProducerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProducerController {

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    public HttpStatusCode callProducerApi(DeviceContractedDTO device){
        logger.info("Chamando API Device.");

        ProducerDTO producer = new ProducerDTO();
            producer.setDevice(device.getDevice());
            producer.setReleased(true);

        RestTemplate restTemplate = new RestTemplate();
           ResponseEntity<ProducerDTO> resp = restTemplate.postForEntity("http://localhost:8080/device", producer, ProducerDTO.class);

        return resp.getStatusCode();
    }
}