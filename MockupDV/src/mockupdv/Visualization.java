/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author MichaelH
 * 
 **/

public class Visualization extends JPanel{
    
    public int locx;
    public int locy;
    public JPanel panel;
    
    public Visualization(int x, int y, JPanel panel2){
        
        locx = x;
        locy = y;
        panel = panel2;
        
    }
    
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillArc(locx, locy, 200, 200, 0, 360);
    }
    
}
