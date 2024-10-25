package org.koreait.main.templates;

import org.koreait.global.Template;

public class MainMenu implements Template {

    /**
     * 메인 화면 메뉴 출력
     *
     */
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 게임하기..")
                .append("2. 메뉴...\n")
                        .append("LOGOUT. 로그아웃\n");

        System.out.println(sb);
    }
}
