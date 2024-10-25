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
        sb.append("1. 가위바위보   ")
                .append("2. 랜덤 가위바위보\n")
                    .append("3. 마이 페이지  ")
                        .append("LOGOUT. 로그아웃\n");

        System.out.println(sb);
    }
}
