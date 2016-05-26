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

/**
 *
 * @author MichaelH
 */

public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    Visualization ap;

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
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        // NOTE: the e.isShiftDown() trick apparently only works on Mac to distinguish V/H scrolling
        if (e.isShiftDown()) {
            // horizontal scrolling:
            ap.angle += e.getPreciseWheelRotation()/20;
        } else {
            // vertical scrolling:
            double factor = Math.pow(1.05, e.getWheelRotation());
            double newZoom = ap.scale * factor;
            ap.scale = newZoom;
        }
    }
    
}
