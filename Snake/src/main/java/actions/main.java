/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import clocks.GameClock;
import gui.Gui;

/**
 *
 * @author Bene
 */
public class main {
    
    public static void main(String[] args) {
        Gui g = new Gui();
        GameClock gc = new GameClock();
        
        g.create();
        gc.start();
    }
    
    
}
