package org.koreait.HamdGame.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayOption implements Serializable {
    public int win;
    public int draw;
    public int lose;
    public static String[] myhand = {"가위", "바위", "보"};

}
