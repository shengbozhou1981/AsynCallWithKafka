package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.service.UserProfileService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/saveProfile")
    public String saveProfile(@RequestBody UserProfile userProfile){
//        UserProfile userProfile = new UserProfile();
//        userProfile.setAddress("100 Yong street");
//        userProfile.setPhoneNumber("4383456890");
//        userProfile.setUserId(1);
        userProfileService.save(userProfile);
        return "ok";
    }

    @PutMapping("/updateProfile")
    public String updateProfile( @RequestBody UserProfile userProfile){

//        HttpSession session = request.getSession();
//        User currentUser = (User) request.getSession().getAttribute("user");
////        if(currentUser !=null){
            UserProfile currentUserProfile = userProfileService.findByUserId(userProfile.getUserId());
            if (currentUserProfile != null) {
                userProfile.setId(currentUserProfile.getId());

                UserProfile save = userProfileService.save(userProfile);
                if (save !=null) return "save success";
            }
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
