/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import actions.KeyHandler;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author Bene
 */
public class Gui {
    JFrame jf;
    Draw d;
    
    public static int width = 800, height = 600;
    public static int xoff = width / 2 - 270 , yoff = 20;
    
    public void create(){
        jf = new JFrame("Snake");
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);                                         //Bildschirmmitte
        jf.setLayout(null);
        jf.setResizable(false);
        jf.addKeyListener(new KeyHandler());
        
        d = new Draw();
        d.setBounds(0, 0, width, height);
        d.setVisible(true);
        jf.add(d);
        
        jf.requestFocus();
        jf.setVisible(true);
    }
    
}
