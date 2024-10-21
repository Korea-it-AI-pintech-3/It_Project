package org.koreait.global;

import org.koreait.global.exceptions.CommonException;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.MainController;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Router {
    public static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    /**
     * 컨트롤러 라우터 실행
     *
     */
    public void execute() {
        while(true) {
            try {
                Utils.loadController(MainController.class);
            } catch (Exception e) {
                // 예외 공통 출력 처리 S
                int code = 500; // 우리가 정의한 예외가 아니라면 500으로 예외 코드 고정

                // 우리가 정의한 예외라면 그 예외 코드로 교체
                // reflection으로 유입된 경우
                if (e instanceof InvocationTargetException targetException) {
                   Throwable throwable = targetException.getTargetException();
                   if (throwable instanceof CommonException exception) {
                       code = exception.getCode();
                   }
                }
                // 그외
                if (e instanceof CommonException commonException) {
                    code = commonException.getCode();
                }

                System.out.printf("[%d]%s%n", code, e.getMessage());
                // 예외 공통 출력 처리 E
            }
        }
    }
}
