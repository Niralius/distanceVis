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

    int last_right_drag_x = 0;  // for rotations
    int last_right_drag_y = 0;

    public Listener(Visualization p) {
        ap = p;
    }
    
    public void mouseMoved(MouseEvent e) {
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
                double dx = (x - last_drag_x) / (double) ap.getWidth();
                double dy = (y - last_drag_y) / (double) ap.getHeight();
                
//                total_offset_x += dx / ap.scale;
//                total_offset_y += dy / ap.scale;
                
                double inc_X = -(dx / ap.scale);
                double inc_Y = -(dy / ap.scale);
                
                ap.shiftX -= inc_X;
                ap.shiftY -= inc_Y;
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
                total_offset_x += dx / ap.scale;
                total_offset_y += dy / ap.scale;
                ap.angle += dx;// / ap.scale;
//                ap.center_x = (ap.center_x - dx / ap.scale);
//                ap.center_y = (ap.center_y - dy / ap.scale);
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
            double factor = e.getWheelRotation();
            double newZoom = ap.scale + 0.5*factor;
            ap.scale = newZoom;           
        }
    }
    
}
