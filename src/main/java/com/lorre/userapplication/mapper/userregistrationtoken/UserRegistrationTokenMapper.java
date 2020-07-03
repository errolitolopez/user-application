package com.lorre.userapplication.mapper.userregistrationtoken;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;

public interface UserRegistrationTokenMapper {

    UserRegistrationToken mapEntityToModel(UserRegistrationTokenEntity userRegistrationTokenEntity);

    UserRegistrationTokenEntity mapModelToEntity(UserRegistrationToken userRegistrationToken);
}
