package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.openFein.UserProfileFeignClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 监听Kafka Topic,从里面取数据
 **/
@Service
@Slf4j
public class KafkaConsumer {


    @Autowired
    private UserProfileFeignClient userProfileFeignClient;
    @Autowired
    private UpdateAndDeleteProfileService service;
    @Autowired
//    private UserProfile userProfile;

    private static Gson gson = new GsonBuilder().create();
    /**
     * here topics should be the same as in controller
     * @param record here is the message
     */
    @KafkaListener(topics = "test")
    public void receiveMessageFromKafka(ConsumerRecord<?, ?> record, Acknowledgment ack) {
/*        log.info("receive new message :{}", String.valueOf(record.value()));
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent())  {
//            service.updateProfile(userProfile);
        }
        ack.acknowledge();*/
        System.out.println(record.topic());
        System.out.println(record.offset());
        System.out.println(record.value());
    }




}