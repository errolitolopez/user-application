package com.lorre.userapplication.usecase.user;

import com.lorre.userapplication.entity.user.UserEntity;

import java.util.List;

public interface GetUsers {
    List<UserEntity> get();
}
