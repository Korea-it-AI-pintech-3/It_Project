package org.koreait.user.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.MainController;
import org.koreait.user.entities.User;
import org.koreait.user.services.UserInfoService;
import org.koreait.user.services.UserLoginService;
import org.koreait.user.templates.UserLogin;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserLoginController extends Controller {



    public UserLoginController() {

        UserLoginService service = BeanContainer.getBean(UserLoginService.class);
        UserInfoService infoService = BeanContainer.getBean(UserInfoService.class);

        setPromptProcess(() -> {


            String id = Utils.getString("아이디", "아이디를 입력하세요.", List.of(
                    input -> {
                        if (infoService.getUser(input) == null) {
                            System.out.println("회원을 찾을 수 없습니다.");
                            return false;
                        }

                        return true;
                    }
            ));
            String password = Utils.getString("비밀번호", "비밀번호를 입력하세요.", List.of(
                    input -> {
                        User user = infoService.getUser(id);
                        String hash = user.getPassword();
                        if (!BCrypt.checkpw(input, hash)) {
                            System.out.println("비밀번호가 불일치 합니다.");
                            return false;
                        }

                        return true;
                    }
            ));

            // 검증 성공시 로그인 처리
            service.login(id);

            // 로그인 성공시 메인 화면 이동 - 로그인 상태이므로 메뉴 선택 화면이 나온다.
            Utils.loadController(MainController.class);
        });
    }

    @Override
    protected String getPromptText() {
        return "게임을 시작하시려면 로그인 해 주세요(메인:M, 종료:Q)\n";
    }

    @Override
    public void view() {
        Utils.loadTpl(UserLogin.class);
    }
}
