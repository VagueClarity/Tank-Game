package main;

public class Camera {


    private float offX;
    private float offY;
    private GameManager gm;

    public Camera(GameManager gm){

        this.gm = gm;
        this.offX = 0;
        this.offY = 0;
    }


    public void update(Tank t){

        offX = t.getX()  - gm.SCREEN_W / 2 + t.getW()/2;
        offY = t.getY() - gm.SCREEN_H / 2 + t.getH() /2;


    }
    public void move(float x, float y){
        offX += x;
        offY += y;

    }

    public float getOffX() {
        return offX;
    }

    public void setOffX(float offX) {
        this.offX = offX;
    }

    public float getOffY() {
        return offY;
    }

    public void setOffY(float offY) {
        this.offY = offY;
    }


}
