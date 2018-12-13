package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Map {



    private int [][] map;
    private BufferedImage tileSheet;
    private Camera cam;
    public static final int TEXTURE_W = 50;
    public static final int TEXTURE_H  = 50;
    private PowerUp speedBoost;


    private ArrayList<Tile> wallTiles;


    public Map(int[][] givenMap, Camera cam){


        map = new int[givenMap.length][givenMap[0].length];
        this.cam = cam;
        for(int y = 0; y < map.length; y++){

            for(int x = 0; x < map[y].length; x++){

                map[y][x] = givenMap[y][x];
            }
        }

        tileSheet = loadTileSheet("D:\\Documents\\cscFiles\\Tank\\csc413-tankgame-VagueClarity\\Resources\\Tileset.jpg");
    }

    public Map(int width, int height, Camera cam){

        this.map = new int[height][width];
        this.cam = cam;
        this.wallTiles = new ArrayList<>();
    }

    public static Map textFile(String fileName, Camera cam){

        Map layer = null;
        ArrayList<ArrayList<Integer>> layout = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curr;

            while((curr = br.readLine()) != null) {

                if(curr.isEmpty()){
                    continue;
                }

                ArrayList<Integer> row = new ArrayList<>();

                // ignore spaces and put values
                String[] values = curr.trim().split(" ");

                for(String s: values){
                    if(!s.isEmpty()){
                        int y = Integer.parseInt(s);
                        row.add(y);
                    }
                }

                layout.add(row);
            }
        }
        catch(IOException e)
        {
            System.out.println("file not found");
        }

        int width = layout.get(0).size();
        int height = layout.size();


        System.out.println(layout.get(0).get(0));
        layer = new Map(width, height, cam);


        for(int y = 0; y < height; y++){

            for(int x = 0;  x < width; x++){
                layer.map[y][x] = layout.get(y).get(x);


            }
        }

        layer.tileSheet = layer.loadTileSheet("Resources/Tileset.jpg");
        return layer;


    }

    public BufferedImage loadTileSheet(String fileName){

        BufferedImage img = null;


        try{
            img = read(new File(fileName));

        }catch(IOException e){
            System.out.println("no file found");
        }


        return img;

    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;


        //System.out.println(cam.getOffX() + " " + cam.getOffY());

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){

                int index = map[y][x];
                int yOffset = 0;

                // resets index to go to the next row of tilesheet

                if(index > (tileSheet.getWidth() / 50) - 1){
                    yOffset++;
                    index = index - (tileSheet.getWidth() / 50);
                }

                g2d.drawImage(tileSheet,
                            x *TEXTURE_W -(int)cam.getOffX() ,
                            y * TEXTURE_H -(int)cam.getOffY(),
                            (x * TEXTURE_W) + TEXTURE_W -(int)cam.getOffX(),
                            (y * TEXTURE_H) + TEXTURE_H -(int)cam.getOffY(),
                            index * TEXTURE_W ,
                            yOffset * TEXTURE_H,
                            (index * TEXTURE_W) + TEXTURE_W,
                            (yOffset * TEXTURE_H) + TEXTURE_H,
                            null
                             );
            }
        }

    }

    public int getTileType(int xT , int yT){

        int tileX = xT / TEXTURE_W;
        int tileY = yT / TEXTURE_H;

        getTileXY(xT, yT);
        System.out.println(tileX + " " + tileY);
        int tileNumber = 0;

        try {
            tileNumber =  map[tileY][tileX];
            System.out.println(tileNumber);
        }
        catch(IndexOutOfBoundsException e){
           //e.printStackTrace();
        }

        return tileNumber;
    }

    public int setTileType(int xT, int yT, int type){

        int tileX = xT / TEXTURE_W;
        int tileY = yT / TEXTURE_H;

        try {
            map[tileY][tileX] = type;
            System.out.println(type);
        } catch(IndexOutOfBoundsException e){
            //e.printStackTrace();
        }

        return type;
    }

    public void wallTileSetUp(){

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){

                if(map[y][x] == 2 ){

                    wallTiles.add(new Tile(x * Map.TEXTURE_W,y * Map.TEXTURE_H, Map.TEXTURE_W, Map.TEXTURE_H, 2, this, 0));
                }
                if(map[y][x] == 4){
                    wallTiles.add(new Tile(x * Map.TEXTURE_W,y * Map.TEXTURE_H, Map.TEXTURE_W, Map.TEXTURE_H, 4, this, 3));
                }
            }
        }


        for(int i = 0; i < wallTiles.size(); i++ ){

            System.out.println("WallTile(" + i + "): " + wallTiles.get(i).getX() + ", " + wallTiles.get(i).getY());
        }

    }



    // returns x and y world coordinate position of the tile
    public int[] getTileXY(int xT, int yT){

        int tempXY[] = new int[2]; // for storing x and y value of a tile

        int tileX = xT / TEXTURE_W;
        int tileY = yT / TEXTURE_H;

        int tileWX = tileX * TEXTURE_W;
        int tileWY = tileY * TEXTURE_H;

        tempXY[0] = tileWX; // tempXY [0] has the x coordinate of the tile that the tank is on
        tempXY[1] = tileWY; // tempXY [1] has the y coordinate of the tile that the tank is on

        return tempXY;

    }

    public boolean isSolid(int index){
        switch (index){
            case 5:
                return false;
            case 2:
                return true;
            default:
                return false;

        }
    }

    public ArrayList<Tile> getWallTiles() {
        return wallTiles;
    }

    public int MapH(){ return map.length; }

    public int MapW(){ return map[0].length; }

    public int MapWorld_H() { return map.length * TEXTURE_H;}

    public int MapWorld_W(){ return map[0].length * TEXTURE_W;}

    public int[][] getMap() {
        return map;
    }
}
