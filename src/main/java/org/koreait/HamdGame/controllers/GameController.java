package org.koreait.HamdGame.controllers;

import org.koreait.HamdGame.service.SimplePlayFunction;
import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;

public class GameController extends Controller {



    public GameController() {
        SimplePlayFunction simplePlayFunction = BeanContainer.getBean(SimplePlayFunction.class);

        setPromptProcess(() -> {
            simplePlayFunction.start();
        });
    }


    @Override
    public void view() {

    }
}
