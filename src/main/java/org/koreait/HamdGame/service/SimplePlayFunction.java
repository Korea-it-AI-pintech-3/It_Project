package org.koreait.HamdGame.service;

import org.koreait.HamdGame.entities.PlayOption;
import org.koreait.global.BeanContainer;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.MainController;

import java.util.Random;

public class SimplePlayFunction {

    private final PlayOption playOption;

    public SimplePlayFunction() {
        playOption = BeanContainer.getBean(PlayOption.class, true);
    }

    public void start() {
        Random random = new Random();

        String[] choices = PlayOption.myhand;


        // 컴퓨터의 선택
        int com = random.nextInt(choices.length);
        String comChoice = choices[com];

        while (true) {
            // 사용자 입력
            String user = Utils.getString("가위(1), 바위(2), 보(3) 중 하나 선택하세요\n입력란(메뉴:M, 종료: Q 입력)", "입력하세요.");
            Utils.drawLine(42);


            // 사용자가 'q'를 입력하면 게임 종료
            if (user.toLowerCase().equals("q")) {
                System.out.println("게임 종료!");
                Utils.drawLine(42);

               System.exit(0);
            } else if (user.toLowerCase().equals("m")) {
                Utils.loadController(MainController.class);
                break;
            }

            // 사용자의 선택을 한글로 변환하여 출력
            String userChoice = "";
            switch (user) {
                case "2":
                    userChoice = "바위";
                    break;
                case "1":
                    userChoice = "가위";
                    break;
                case "3":
                    userChoice = "보";
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 가위(1), 바위(2), 보(3) 중 하나를 입력하세요.");
                    Utils.drawLine(42);

                    continue; // 잘못된 입력이면 다시 입력받음
            }

            // 컴퓨터, 플레이어 선택 출력
            System.out.printf("|컴퓨터 : %s        |     플레이어 : %s%n", comChoice, userChoice);

            // 컴퓨터 선택을 알파벳으로 변환
            String comLetter = comChoice.equals("가위") ? "1" : comChoice.equals("바위") ? "2" : "3";

            // 승패 결정
            if (user.equals(comLetter)) {
                System.out.println("|--------------무승부입니다!--------------|");
                playOption.draw++;
            } else if (
                    (user.equals("2") && comLetter.equals("1")) ||
                            (user.equals("1") && comLetter.equals("3")) ||
                            (user.equals("3") && comLetter.equals("2"))
            ) {
                System.out.println("|--------------이겼습니다!---------------|");
                playOption.win++;
            } else {
                System.out.println("|----------------졌습니다!----------------|");
                playOption.lose++;
            }


            // 전적 출력
            System.out.printf("| 전적    |  승:%d   |  무:%d   |  패:%d     |\n", playOption.win, playOption.draw, playOption.lose);
            Utils.drawLine(42);
        }

    }

}
