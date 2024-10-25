package org.koreait.HamdGame.controllers;

import org.koreait.HamdGame.service.RandomPlayFunction;
import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;

public class RandomGameController extends Controller {

    public RandomGameController() {
        RandomPlayFunction RandomPlayFunction = BeanContainer.getBean(RandomPlayFunction.class);

        setPromptProcess(() -> {
            RandomPlayFunction.start();
        });
    }

    @Override
    public void view() {

    }
}
