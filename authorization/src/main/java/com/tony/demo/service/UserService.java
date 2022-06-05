package com.tony.demo.service;

import com.tony.demo.entity.User;
import com.tony.demo.entity.UserProfile;
import com.tony.demo.vo.ResultVo;
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
    public interface UserService  {
        public User save(User user);
        public User findById(Integer userId);
        public List<User> findAll();
        public User update(User user);
        public void deleteById(Integer userId);
        public User findByUsernameAndPassword(String username, String password);

        public ResponseEntity<ResultVo> createUserProfile(UserProfile profile);

        public void updateUserProfile(UserProfile profile);

        public void deleteUserProfile(Integer userId);

        public UserProfile asynUpdateUserProfile(UserProfile profile);

        public List<UserProfile> findAllProfile();

        public void deleteAllProfile();
}