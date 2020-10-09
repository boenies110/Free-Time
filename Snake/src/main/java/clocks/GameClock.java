/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clocks;

import actions.Collision;
import game.Snake;

/**
 *
 * @author Bene
 */
public class GameClock extends Thread{
    public static boolean running = true;
    
    public void run(){
        while(running){
            try {
                sleep(200);
                Snake.move();
                Snake.waitToMove = false;
                Collision.collidePickUp();
                if(Collision.collideSelf() || Collision.collideWall()){
                    Snake.tails.clear();
                    Snake.head.setX(7);
                    Snake.head.setY(7);
                    Snake.score = 0;
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
