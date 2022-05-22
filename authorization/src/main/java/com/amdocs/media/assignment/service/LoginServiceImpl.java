package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.util.ResultVoUtil;
import com.amdocs.media.assignment.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{
    @Autowired
    HttpServletRequest request;
    @Autowired
    private UserService userservice;
    @Override

    public ResponseEntity<ResultVo> login(User user) {
        HttpSession session = request.getSession();
        String username= user.getUsername();
        String password= user.getPassword();
        User credentials = userservice.findByUsernameAndPassword(username, password);

        if (username !=null && password !=null){
            if (credentials.getUsername().equals(username) && credentials.getPassword().equals(password)){} {
                session.setAttribute("user",credentials);
                log.info(session.getAttribute("user").toString());
                return ResponseEntity.ok(ResultVoUtil.success("login successfully, welcome back " + username));
            }
        }
        return ResponseEntity.ok(ResultVoUtil.notAuthenticated());
    }
}
