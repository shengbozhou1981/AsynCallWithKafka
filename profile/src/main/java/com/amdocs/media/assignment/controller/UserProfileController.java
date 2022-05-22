package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("profile")
@EnableRedisHttpSession
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/saveProfile")
    public String saveProfile(@RequestBody UserProfile userProfile){
        userProfileService.save(userProfile);
        return "ok";
    }

    @PutMapping("/updateProfile")
    public String updateProfile( @RequestBody UserProfile userProfile){
                Boolean save = userProfileService.save(userProfile);
                if (save) return "save success";
                return "save failed";
    }

    @DeleteMapping("/deleteProfile/{userId}")
    public void  deleteById(@PathVariable("userId") Integer userId){
         userProfileService.deleteByUserId(userId);
    }

    @GetMapping("/findByUserId/{userId}")
    public UserProfile  findByUserId(@PathVariable("userId") Integer userId){
        return userProfileService.findByUserId(userId);
    }

    @GetMapping("/findAll")
    public List<UserProfile> find(){

        return  userProfileService.findAll();
    }

    @DeleteMapping("/deleteAll")
    public void  deleteAll(){
        userProfileService.deleteAll();
    }
}
