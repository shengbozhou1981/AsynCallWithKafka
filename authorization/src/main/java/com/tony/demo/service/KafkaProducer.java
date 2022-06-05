package com.tony.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class KafkaProducer implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendToKafkaNormalMessage(String topic,String  message) {

        log.info("kafka sending message='{}' to topic='{}'", message, topic);
        //create the message
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
        // use template to send message
        kafkaTemplate.send(record)
                .addCallback(new SuccessCallback<SendResult<String, String>>() { //add call back for asyn
                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        if (null != result.getRecordMetadata()) {
                            System.out.println("message send success, offset:" + result.getRecordMetadata().offset());
                            return;
                        }
                        System.out.println("message send success");
                    }
                }, new FailureCallback() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("message send failure:" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void run(String... args) throws Exception {

    }
}