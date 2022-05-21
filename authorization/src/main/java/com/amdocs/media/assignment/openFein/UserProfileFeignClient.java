package com.amdocs.media.assignment.openFein;

import com.amdocs.media.assignment.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("userProfile-service")
public interface UserProfileFeignClient {
    @PostMapping("/profile/saveProfile")
    public String saveProfile(@RequestBody UserProfile userProfile);

    @GetMapping("/profile/findByUserId/{userId}")
    public UserProfile  findByUserId(@PathVariable("userId") Integer userId);

    @DeleteMapping("/profile/deleteProfile/{userId}")
    public void  deleteById(@PathVariable("userId") Integer userId);

//    public List<UserProfile> findAll();
//
//
//    public void deleteAll();


}
