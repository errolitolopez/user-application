package com.lorre.userapplication.gateway.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.exception.userregistrationtoken.UserRegistrationTokenExpiredException;
import com.lorre.userapplication.gateway.userregistrationtoken.UserRegistrationTokenGateway;
import com.lorre.userapplication.mapper.userregistrationtoken.UserRegistrationTokenMapper;
import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;
import com.lorre.userapplication.usecase.userregistrationtoken.CreateUserRegistrationToken;
import com.lorre.userapplication.usecase.userregistrationtoken.GetUserRegistrationTokenById;
import com.lorre.userapplication.usecase.userregistrationtoken.GetUserRegistrationTokensByUserId;
import com.lorre.userapplication.usecase.userregistrationtoken.UpdateUserRegistrationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IUserRegistrationTokenGateway implements UserRegistrationTokenGateway {

    private final CreateUserRegistrationToken createUserRegistrationToken;
    private final UserRegistrationTokenMapper userRegistrationTokenMapper;
    private final GetUserRegistrationTokenById getUserRegistrationTokenById;
    private final UpdateUserRegistrationToken updateUserRegistrationToken;
    private final GetUserRegistrationTokensByUserId getUserRegistrationTokensByUserId;

    @Override
    public UUID createUserRegistrationToken(UserRegistrationToken userRegistrationToken) {
        UserRegistrationTokenEntity userRegistrationTokenEntity =
                userRegistrationTokenMapper.mapModelToEntity(userRegistrationToken);
        return createUserRegistrationToken.create(userRegistrationTokenEntity);
    }

    @Override
    public UserRegistrationToken getUserRegistrationTokenById(UUID userRegistrationTokenId) {
        UserRegistrationTokenEntity userRegistrationTokenEntity =
                getUserRegistrationTokenById.get(userRegistrationTokenId);

        if (userRegistrationTokenEntity.isExpired()) {
            throw new UserRegistrationTokenExpiredException("Registration token expired.");
        }

        LocalDateTime creationDate = userRegistrationTokenEntity.getCreationDate();
        if (creationDate.plusMinutes(5).isBefore(LocalDateTime.now())) {
            throw new UserRegistrationTokenExpiredException("Registration token expired.");
        }
        return userRegistrationTokenMapper.mapEntityToModel(userRegistrationTokenEntity);
    }

    @Override
    public void updateUserRegistrationToken(UserRegistrationToken userRegistrationToken) {
        UserRegistrationTokenEntity userRegistrationTokenEntity =
                userRegistrationTokenMapper.mapModelToEntity(userRegistrationToken);
        updateUserRegistrationToken.update(userRegistrationTokenEntity);
    }

    @Override
    public List<UserRegistrationToken> getUserRegistrationTokensByUserId(Long userId) {
        List<UserRegistrationTokenEntity> userRegistrationTokens = getUserRegistrationTokensByUserId.get(userId);
        return userRegistrationTokens.stream()
                .filter(userRegistrationTokenEntity -> !userRegistrationTokenEntity.isExpired())
                .map(userRegistrationTokenMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }
}
