package com.lorre.userapplication.usecase.user;

import com.lorre.userapplication.entity.user.UserEntity;

public interface GetUserByUsername {

    UserEntity get(String username);
}
