package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.openFein.UserProfileFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableAsync
public class UpdateAndDeleteProfileService {

    @Autowired
    private UserProfileFeignClient userProfileFeignClient;


    @Async("asyncServiceExecutor")
//    @CachePut
    public void updateProfile(UserProfile userProfile) {
        this.userProfileFeignClient.updateProfile(userProfile);
        }

    @Async("asyncServiceExecutor")
//    @CacheEvict
    public void deleteProfile(Integer userId) {
        this.userProfileFeignClient.deleteById(userId);
    }
    }

