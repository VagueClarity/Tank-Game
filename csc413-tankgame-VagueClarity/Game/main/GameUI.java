package main;

import java.awt.*;

public class GameUI {

   private Tank tempT1;
   private Tank tempT2;



    public GameUI(Tank t1, Tank t2){

        tempT1 = t1;
        tempT2 = t2;
    }




    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, GameManager.SCREEN_H - 110, GameManager.SCREEN_W, 100 );

        g2d.setColor(Color.RED);
        g2d.fillRect(GameManager.SCREEN_W - 1180, GameManager.SCREEN_H - 100 + 10, 200, 40 );

        g2d.setColor(Color.GREEN);
        g2d.fillRect(GameManager.SCREEN_W - 1180, GameManager.SCREEN_H - 100 + 10, tempT1.getHealth()*2, 40 );


        g2d.setColor(Color.RED);
        g2d.fillRect(GameManager.SCREEN_W - 350, GameManager.SCREEN_H - 100 + 10, 200, 40 );

        g2d.setColor(Color.GREEN);
        g2d.fillRect(GameManager.SCREEN_W - 350, GameManager.SCREEN_H - 100 + 10, tempT2.getHealth()*2, 40 );


    }
}
