/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import game.Dir;
import game.Snake;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Bene
 */
public class KeyHandler implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                if(!(Snake.head.getDir() == Dir.DOWN) && !Snake.waitToMove){
                    Snake.head.setDir(Dir.UP);
                    Snake.waitToMove = true;
                }
                break;
            case KeyEvent.VK_A:
                if(!(Snake.head.getDir() == Dir.RIGHT) && !Snake.waitToMove){
                    Snake.head.setDir(Dir.LEFT);
                    Snake.waitToMove = true;
                }
                break;
            case KeyEvent.VK_S:
                if(!(Snake.head.getDir() == Dir.UP) && !Snake.waitToMove){
                    Snake.head.setDir(Dir.DOWN);
                    Snake.waitToMove = true;
                }
                break;
            case KeyEvent.VK_D:
                if(!(Snake.head.getDir() == Dir.LEFT) && !Snake.waitToMove){
                    Snake.head.setDir(Dir.RIGHT);
                    Snake.waitToMove = true;
                }
                break;                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
