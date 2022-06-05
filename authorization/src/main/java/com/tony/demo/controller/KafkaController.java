package com.tony.demo.controller;

import com.alibaba.fastjson.JSON;
import com.tony.demo.entity.User;
import com.tony.demo.entity.UserProfile;
import com.tony.demo.service.KafkaProducer;
import com.tony.demo.service.UserService;
import com.tony.demo.util.ResultVoUtil;
import com.tony.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("kafka")
@EnableRedisHttpSession
public class KafkaController {
    @Resource
    KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private UserService userservice;
    @Autowired
    private KafkaProducer kafkaProducer;


    @PostMapping("/postMessage")
    public ResponseEntity<ResultVo> sendMessage() {
        User user = new User();
        user.setId(12);
        user.setUsername("test");
        user.setPassword("1234");
        kafkaTemplate.send("test", user.toString());
        return ResponseEntity.ok(ResultVoUtil.success("test success"));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ResultVo> updateProfile(@RequestBody UserProfile profile, HttpServletRequest request, HttpServletResponse response) {
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");

        if (request.getSession().getAttribute("loginId").equals(profile.getUserId()))  {
            UserProfile updateUserProfile = userservice.asynUpdateUserProfile(profile);
            this.kafkaProducer.sendToKafkaNormalMessage("test", JSON.toJSONString(updateUserProfile));
            return ResponseEntity.ok(ResultVoUtil.success("update profile success"));
        }
        return ResponseEntity.ok(ResultVoUtil.fail("Not allowed to update profile which are not yours"));
    }

    @DeleteMapping("/deleteProfileByUserId/{userId}")
    public ResponseEntity<ResultVo> deleteProfileByUserId(@PathVariable("userId") Integer userId, HttpServletRequest request, HttpServletResponse response) {
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        if (request.getSession().getAttribute("loginId").equals(userId))  {
            this.kafkaProducer.sendToKafkaNormalMessage("test", userId.toString());
            return ResponseEntity.ok(ResultVoUtil.success("delete profile success"));
        }

        return ResponseEntity.ok(ResultVoUtil.fail("Not allowed to delete profile which are not yours"));
    }
}