package com.lorre.userapplication.usecase.user;

import com.lorre.userapplication.entity.user.UserEntity;

public interface CreateUser {
    Long create(UserEntity userEntity);
}
