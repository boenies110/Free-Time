/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import game.Snake;

/**
 *
 * @author Bene
 */
public class Collision {
    public static boolean collideSelf(){
        for(int i = 0; i < Snake.tails.size(); i++){
            if(Snake.head.getX() == Snake.tails.get(i).getX() &&
                    Snake.head.getY() == Snake.tails.get(i).getY() &&
                    !Snake.tails.get(i).isWait()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean collideWall(){
        
        return (Snake.head.getX() < 0 ||
                Snake.head.getX() > 15 ||
                Snake.head.getY() < 0 ||
                Snake.head.getY() > 15 );
    }
    
    public static void collidePickUp(){
        if(Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()){
            Snake.pickup.reset();
            Snake.addTail();
            
            Snake.score += 1;
            if(Snake.score > Snake.bestscore) Snake.bestscore = Snake.score;
        }
    }
}
