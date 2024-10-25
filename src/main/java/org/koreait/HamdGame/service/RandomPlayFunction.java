package org.koreait.HamdGame.service;

import org.koreait.HamdGame.entities.PlayOption;
import org.koreait.global.BeanContainer;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.MainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomPlayFunction {

    private final PlayOption playOption;

    public RandomPlayFunction(){
        playOption = BeanContainer.getBean(PlayOption.class, true);
    }


    public void start(){


        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Utils.drawLine(42);
        System.out.println("랜덤 가위바위보 게임에 오신것을 환영합니다.");
        System.out.println("랜덤한 5개의 패가 주어집니다.");
        System.out.println("그 중 하나를 선택하여 컴퓨터에게 승리하세요.\n");
        Utils.drawLine(42);

        while (true) {



            // 플레이어 랜덤 출력 배열 생성
            List<String> playerRandom = new ArrayList<>();
            String[] selections = playOption.myhand;
            for (int i = 0; i < 5; i++) {
                int randomNumber = (int) (Math.random() * selections.length);
                playerRandom.add(selections[randomNumber]);
            }


            // 컴퓨터 랜덤 출력 배열 생성
            List<String> comRandom = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                int randomNumber2 = (int) (Math.random() * selections.length);
                comRandom.add(selections[randomNumber2]);
            }


            for (int i = 0; i < 5; i++) {
                System.out.println("[메뉴 : M, 종료 : Q]");
                System.out.println("\n라운드 " + (i + 1));
                System.out.println("유저의 선택지" + playerRandom+"👈");
                System.out.println("컴퓨터의 선택지" + comRandom);
                System.out.print("유저의 선택은? [가위 : 1] [바위 : 2] [보 : 3]\n영어로 입력하세요. : ");
                String userChoice = sc.nextLine();

                // 사용자가 'q'를 입력하면 게임 종료
                if (userChoice.toLowerCase().equals("q")) {
                    System.out.println("게임 종료!");
                    Utils.drawLine(42);

                    System.exit(0);
                } else if (userChoice.toLowerCase().equals("m")) {
                    Utils.loadController(MainController.class);
                    break;
                }

                switch (userChoice) {
                    case "1":
                        userChoice = "가위";
                        break;
                    case "2":
                        userChoice = "바위";
                        break;
                    case "3":
                        userChoice = "보";
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        i--;
                        continue;
                }

                if (playerRandom.contains(userChoice)) {
                    int computerIndex = random.nextInt(comRandom.size());
                    String computerChoice = comRandom.get(computerIndex);
                    System.out.println("컴퓨터의 선택!: " + computerChoice);


                    String result = getResult(userChoice, computerChoice);
                    System.out.println("결과: " + result);

                    playerRandom.remove(userChoice);
                    comRandom.remove(computerChoice);
                } else {
                    System.out.println("잘못된 선택입니다.");
                    i--;
                }
                // 전적 출력
                System.out.printf("| 전적    |  승:%d   |  무:%d   |  패:%d     |\n", playOption.win, playOption.draw, playOption.lose);
                Utils.drawLine(42);
            }
        }
    }

    String getResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            playOption.draw++;
            return "비김";  // Draw
        } else if ((userChoice.equals("가위") && computerChoice.equals("보")) ||
                (userChoice.equals("바위") && computerChoice.equals("가위")) ||
                (userChoice.equals("보") && computerChoice.equals("바위"))) {
            playOption.win++;
            return "유저 승";  // User wins
        } else {
            playOption.lose++;
            return "컴퓨터 승";  // Computer wins
        }
    }
}

