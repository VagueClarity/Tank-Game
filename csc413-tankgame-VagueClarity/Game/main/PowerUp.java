package main;

import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class PowerUp {



    private BufferedImage img;
    private int x;
    private int y;
    private int w;
    private int h;
    private int time;
    private int SPAWN_TIMER;
    private int powerUpTimer;
    private boolean spawned;
    private boolean collected;
    private Random rand;
    private Camera cam;
    private GameManager gm;


   // private ArrayList<PowerUp> powerUps;


    public PowerUp( int timer, Camera cam, GameManager gm ){


        try {
            this.img =  read(new File("Resources/speedUp.png"));
        }catch(Exception e){
            System.out.println("Power Image not found");
        }

        this.gm = gm;
        rand = new Random();
        this.x = rand.nextInt(gm.getMap().MapWorld_W() - 50) + 50;
        this.y =  rand.nextInt(gm.getMap().MapWorld_H() - 50) + 50;
        this.w = 50;
        this.h = 50;
        //this.powerUps = new ArrayList<>();
        this.time = 100;
        this.SPAWN_TIMER = timer;
        this.powerUpTimer = 1000;

        this.spawned = true;
        this.collected = false;
        this.cam = cam;
    }


    public void update(Tank tank){
        //if(collected) {
            spawn();
           // spawned =false;
        //}
        if(collected) {
            doEffect(tank);
        }

        if(powerUpTimer <= 0) {
            powerUpTimer = 0;

        }

    }


    public void spawn(){

        time -= 1;
        if(time <= 0 ){

            this.x = rand.nextInt(gm.getMap().MapWorld_W() - 50) + 50;
            this.y =  rand.nextInt(gm.getMap().MapWorld_H() - 50) + 50;
            spawned = true;
            time = SPAWN_TIMER;
        }



    }

//    private void setUp(){
//
//        powerUps.add(new PowerUp(this.SPAWN_TIMER, cam));
//    }


    public void doEffect(Tank tank){

        powerUpTimer -=1;

            if (powerUpTimer >= 0) {
                tank.setR(8);
            } else {
                tank.setR(3);
            }
            System.out.println(powerUpTimer);

    }

    public void draw(Graphics g){


        Graphics2D g2d = (Graphics2D) g;

        if(spawned && !collected) {
            g2d.drawImage(img, x-(int) cam.getOffX(), y -(int) cam.getOffY(), w,h, null);
        }

    }

    /**
     * Setters and Getters
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }


}
