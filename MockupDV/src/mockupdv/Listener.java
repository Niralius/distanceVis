/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import mockupdv.Visualization;
import mockupdv.xyzPos;

/**
 *
 * @author MichaelH
 */

public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    Visualization ap;
    
    boolean dragging = false;
    int button_pressed;
    int last_drag_x = 0;
    int last_drag_y = 0;
    int total_offset_x = 0;
    int total_offset_y = 0;
    
    // For mouse selection
    static double mouseX = 0;
    static double mouseY = 0;

    int last_right_drag_x = 0;  // for rotations
    int last_right_drag_y = 0;

    public Listener(Visualization p) {
        ap = p;
    }
    
    public void mouseMoved(MouseEvent e) {
    	mouseX = e.getX();
    	mouseY = e.getY();
    	//Visualization.mouseMoved();
    	Visualization.mouseMoving = true;
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }    
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        button_pressed = e.getButton();
    }
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
    public void mouseDragged(MouseEvent e) {
        if (button_pressed == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();
            if (dragging) {
                double dx = (x - last_drag_x);
                double dy = (y - last_drag_y);                
                dx = dx / (double) ap.getWidth();  
                dy = dy / (double) ap.getHeight();              
                
                double inc_X = (dx / ap.scale);
                double inc_Y = (dy / ap.scale);
                
                double c = Math.max((ap.positions.maxX - ap.positions.minX)/2,
                                    (ap.positions.maxY - ap.positions.minY)/2);
                
                ap.shiftX += inc_X*c*Math.cos(ap.angle);
                ap.shiftY -= inc_Y*c;
                ap.shiftZ -= inc_X*c*Math.sin(ap.angle);
            }
            last_drag_x = x;
            last_drag_y = y;
            dragging = true;
        }
        if (button_pressed == MouseEvent.BUTTON3) {
            int x = e.getX();
            int y = e.getY();
            if (dragging) {
                double dx = (x - last_right_drag_x) / (double) ap.getWidth();
                double dy = (y - last_right_drag_y) / (double) ap.getHeight();
                ap.angle += dx;// / ap.scale;

            }
            last_right_drag_x = x;
            last_right_drag_y = y;
            dragging = true;
        }
        
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        // NOTE: the e.isShiftDown() trick apparently only works on Mac to distinguish V/H scrolling
        if (e.isShiftDown()) {
            // horizontal scrolling:
            ap.angle += e.getPreciseWheelRotation()/20;
        } else {
            // vertical scrolling:
//            double factor = e.getWheelRotation();
//            double newZoom = ap.scale + 0.5*factor;
//            ap.scale = newZoom;           

            double factor = e.getWheelRotation();
            double newZoom = ap.scale * Math.pow(0.95, factor);
            if (newZoom<0.01) newZoom = 0.01;
            if (newZoom>100) newZoom = 100;
            ap.scale = newZoom;           
        }
    }
    
}
