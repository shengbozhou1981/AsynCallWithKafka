package com.amdocs.media.assignment.dao;

import com.amdocs.media.assignment.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
     * @description UserProfile Dao layer
     * @author Tony Zhou
     * @date 2022-05-20
     */
    @Repository
    @Component
    public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

        public UserProfile findByUserId(Integer userId);

        public void deleteAllInBatch();

        public void deleteByUserId(Integer userId);
}