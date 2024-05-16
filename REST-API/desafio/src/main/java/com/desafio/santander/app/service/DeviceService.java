package com.desafio.santander.app.service;

import com.desafio.santander.domain.entity.ProducerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    private final String topic;
    private final KafkaTemplate<String, ProducerDTO> kafkaTemplate;

    public DeviceService(@Value("${topic.producer-devices-events}") String topic, KafkaTemplate<String, ProducerDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ProducerDTO producerDTO){
        logger.info("Enviando Producer para o Topic Kafka.");
        kafkaTemplate.send(topic, producerDTO);
    }
}