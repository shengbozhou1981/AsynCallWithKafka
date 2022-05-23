package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.dao.CredentialsDao;
import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.service.LoginService;
import com.amdocs.media.assignment.service.UserService;
import com.amdocs.media.assignment.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("authorization")
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
    public ResponseEntity<ResultVo> createUserProfile(@RequestBody UserProfile profile) {

        return userservice.createUserProfile(profile);
    }

    @PutMapping("/updateProfile")
    public void updateUserProfile(@RequestBody UserProfile profile){

        userservice.updateUserProfile(profile);
    }

    @DeleteMapping("/deleteProfile/{userId}")
    public void  deleteById(@PathVariable("userId") Integer userId){

        userservice.deleteUserProfile(userId);
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
