package com.lorre.userapplication.repository.userregistrationtoken;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRegistrationTokenRepository extends JpaRepository<UserRegistrationTokenEntity, UUID> {

    List<UserRegistrationTokenEntity> findUserRegistrationTokenEntityByUserId(Long userId);
}
