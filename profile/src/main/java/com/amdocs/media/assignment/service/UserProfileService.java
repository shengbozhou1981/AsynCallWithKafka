package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    public UserProfile save(UserProfile userProfile);
    public UserProfile findByUserId(Integer userId);
    public List<UserProfile> findAll();


    public void deleteAll();

    public void deleteByUserId(Integer userId);
}
