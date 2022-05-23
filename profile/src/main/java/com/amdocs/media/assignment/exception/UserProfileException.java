package com.amdocs.media.assignment.exception;

import com.amdocs.media.assignment.enums.UserProfileEnum;

public class UserProfileException extends RuntimeException{
    public UserProfileException(UserProfileEnum userProfileEnum) {

        super(userProfileEnum.getMsg());
    }
}
