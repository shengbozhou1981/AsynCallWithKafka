package com.tony.demo.exception;

import com.tony.demo.enums.UserProfileEnum;

public class UserProfileException extends RuntimeException{
    public UserProfileException(UserProfileEnum userProfileEnum) {

        super(userProfileEnum.getMsg());
    }
}
