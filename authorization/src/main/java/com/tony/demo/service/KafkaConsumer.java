package com.tony.demo.service;

import com.alibaba.fastjson.JSON;
import com.tony.demo.entity.UserProfile;
import com.tony.demo.openFein.UserProfileFeignClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
public class KafkaConsumer {
    @Autowired
    private UserProfileFeignClient userProfileFeignClient;
    @Autowired
    private UpdateAndDeleteProfileService service;
    @Autowired

    private static Gson gson = new GsonBuilder().create();
    /**
     * here topics should be the same as in controller
     * @param record here is the message
     */
    @KafkaListener(topics = "test")
    public void receiveMessageFromKafka(ConsumerRecord<?, ?> record, Acknowledgment ack) {

        System.out.println("topic: "+record.topic() + " offset: " +record.offset() + " message: " + record.value());
        log.info("receive new message :{}", String.valueOf(record.value()));
        Object message = record.value();
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

        if(kafkaMessage.isPresent() &&  pattern.matcher(message.toString()).matches() ){
            this.service.deleteProfile(Integer.parseInt(message.toString()));
        } else {
            UserProfile updateUserProfile = (UserProfile) JSON.parseObject(record.value().toString(), UserProfile.class);
            this.service.updateProfile(updateUserProfile);
        }
        ack.acknowledge();
    }
}