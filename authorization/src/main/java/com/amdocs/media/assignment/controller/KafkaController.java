package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.util.ResultVoUtil;
import com.amdocs.media.assignment.vo.ResultVo;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class KafkaController {
    @Resource
    KafkaTemplate<String,Object> kafkaTemplate;

    @PostMapping("/postMessage")
    public ResponseEntity<ResultVo> sendMessage(){
        User user = new User();
        user.setId(12);
        user.setUsername("test");
        user.setPassword("1234");
        kafkaTemplate.send("test", user.toString());
        return ResponseEntity.ok(ResultVoUtil.success("test success"));
    }
}