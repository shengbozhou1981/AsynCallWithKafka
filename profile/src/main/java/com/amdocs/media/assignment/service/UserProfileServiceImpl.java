package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.dao.UserProfileDao;
import com.amdocs.media.assignment.entity.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
//@CacheConfig(cacheNames = "tony")
public class UserProfileServiceImpl implements UserProfileService{
    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public boolean save(UserProfile userProfile) {
        UserProfile currentUserProfile = userProfileDao.findByUserId(userProfile.getUserId());

        if (currentUserProfile != null && currentUserProfile.getUserId() == userProfile.getUserId()) {
            userProfile.setId(currentUserProfile.getId());
            userProfileDao.save(userProfile);
            return true;
        }
        userProfileDao.save(userProfile);
        return true;
    }

    @Override
//    @Cacheable
    public UserProfile findByUserId(Integer userId) {
        return userProfileDao.findByUserId(userId);
    }

    @Override
//    @Cacheable
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }



    @Override
    public void deleteAll() {
        userProfileDao.deleteAllInBatch();
    }

    @Override
    public void deleteByUserId(Integer userId) {
        userProfileDao.deleteByUserId(userId);
    }
}
