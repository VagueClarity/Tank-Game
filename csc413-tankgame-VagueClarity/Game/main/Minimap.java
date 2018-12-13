package main;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Minimap {

    private BufferedImage miniMap;
    private int size = 200;
    private int w;
    private int h;
    private int[][] map;
    private final int scale = 5;

    /**
     * 5 = brown
     * 7 = yellow
     * 2 = gray
     *
     */

    public Minimap(Map mapInstance) {

        this.map = mapInstance.getMap();
        this.h = mapInstance.MapH();
        this.w = mapInstance.MapW();
        this.miniMap = new BufferedImage(w * scale, h * scale, BufferedImage.TYPE_INT_RGB);

    }


    public Graphics2D draw(Graphics g, Tank t1, PowerUp p){

        Graphics2D g2d = (Graphics2D) g;
        int tankX = -(t1.getX()) ;
        int tankY = -(t1.getY());




        //g2d.drawImage(miniMap, GameManager.SCREEN_W/2 - (w * scale/2) , GameManager.SCREEN_H - (h * scale) - 30, null);

        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++ ){


                if(map[x][y] == 5){
                    g2d.setColor(Color.red);
                }else if(map[x][y] == 4) {
                    g2d.setColor(Color.GREEN);
                }
                else if(map[x][y] == 7){
                    g2d.setColor(Color.yellow);
                } else if(map[x][y] == 2){
                    g2d.setColor(Color.gray);
                }

                //g2d.setColor(Color.green);
                g2d.fillRect(GameManager.SCREEN_W/2 - (w * scale/2) + y *scale, GameManager.SCREEN_H - (h * scale ) - 30 + x * scale, scale,scale);
            }
        }

        g2d.setColor(Color.black);
        g2d.fillRect((GameManager.SCREEN_W/2 -(w * scale/2)) - tankX/(scale*2),
                      GameManager.SCREEN_H - (h * scale) - 30 - tankY/(scale * 2),
                      scale,
                      scale);
        g2d.setColor(Color.BLUE);

        if(!p.isCollected()) {
            g2d.fillRect(GameManager.SCREEN_W / 2 - (w * scale / 2) + p.getX() / (scale * 2), GameManager.SCREEN_H - (h * scale) - 30 + p.getY() / (scale * 2), scale, scale);
        }
        return null;
    }
}
