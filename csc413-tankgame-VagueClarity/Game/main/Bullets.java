package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Bullets {


    private int x;
    private int y;
    private int w;
    private int h;
    private BufferedImage img = null;
    private static int R = 10;
    private int angle;
    private Color color;
    private int damage;
    int xv;
    int yv;
    private boolean visible;




    public Bullets(int x, int y, int angle, Color color){

        this.x  = x;
        this.y = y;
        this.w = 15;
        this.h = 15;
        this.xv = 0;
        this.yv = 0;
        this.angle = angle;
        this.color = color;
        this.damage = 10;
        this.visible = true;
//        try{
//            this.img = read(new File("D:\\Documents\\cscFiles\\Tank\\csc413-tankgame-VagueClarity\\jar\\main\\bullet.png"));
//             rImg = img.getScaledInstance(30,20,Image.SCALE_SMOOTH);
//
//        }catch(IOException e){
//            System.out.println(e);
//
//        }

    }


    public void update(){

        shoot();
    }

    public void shoot(){

        xv = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        yv = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += xv;
        y += yv;
    }

    public void launch(){


    }

    public int getDamage(){
        return this.damage;
    }

    public void setDamage(int dm){
        this.damage = dm;
    }

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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void draw(Graphics g, Camera cam){



//        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
//        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(color);
        g2d.fillRect(x - (int)cam.getOffX(),y - (int)cam.getOffY(), w, h);

    }
}
