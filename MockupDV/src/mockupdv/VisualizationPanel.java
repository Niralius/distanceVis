/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author santi
 */
public class VisualizationPanel extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.red);
        g.fillArc(100, 100, 100, 100, 0, 360);
    }
}
