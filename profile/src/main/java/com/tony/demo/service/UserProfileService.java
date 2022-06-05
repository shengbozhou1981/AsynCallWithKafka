package com.tony.demo.service;

import com.tony.demo.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    public boolean save(UserProfile userProfile);
    public UserProfile findByUserId(Integer userId);
    public List<UserProfile> findAll();


    public void deleteAll();

    public boolean deleteByUserId(Integer userId);
}
