package com.tony.demo.service;

import com.tony.demo.entity.User;
import com.tony.demo.vo.ResultVo;
import org.springframework.http.ResponseEntity;

/**
     * <p>
     *
     * </p>
     *
     * @author Tony Zhou
     * @since 2022-05-22
     */
    public interface LoginService {
        public ResponseEntity<ResultVo> login(User user);
}