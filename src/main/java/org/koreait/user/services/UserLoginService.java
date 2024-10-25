package org.koreait.user.services;

import org.koreait.global.BeanContainer;
import org.koreait.user.UserSession;
import org.koreait.user.entities.User;

public class UserLoginService {


    public void login(String id) {
        UserInfoService infoService = BeanContainer.getBean(UserInfoService.class);
        User user = infoService.getUser(id);

        UserSession.login(user);
    }
}
