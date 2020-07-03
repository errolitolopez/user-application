package com.lorre.userapplication.mapper.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.mapper.userregistrationtoken.UserRegistrationTokenMapper;
import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;
import org.springframework.stereotype.Service;

@Service
public class IUserRegistrationTokenMapper implements UserRegistrationTokenMapper {

    @Override
    public UserRegistrationToken mapEntityToModel(UserRegistrationTokenEntity userRegistrationTokenEntity) {
        return new UserRegistrationToken(
                userRegistrationTokenEntity.getId(),
                userRegistrationTokenEntity.getCreationDate(),
                userRegistrationTokenEntity.isExpired(),
                userRegistrationTokenEntity.getUserId()
        );
    }

    @Override
    public UserRegistrationTokenEntity mapModelToEntity(UserRegistrationToken userRegistrationToken) {
        return new UserRegistrationTokenEntity(
                userRegistrationToken.getId(),
                userRegistrationToken.getCreationDate(),
                userRegistrationToken.isExpired(),
                userRegistrationToken.getUserId()
        );
    }
}
