package com.amdocs.media.assignment.openFein;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name="userProfile-Service")
public interface UserProfileFeignClient {
    @PostMapping("/profile/saveProfile")
    public ResponseEntity<ResultVo> saveProfile(@RequestBody UserProfile userProfile);

    @GetMapping("/profile/findByUserId/{userId}")
    public UserProfile  findByUserId(@PathVariable("userId") Integer userId);

    @DeleteMapping("/profile/deleteProfile/{userId}")
    public ResponseEntity<ResultVo>  deleteById(@PathVariable("userId") Integer userId);

    @PutMapping("/profile/updateProfile")
    public String updateProfile( @RequestBody UserProfile userProfile);

    @GetMapping("/profile/findAll")
    public List<UserProfile> find();

    @DeleteMapping("/profile/deleteAll")
    public void  deleteAll();

}
