package com.amdocs.media.assignment.entity;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    /**
     * here topics should be the same as in controller
     * @param record here is the message
     */
    @KafkaListener(topics = "test")
    public void userListener(ConsumerRecord<?,?> record){
        System.out.println(record.topic());
        System.out.println(record.offset());
        System.out.println(record.value());
    }
}