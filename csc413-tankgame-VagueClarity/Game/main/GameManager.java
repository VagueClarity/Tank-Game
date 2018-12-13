package main;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class GameManager extends JPanel {


    private JFrame jf;
    private JPanel jp;
    public static final int SCREEN_W = 1280;
    public static final int SCREEN_H = 720;

    private JButton button;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;
    private Tank t1;
    private Tank t2 = null;
    private Camera cam;
    private Map map;
    private Minimap minimap;
    private GameUI gameUI;
    private PowerUp powerUp;

    public GameManager(){
        setUp();
    }


    // Everything gets initialized here

    public void setUp(){

        jf = new JFrame("Tank game");

        bufferedImage = new BufferedImage(SCREEN_W, SCREEN_H, BufferedImage.TYPE_INT_RGB);
        cam = new Camera(this);
        map = map.textFile("Resources/TextureTile.txt", cam);
        map.wallTileSetUp();
        t1 = initTank(Color.red);
        t2 = initTank(Color.yellow);
//        t2.setX(450);
//        t2.setY(100);
        TankControl t1Control = new TankControl(t2,t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        powerUp = new PowerUp(1000, cam, this);


        minimap = new Minimap(this.map);
        gameUI = new GameUI(t1, t2);

        jf.setLayout(new BorderLayout());
        jf.addKeyListener(t1Control);
        jf.getContentPane().add(this);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(SCREEN_W, SCREEN_H);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);

    }

    public void update(){

        t1.update();
//        t2.update();
        powerUp.update(this.t1);
        cam.update( this.t1);


    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        buffer = bufferedImage.createGraphics();
        super.paintComponent(g2);
        buffer.setColor(Color.BLACK);
        buffer.setBackground(Color.RED);
        buffer.fillRect(-100,-100, SCREEN_W + 200, SCREEN_H + 100);
        map.draw(buffer);

        this.t1.draw(buffer);
//        this.t2.draw(buffer);
        buffer.setColor(Color.BLUE);
        powerUp.draw(buffer);
        gameUI.draw(buffer);
        minimap.draw(buffer, t1, powerUp);


        g2.drawImage(bufferedImage,0,0,null);
    }

    private Tank initTank(Color color){

        BufferedImage tank1 = null;
        try {
            tank1 = read(new File("Resources/tank1.png"));

        }
        catch(IOException e){

            System.out.println("Tank Image was not found");
        }

         return new Tank(50,50,0,0,0, tank1, color, this);
    }



    public Tank getTank(int i){

        if(i == 1){
            return t1;
        }
        else if(i == 2){
            return t2;
        }
        else{
            return null;
        }

    }

    public PowerUp getPowerUp(){
        return this.powerUp;
    }

    public Map getMap(){

        return this.map;
    }

}

