package com.lorre.userapplication.mapper.user;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.model.user.User;
import com.lorre.userapplication.request.CreateUserForm;
import com.lorre.userapplication.resource.UserResource;

public interface UserMapper {

    User mapEntityToModel(UserEntity userEntity);
    UserEntity mapModelToEntity(User user);
    UserResource mapModelToResource(User user);
    User mapCreateUserFormToUser(CreateUserForm createUserForm);
}
