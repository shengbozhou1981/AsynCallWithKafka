package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.User;
import com.amdocs.media.assignment.entity.UserProfile;

import java.util.List;

/**
     * <p>
     *
     * </p>
     *
     * @author Tony Zhou
     * @since 2021-08-24
     */
    public interface UserService  {
        public User save(User user);
        public User findById(Integer userId);
        public List<User> findAll();
        public User update(User user);
        public void deleteById(Integer userId);
        public User findByUsernameAndPassword(String username, String password);

        public void createUserProfile(UserProfile profile);

        public void updateUserProfile(UserProfile profile);

        public void deleteUserProfile(Integer userId);

        public UserProfile asynUpdateUserProfile(UserProfile profile);

        public List<UserProfile> findAllProfile();

        public void deleteAllProfile();
}