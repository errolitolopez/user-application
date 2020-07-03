package com.lorre.userapplication.controller.user;

import com.lorre.userapplication.request.ChangeEmailForm;
import com.lorre.userapplication.request.ChangePasswordForm;
import com.lorre.userapplication.request.ChangeUsernameForm;
import com.lorre.userapplication.request.CreateUserForm;
import com.lorre.userapplication.resource.UserResource;
import com.lorre.userapplication.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResource> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public Long createUser(@RequestBody CreateUserForm createUserForm) {
        return userService.createUser(createUserForm);
    }

    @PutMapping("/user/change-username/{id}")
    public void changeUsername(@PathVariable Long id, @RequestBody ChangeUsernameForm changeUsernameForm) {
        userService.changeUsername(id, changeUsernameForm);
    }

    @PutMapping("/user/change-email/{id}")
    public void changeEmail(@PathVariable Long id, @RequestBody ChangeEmailForm changeEmailForm) {
        userService.changeEmail(id, changeEmailForm);
    }

    @PutMapping("/user/change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordForm changePasswordForm) {
        userService.changePassword(id, changePasswordForm);
    }

    @GetMapping("/user/activate-account/{userId}/{userRegistrationTokenId}")
    public void activateAccount(@PathVariable Long userId, @PathVariable UUID userRegistrationTokenId) {
        userService.activateAccount(userId, userRegistrationTokenId);
    }
}
