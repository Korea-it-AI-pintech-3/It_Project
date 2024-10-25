package org.koreait.user.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.user.entities.User;
import org.koreait.user.services.UserInfoService;
import org.koreait.user.services.UserSaveService;
import org.koreait.user.templates.UserJoin;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserJoinController extends Controller {

    public UserJoinController() {

        UserSaveService service = BeanContainer.getBean(UserSaveService.class);
        UserInfoService infoService = BeanContainer.getBean(UserInfoService.class);

        setPromptProcess(() -> {
            User user = new User();
            String id = Utils.getString("아이디", "아이디를 입력하세요.", List.of(
                    // 아이디 자리수 검증 (최소 12자 이상)
                    input -> {
                        if (input.length() > 12) {
                            System.out.println("아이디는 12자 이하 입력하세요.");
                            return false;
                        }

                        return true;
                    },
                    // 아이디 중복 여부 체크
                    input -> {
                        if (infoService.getUser(input) != null) {
                            System.out.println("이미 가입된 회원입니다.");
                            return false;
                        }

                        return true;
                    }

            ));
            String password = Utils.getString("비밀번호", "비밀번호를 입력하세요.", List.of(
                    // 자리수 제한 최소 4자리
                    input -> {
                        if (input.length() < 4) {
                            System.out.println("비밀번호는 8자리 이상 입력하세요.");
                            return false;
                        }

                        return true;
                    }
            ));

            Utils.getString("비밀번호 확인", "입력한 비밀번호가 맞는지 다시한번 입력하세요.", List.of(
                    input -> {
                        if (!input.equals(password)) {
                            System.out.println("비밀번호가 일치하지 않습니다.");
                            return false;
                        }

                        return true;
                    }
            ));

            String nickname = Utils.getString("닉네임", "닉네임을 입력하세요.");

            user.setId(id);
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            user.setPassword(hash);
            user.setNickname(nickname);

            service.save(user); // 회원 가입 처리

            System.out.println("회원 가입 완료!");

            // 가입이 완료되면 로그인 화면으로 전환
            Utils.loadController(UserLoginController.class);
        });
    }

    @Override
    protected String getPromptText() {
        return "회원가입 항목을 입력하세요(메뉴:M, 종료:Q).\n";
    }

    @Override
    public void view() {

        Utils.loadTpl(UserJoin.class);
    }
}
