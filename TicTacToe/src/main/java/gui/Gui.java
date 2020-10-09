/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;

/**
 *
 * @author Bene
 */
public class Gui {
    private static int width = 800, height = 600;
    JFrame jf = new JFrame();
    
    public void create(){
        jf = new JFrame("Snake");
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);                                         //Bildschirmmitte
        jf.setLayout(null);
        jf.setResizable(false);
        
        jf.setVisible(true);
    }
}