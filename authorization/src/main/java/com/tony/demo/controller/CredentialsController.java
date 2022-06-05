package com.tony.demo.controller;

import com.tony.demo.entity.User;
import com.tony.demo.entity.UserProfile;
import com.tony.demo.service.LoginService;
import com.tony.demo.service.UserService;
import com.tony.demo.util.ResultVoUtil;
import com.tony.demo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("assignment")
@Slf4j
@EnableRedisHttpSession
public class CredentialsController {

    @Autowired
    private UserService userservice;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LoginService loginservice;

    @RequestMapping("/initialize")
    public String initialize(){
        User credential = new User();
        credential.setUsername("Tom");
        credential.setPassword("Tom123456");
        userservice.save(credential);

        credential = new User();
        credential.setUsername("Michael");
        credential.setPassword("Michael23456");
        userservice.save(credential);

        return "database initialization done";
    }

    @RequestMapping("/findAll")
    public List<User> find(){

        return (List<User>) userservice.findAll();
    }
    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable("id") Integer id){

        userservice.deleteById(id);
    }

    @RequestMapping("/findById/{userId}")
    public User findById(@PathVariable("userId") Integer userId){

        return (User) userservice.findById(userId);
    }

    @RequestMapping("/login")
    public ResponseEntity<ResultVo> login(@RequestBody User user) throws Exception {
        return this.loginservice.login(user);
    }

    @RequestMapping("/logout")
    public String logout() {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "logout successfully";
    }
    @PostMapping("/createProfile")
    public ResponseEntity<ResultVo> createUserProfile(@RequestBody UserProfile profile, BindingResult bindingResult) {

        return userservice.createUserProfile(profile);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ResultVo> updateUserProfile(@RequestBody UserProfile profile, HttpServletRequest request, HttpServletResponse response){
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        if (request.getSession().getAttribute("loginId").equals(profile.getUserId())){
            userservice.updateUserProfile(profile);
            return ResponseEntity.ok(ResultVoUtil.success("update profile success"));
        }
        return ResponseEntity.ok(ResultVoUtil.fail("Not allowed to update profile which are not yours"));
    }

    @DeleteMapping("/deleteProfile/{userId}")
    public ResponseEntity<ResultVo>  deleteById(@PathVariable("userId") Integer userId, HttpServletRequest request, HttpServletResponse response){
        Integer loginId = (Integer) request.getSession().getAttribute("loginId");
        if (request.getSession().getAttribute("loginId").equals(userId)) {
            userservice.deleteUserProfile(userId);
            return ResponseEntity.ok(ResultVoUtil.success("delete profile success"));
        }
        return ResponseEntity.ok(ResultVoUtil.fail("Not allowed to delete profile which are not yours"));
    }

    @GetMapping("/findAllProfile")
    public List<UserProfile> findAllProfile(){
        return  userservice.findAllProfile();
    }

    @DeleteMapping("/deleteAllProfile")
    public void  deleteAll(){
        userservice.deleteAllProfile();
    }
}
