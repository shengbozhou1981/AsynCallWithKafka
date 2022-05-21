package com.amdocs.media.assignment.interceptor;

import com.amdocs.media.assignment.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.io.PrintWriter;

import static com.sun.xml.internal.ws.api.message.Packet.State.ServerResponse;

/**
 * @Author: Tony Zhou
 * @Date: 2022/505/21
 * <p>
 * interceptor: authenticate whether user login
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了拦截器的preHandle方法");
        try {
            HttpSession session = request.getSession();
            //统一拦截（查询当前session是否存在user）(这里user会在每次登录成功后，写入session)
            Object user = session.getAttribute("user");
            if (user == null) {
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer =response.getWriter();
                writer.write("Not authenticate yet, please login first");
                writer.flush();
                writer.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("already login, welcome back");
        return true;
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作

/*        //http的header中获得token
        String token = request.getHeader(JWTUtils.USER_LOGIN_TOKEN);
        //token不存在
        if (token == null || token.equals("")) throw new ApiException("请先登录");
        //验证token
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals(""))
            throw new ApiException(ResultInfo.unauthorized("token验证失败"));
        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)){
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN,newToken);
        }*/
    }
}