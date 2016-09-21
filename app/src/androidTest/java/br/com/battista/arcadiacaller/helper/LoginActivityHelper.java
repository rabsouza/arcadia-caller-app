package br.com.battista.arcadiacaller.helper;

import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_MAIL;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_PROFILE;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_USERNAME;
import static com.googlecode.eyesfree.utils.LogUtils.TAG;

import android.util.Log;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.LoginService;

public class LoginActivityHelper {

    private LoginActivityHelper() {
    }

    public static User createNewUser() {
        LoginService loginService = Inject.provideLoginService();
        User user = new User()
                .username(DATA_USER_TEST_USERNAME)
                .mail(DATA_USER_TEST_MAIL)
                .profile(DATA_USER_TEST_PROFILE);
        user.initEntity();
        try {
            loginService.create(user);
        } catch (EntityAlreadyExistsException e) {
            Log.e(TAG, "EntityAlreadyExistsException: the error will be ignored!");
        }
        return user;
    }

    public static String loginUser(String username) {
        LoginService loginService = Inject.provideLoginService();

        return loginService.login(username);
    }
}
