//package com.amdocs.media.assignment.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
//
///**
// * @Author: Tony Zhou
// * @Date: 2022/05/21
// * <p>
// * interceptor: authenticate whether user login
// */
//@Slf4j
//public class UserLoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("enter request interceptor");
//        try {
//            HttpSession session = request.getSession();
//            //check whether the session has user attribute, everytime login, will set the user into session
//            Object user = session.getAttribute("user");
//            if (user == null) {
//                response.reset();
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json;charset=UTF-8");
//                PrintWriter writer =response.getWriter();
//                writer.write("Not authenticate yet, please login first");
//                writer.flush();
//                writer.close();
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info("already login, welcome back");
//        return true;
//    }
//}