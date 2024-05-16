package br.com.santander.domain.config;

import br.com.santander.domain.entity.DeviceContractedDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String boostrapAddress;

    @Value(value = "${topic.consumer-contractions-events}")
    private String topic;

    @Value(value = "${group.consumer-contractions-events}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, DeviceContractedDTO> consumer() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(DeviceContractedDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeviceContractedDTO> deviceListener() {

        ConcurrentKafkaListenerContainerFactory<String, DeviceContractedDTO> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaFactory.setConsumerFactory(consumer());

        return kafkaFactory;
    }
}