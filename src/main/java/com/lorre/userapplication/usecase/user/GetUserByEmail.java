package com.lorre.userapplication.usecase.user;

import com.lorre.userapplication.entity.user.UserEntity;

public interface GetUserByEmail {
    UserEntity get(String email);
}
