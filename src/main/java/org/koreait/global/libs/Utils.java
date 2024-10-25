package org.koreait.global.libs;

public class Utils {

    // "-" 줄 생성
    public static void draw(char ch, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
    public static void drawLine(int length){ draw('-', length); }

}
