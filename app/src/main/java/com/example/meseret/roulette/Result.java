package com.example.meseret.roulette;

import java.util.Random;
import org.andengine.entity.sprite.Sprite;

public class Result {
    private Sprite ball;
    private int luckyNumber = 0;

    public Result(Sprite balls) {
        this.ball = balls;
    }

    public void getNumber(int gamestarted) {
        switch (gamestarted) {
            case 1:
                Random r = new Random();
                this.luckyNumber = ((int) (Math.random() * ((double) 3))) + 1;
                findLuckyNumberfor1(this.luckyNumber);
                return;
            case 2:
                Random r2 = new Random();
                this.luckyNumber = ((int) (Math.random() * ((double) 3))) + 1;
                findLuckyNumberfor2(this.luckyNumber);
                return;
            case 3:
                Random r3 = new Random();
                this.luckyNumber = ((int) (Math.random() * ((double) 3))) + 1;
                findLuckyNumberfor3(this.luckyNumber);
                return;
            default:
                return;
        }
    }

    public void findLuckyNumberfor1(int result) {
        switch (result) {
            case 1:
                this.ball.setPosition((float) findcenterx(186, 201), (float) findcentery(120, 156));
                return;
            case 2:
                this.ball.setPosition((float) findcenterx(180, 192), (float) findcentery(182, 169));
                return;
            case 3:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            case 4:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            default:
                return;
        }
    }

    public void findLuckyNumberfor2(int result) {
        switch (result) {
            case 1:
                this.ball.setPosition((float) findcenterx(186, 201), (float) findcentery(120, 156));
                return;
            case 2:
                this.ball.setPosition((float) findcenterx(180, 192), (float) findcentery(182, 169));
                return;
            case 3:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            case 4:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            default:
                return;
        }
    }

    public void findLuckyNumberfor3(int result) {
        switch (result) {
            case 1:
                this.ball.setPosition((float) findcenterx(186, 201), (float) findcentery(120, 156));
                return;
            case 2:
                this.ball.setPosition((float) findcenterx(180, 192), (float) findcentery(182, 169));
                return;
            case 3:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            case 4:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            default:
                return;
        }
    }

    public void findLuckyNumberfor4(int result) {
        switch (result) {
            case 1:
                this.ball.setPosition((float) findcenterx(186, 201), (float) findcentery(120, 156));
                return;
            case 2:
                this.ball.setPosition((float) findcenterx(180, 192), (float) findcentery(182, 169));
                return;
            case 3:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            case 4:
                this.ball.setPosition((float) findcenterx(160, 171), (float) findcentery(202, 185));
                return;
            default:
                return;
        }
    }

    public int findcenterx(int x1, int x2) {
        return (x1 + x2) / 2;
    }

    public int findcentery(int y1, int y2) {
        return (y1 + y2) / 2;
    }
}
