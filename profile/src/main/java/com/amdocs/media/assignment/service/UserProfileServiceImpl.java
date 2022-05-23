package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.dao.UserProfileDao;
import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.enums.UserProfileEnum;
import com.amdocs.media.assignment.exception.UserProfileException;
import com.amdocs.media.assignment.utils.ResultVoUtil;
import com.amdocs.media.assignment.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Slf4j
@Transactional
//@CacheConfig(cacheNames = "tony")
public class UserProfileServiceImpl implements UserProfileService{
    @Resource
    private UserProfileDao userProfileDao;
    @Autowired
    HttpServletRequest request;

    @Override
    public boolean save(UserProfile userProfile) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        UserProfile currentUserProfile = userProfileDao.findByUserId(userProfile.getUserId());
        if(currentUserProfile==null) {
            userProfileDao.save(userProfile);
            return true;
        }else if (currentUserProfile != null && currentUserProfile.getUserId() == userProfile.getUserId()) {
            userProfile.setId(currentUserProfile.getId());
            userProfileDao.save(userProfile);
            return true;
        }
        return false;
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
    public ResponseEntity<ResultVo> deleteByUserId(Integer userId) {
        UserProfile userProfile = userProfileDao.findByUserId(userId);
//        boolean existsById = userProfileDao.existsById(userId);
        if(userProfile!=null) {
            userProfileDao.deleteByUserId(userId);
            return ResponseEntity.ok(ResultVoUtil.success("Delete profile success"));
        }
        return ResponseEntity.ok(ResultVoUtil.success(UserProfileEnum.PROFILE_NOT_EXIST));
    }
}
