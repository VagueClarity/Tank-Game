/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




/**
 *
 * @author anthony-pc
 */
public class TankControl implements KeyListener {

    private Tank t1;
    private Tank t2;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl(Tank t2, Tank t1, int up, int down, int left, int right, int shoot) {
        this.t2 = t2;
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == KeyEvent.VK_W) {
            this.t2.toggleUpPressed();
        }
        if (keyPressed == KeyEvent.VK_S) {
            this.t2.toggleDownPressed();
        }
        if (keyPressed == KeyEvent.VK_A) {
            this.t2.toggleLeftPressed();
        }
        if (keyPressed == KeyEvent.VK_D) {
            this.t2.toggleRightPressed();
        }
        if (keyPressed == KeyEvent.VK_SPACE){

            this.t1.toggleShoot();
        }
        if (keyPressed == KeyEvent.VK_ENTER){

            this.t2.toggleShoot();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.t1.unToggleRightPressed();
        }
        if (keyReleased  == KeyEvent.VK_W) {
            this.t2.unToggleUpPressed();
        }
        if (keyReleased == KeyEvent.VK_S) {
            this.t2.unToggleDownPressed();
        }
        if (keyReleased  == KeyEvent.VK_A) {
            this.t2.unToggleLeftPressed();
        }
        if (keyReleased  == KeyEvent.VK_D) {
            this.t2.unToggleRightPressed();
        }
        if (keyReleased == KeyEvent.VK_SPACE){

            this.t1.unToggleShoot();
        }
        if (keyReleased == KeyEvent.VK_ENTER){

            this.t2.unToggleShoot();
        }

    }
}
