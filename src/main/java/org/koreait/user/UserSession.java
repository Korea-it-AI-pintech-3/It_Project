package org.koreait.user;

import org.koreait.user.entities.User;

public class UserSession {
    private static User user;

    /**
     * 로그인 여부 확인
     *
     * @return
     */
    public static boolean isLogin() {
        return user != null;
    }

    /**
     * 로그아웃 처리
     *
     */
    public static void logout() {
        user = null;
    }

    /**
     * 로그인 처리
     *
     * @param user
     */
    public static void login(User user) {
        UserSession.user = user;
    }
}
