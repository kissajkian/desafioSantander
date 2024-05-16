package br.com.santander.app.service;

import br.com.santander.domain.entity.DeviceContractedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducerService {

  private static final Logger logger = LoggerFactory.getLogger(EventProducerService.class);

  private final String topic;
  private final KafkaTemplate<String, DeviceContractedDTO> kafkaTemplate;

  public EventProducerService(@Value("${topic.consumer-contractions-events}") String topic, KafkaTemplate<String, DeviceContractedDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(DeviceContractedDTO device) {
      logger.info("Enviando para o Topic Kafka.");
      kafkaTemplate.send(topic, device);
  }
}