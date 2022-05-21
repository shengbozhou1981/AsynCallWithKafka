package com.amdocs.media.assignment.service;

import com.amdocs.media.assignment.entity.User;

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

    }