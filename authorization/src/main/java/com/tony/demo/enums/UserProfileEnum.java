package com.tony.demo.enums;

import lombok.Getter;

@Getter
public enum UserProfileEnum {
    PROFILE_ALREADY_EXIST(0,"profile already exit, will update"),
    PROFILE_NOT_EXIST(1,"profile not exist"),
    PROFILE_DUPLICATE(2,"profile duplicate"),
    PROFILE_UPDATE_DONE(3,"profile update done"),
    PROFILE_CREATE_FAILED(4,"profile create failed"),
    PROFILE_UPDATE_FAILED(5,"profile update failed");

    UserProfileEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
}

