package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {


    private int x;
    private int y;
    private int w;
    private int h;
    private BufferedImage img;
    private Map map;
    private int health;
    private boolean visible;

    private int index;
    private Camera cam;


    public Tile(int x, int y, int w, int h, int index, Map map, int health){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.index = index;
        this.visible = true;
        this.health = health;
        this.map = map;

    }

    public void update(){


        System.out.println("health of tile" + this.health);
        if(this.health < 0 && index == 4){

           this.visible = false;
            map.setTileType(x,y,7);
        }
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
