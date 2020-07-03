package com.lorre.userapplication.mapper.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.mapper.user.UserMapper;
import com.lorre.userapplication.model.user.User;
import com.lorre.userapplication.request.CreateUserForm;
import com.lorre.userapplication.resource.UserResource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IUserMapper implements UserMapper {

    @Override
    public User mapEntityToModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getMobilePhone(),
                userEntity.getExternalId(),
                userEntity.getCreationDate(),
                userEntity.getLastLogin(),
                userEntity.isActive()
        );
    }

    @Override
    public UserEntity mapModelToEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getMobilePhone(),
                user.getExternalId(),
                user.getCreationDate(),
                user.getLastLogin(),
                user.isActive()
        );
    }

    @Override
    public UserResource mapModelToResource(User user) {
        return new UserResource(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getMobilePhone(),
                user.getExternalId(),
                user.getCreationDate(),
                user.getLastLogin(),
                user.isActive()
        );
    }

    @Override
    public User mapCreateUserFormToUser(CreateUserForm createUserForm) {
        return new User(
                null,
                createUserForm.getName(),
                createUserForm.getUsername(),
                createUserForm.getPassword(),
                createUserForm.getEmail(),
                createUserForm.getMobilePhone(),
                null,
                LocalDateTime.now(),
                null,
                false
        );
    }
}
