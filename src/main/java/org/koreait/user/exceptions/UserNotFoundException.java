package org.koreait.user.exceptions;

import org.koreait.global.exceptions.CommonException;

public class UserNotFoundException extends CommonException {
    public UserNotFoundException() {
        super("사용자를 찾을 수 없습니다.", 404);
    }
}
