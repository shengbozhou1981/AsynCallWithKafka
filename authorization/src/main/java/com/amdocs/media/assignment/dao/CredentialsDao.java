package com.amdocs.media.assignment.dao;

import com.amdocs.media.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description Credentials Dao level
 * @author Tony Zhou
 * @date 2022-05-20
 */
@Repository
public interface CredentialsDao extends JpaRepository<User, Integer> {
    public User findByUsernameAndPassword(String username, String password);
}

