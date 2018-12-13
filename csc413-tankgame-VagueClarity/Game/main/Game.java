package main;

import java.awt.*;

public class Game {


    private static GameManager gm;

    public static void main(String args[]){

        gm = new GameManager();
        Thread x;
        //gm.init();
        try {

            while (true) {
                gm.update();
                gm.repaint();

                //System.out.println(gm.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

    }



}
