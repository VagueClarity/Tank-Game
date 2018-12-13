package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tank {


    private int x;
    private int y;
    private int w;
    private int h;
    private int R = 3;
    private final int ROTATIONSPEED = 3;
    private int angle;
    private int xv;
    private int yv;

    private int health;



    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shoot;

    private Color bullet;
    private BufferedImage img;
    private Camera cam;
    private GameManager gm;

    private ArrayList<Bullets> bullets = new ArrayList<>();
    private static int DEFAULT_BULLET_DELAY = 20;
    private static int time = 0;
    private Map map;
    private ArrayList<Tile> walls;

    private BufferedImage screen;




    public Tank(int x, int y, int xv, int yv, int angle, BufferedImage img, Color bullet, GameManager gm){

        this.x = x;
        this.y = y;
        this.w = img.getWidth();
        this.h = img.getHeight();
        this.xv = xv;
        this.yv = yv;
        this.angle = angle;
        this.img = img;
        this.shoot = false;
        this.bullet = bullet;
        this.cam = new Camera(gm);
        this.gm = gm;
        this.health = 100;
        this.map = gm.getMap();
        this.walls = this.map.getWallTiles();
        this.screen = new BufferedImage(GameManager.SCREEN_W/2, GameManager.SCREEN_H, BufferedImage.TYPE_INT_RGB);

    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void toggleShoot(){
        this.shoot = true;
    }

    void unToggleShoot(){
        this.shoot = false;
    }




    public void update(){

//        if(this.health >= 0){
//            this.health --;
//        }

        if (this.UpPressed) {

            this.moveForwards();
        }
        if (this.DownPressed) {

            this.moveBackwards();
        }

        if (this.LeftPressed) {

            this.rotateLeft();
        }
        if (this.RightPressed) {

            this.rotateRight();
        }

        cam.update(this);




        time -= 1;
        if(time <= 0 && this.shoot ){
            fire();
          time = DEFAULT_BULLET_DELAY;
        }

        for(Bullets b: bullets){
            if(b.isVisible()) {
                b.shoot();
            }
        }

        for(int i = 0; i < bullets.size(); i++){

            for(int j = 0; j < walls.size(); j++) {
                if (collideBullet(bullets.get(i),walls.get(j)) && bullets.get(i).isVisible() && walls.get(j).isVisible()){

                    bullets.get(i).setVisible(false);
                    walls.get(j).setHealth( walls.get(j).getHealth() - 1);
                   // map.setTileType(walls.get(j).getX(), walls.get(j).getY(), 7);
                    walls.get(j).update();
                    //walls.get(j).setVisible(false);
                }
            }
        }

        if(collidePowerUp(this, gm.getPowerUp()) && !gm.getPowerUp().isCollected()){
            gm.getPowerUp().setCollected(true);
           // gm.getPowerUp().doEffect(this,1000);
            System.out.println("collected");
        }


    }


    private void rotateLeft() {

        //collide(this.x, this.y);
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {

       // collide(this.x + this.w, this.y);
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        xv = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        yv = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= xv;
        y -= yv;


        for(int i = 0; i < walls.size(); i++){

            if(collide(this, walls.get(i)) && walls.get(i).isVisible()){
                x += xv;
                y += yv;
            }


        }

        checkBorder();
    }

    private void moveForwards() {

        xv = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        yv = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += xv;
        y += yv;



        for(int i = 0; i <walls.size(); i++){

           if(collide(this, walls.get(i)) && walls.get(i).isVisible()){
               x -= xv;
               y -= yv;
           }

        }

        checkBorder();
    }



    public void fire () {

        Bullets b = new Bullets(x+(img.getWidth()/2), y+(img.getHeight()/2), angle, bullet );
        bullets.add(b);

    }

    private void checkBorder() {
        if (x < 50) {
            x = 50;
        }
        if (x >= gm.getMap().MapW() * Map.TEXTURE_W - 100) {
            x = gm.getMap().MapW() * Map.TEXTURE_W - 100;
        }
        if (y < 50) {
            y = 50;
        }
        if (y >= gm.getMap().MapH() * Map.TEXTURE_H - 100) {
            y = gm.getMap().MapH() * Map.TEXTURE_H  - 100;
        }
    }




    public ArrayList<Bullets> getBullets() {
        return bullets;
    }

    public boolean collide(Tank tank, Tile tile){

        int tX = tile.getX();
        int tY = tile.getY();
        int tW = tile.getW();
        int tH = tile.getH();

        if(this.x < tX + tW && this.x + this.w > tX && this.y < tY + tH && this.y + this.h > tY ){

          return true;
        }

        return false;
    }

    public boolean collideBullet(Bullets b, Tile tile){


        int tX = tile.getX();
        int tY = tile.getY();
        int tW = tile.getW();
        int tH = tile.getH();


        if(b.getX() < tX + tW && b.getX() + b.getW() > tX && b.getY() < tY + tH && b.getY()+ b.getH() > tY ){

            return true;
        }
        return false;

    }

    public boolean collidePowerUp(Tank t, PowerUp p){

        if(this.x < p.getX() + p.getW() && this.x + this.w > p.getX() && this.y < p.getY() + p.getH() && this.y + this.h > p.getY() ){

            return true;
        }

        return false;

    }

    public boolean collideTank(Tank t){

        if(this.x < t.getX() + t.getW() && this.x + this.w > t.getX() && this.y < t.getY() + t.getH() && this.y + this.h > t.getY() ){

            return true;
        }

        return false;

    }


    private Graphics2D buffer;

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        buffer = screen.createGraphics();
        AffineTransform rotation = AffineTransform.getTranslateInstance(x - cam.getOffX(), y - cam.getOffY());
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);


        for(Bullets b : bullets ){
            if(b.isVisible()) {
                b.draw(g, cam);
            }
        }

        //buffer.drawImage(this.img, rotation, null);
       // g2d.drawImage(screen, 0,0,null);
        g2d.drawImage(this.img, rotation, null);


    }


    /**
     * Getters and Setter
     */
    public int getX(){ return this.x;}

    public int getY(){return this.y;}

    public int getW(){return img.getWidth();}

    public int getH(){ return img.getHeight();}

    public void setX(int x){this.x  = x;}

    public void setY(int y){this.y = y;}

    public int getXV(){

        return this.xv;
    }

    public int getYV(){

        return this.yv;
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int i){
        this.health = i;
    }

    public int getR() {
        return R;
    }

    public void setR(int r){
        this.R = r;
    }

}
