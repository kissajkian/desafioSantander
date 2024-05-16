package br.com.santander.app.service;

import br.com.santander.app.rest.ProducerController;
import br.com.santander.domain.entity.DeviceContractedDTO;
import br.com.santander.domain.entity.ProducerDTO;
import br.com.santander.domain.entity.RetryDTO;
import br.com.santander.infra.db.model.Devices;
import br.com.santander.infra.db.service.DevicesService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class EventConsumerService {

  private static final Logger logger = LoggerFactory.getLogger(EventConsumerService.class);

  @Autowired
  private ProducerController producerController;

  @Autowired
  private DevicesService devicesService;

  @KafkaListener(topics = "consumer.contractions.events", groupId = "group-1", containerFactory = "deviceListener")
  public void receiveMessage(DeviceContractedDTO message) {
    logger.info("Consumindo Topic Kafka: ".concat(message.toString()));
    HttpStatusCode codeReturn = producerController.callProducerApi(message);

    if(HttpStatus.CREATED.equals(codeReturn)){
      try {
        logger.info("Persistindo dados na tabela device. Device: ".concat(message.getDevice()).concat(" ReleaseDate: ".concat(message.getReleaseDate()).concat(" Clientname: ".concat(message.getClientName()))));
        devicesService.saveDetails(getDevices(message));
      } catch (Exception e){
        // @TODO: se houver erro, enviar para o topic dlq
      }

    } else {
      // @TODO: enviar para o topic retry
        // @TODO: se houver erro, enviar para o topic dlq
    }
  }

  private Devices getDevices(DeviceContractedDTO dto){
    Devices devices = new Devices();
      devices.setDevice(dto.getDevice());
      devices.setReleasedate(Date.valueOf(dto.getReleaseDate()));
      devices.setClientname(dto.getClientName());
    return devices;
  }

  private RetryDTO retryConfig(DeviceContractedDTO device,Exception e){
    RetryDTO retry = new RetryDTO();
      retry.setDevice(device.getDevice());
      retry.setErrorEnum(e.getCause().getMessage());
      retry.setErrorException(e.getMessage());
    return retry;
  }
}