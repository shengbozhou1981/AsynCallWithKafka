package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.dao.CredentialsDao;
import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.entity.UserProfile;
import com.amdocs.media.assignment.openFein.UserProfileFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CredentialsDao credentialsDao;
    @Autowired
    private UserProfileFeignClient userProfileFeignClient;

    @Override
    public User save(User user) {
        return credentialsDao.save(user);
    }

    @Override
    public User findById(Integer userId) {
        return credentialsDao.findById(userId).get();
    }

    @Override
    public List<User> findAll() {
        return credentialsDao.findAll();
    }

    @Override
    public User update(User user) {
        if (user.getId() != null) {
            User userById = credentialsDao.findById(user.getId()).get();
        }
        return credentialsDao.save(user);
    }

    @Override
    public void deleteById(Integer userId) {
        if(credentialsDao.findById(userId)!=null)
            credentialsDao.deleteById(userId);

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return credentialsDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public void createUserProfile(UserProfile profile) {
        this.userProfileFeignClient.saveProfile(profile);
    }
}
