package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.service.UserProfileService;
import com.amdocs.media.assignment.utils.ResultVoUtil;
import com.amdocs.media.assignment.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("profile")
//@EnableRedisHttpSession
public class UserProfileController {
//    @Autowired
//    @Qualifier("UserProfileDao")
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/saveProfile")
    public ResponseEntity<ResultVo> saveProfile(@RequestBody UserProfile userProfile){
        if(userProfileService.save(userProfile))
            return ResponseEntity.ok(ResultVoUtil.success("Save profile success"));
        return ResponseEntity.ok(ResultVoUtil.success("Save failed"));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ResultVo> updateProfile( @RequestBody UserProfile userProfile){
                Boolean save = userProfileService.save(userProfile);
                if (save) return ResponseEntity.ok(ResultVoUtil.success("update profile success"));
        return ResponseEntity.ok(ResultVoUtil.success("update failed"));
    }

    @DeleteMapping("/deleteProfile/{userId}")
    public ResponseEntity<ResultVo>  deleteById(@PathVariable("userId") Integer userId){
        boolean deleteByUserId = userProfileService.deleteByUserId(userId);
        if(deleteByUserId)
            return ResponseEntity.ok(ResultVoUtil.success("delete profile success"));
        return ResponseEntity.ok(ResultVoUtil.success("delete failed"));
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
