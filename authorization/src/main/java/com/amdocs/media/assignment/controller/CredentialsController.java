package com.amdocs.media.assignment.controller;

import com.amdocs.media.assignment.dao.CredentialsDao;
import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CredentialsController {

    @Autowired
    private UserService userservice;
    @Autowired
    HttpServletRequest request;
//    @Autowired
//    HttpServletResponse response;

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

    @RequestMapping("/findById/{userId}")
    public User findById(@PathVariable("userId") Integer userId){

        return (User) userservice.findById(userId);
    }

    @RequestMapping("/login")
    public String  login(@RequestBody User user) throws Exception {
        HttpSession session = request.getSession();

        String str = "";
        String username= user.getUsername();
        String password= user.getPassword();
        User credentials = userservice.findByUsernameAndPassword(username, password);

        if (username !=null && password !=null){
            if (credentials.getUsername().equals(username) && credentials.getPassword().equals(password)){} {
                session.setAttribute("user",credentials);
                log.info(session.getAttribute("user").toString());
                return "login successfully, welcome back " + username;
            }
        }
        return "not login yet, please login first";

/*
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("password", password);
        if(userService.login(map)) {
            Cookie c1 = new Cookie("loginName", username);
            c1.setPath("/");
            response.addCookie(c1);
            session.setAttribute("user",WebMvcConfg.SESSION_KEY);
            str = "redirect:/main.html";
        }else {
            str = "redirect:/index.html";
        }
        return str;*/
    }

    @RequestMapping("/logout")
    public String logout() {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "logout successfully";
    }

}
