package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.vo.ResultVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

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