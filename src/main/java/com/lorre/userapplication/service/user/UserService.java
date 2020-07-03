package com.lorre.userapplication.service.user;

import com.lorre.userapplication.request.ChangeEmailForm;
import com.lorre.userapplication.request.ChangePasswordForm;
import com.lorre.userapplication.request.ChangeUsernameForm;
import com.lorre.userapplication.request.CreateUserForm;
import com.lorre.userapplication.resource.UserResource;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResource> getUsers();

    Long createUser(CreateUserForm createUserForm);

    UserResource getUserById(Long id);

    void changeUsername(Long id, ChangeUsernameForm changeUsernameForm);

    void changeEmail(Long id, ChangeEmailForm changeEmailForm);

    void changePassword(Long id, ChangePasswordForm changePasswordForm);

    void activateAccount(Long userId, UUID userRegistrationTokenId);
}
