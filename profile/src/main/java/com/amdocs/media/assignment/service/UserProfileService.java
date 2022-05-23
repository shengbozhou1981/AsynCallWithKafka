package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.vo.ResultVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserProfileService {
    public boolean save(UserProfile userProfile);
    public UserProfile findByUserId(Integer userId);
    public List<UserProfile> findAll();


    public void deleteAll();

    public ResponseEntity<ResultVo> deleteByUserId(Integer userId);
}
