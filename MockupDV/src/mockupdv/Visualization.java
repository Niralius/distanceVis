/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockupdv;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import static com.jogamp.opengl.GL.*;  // GL constants
import static com.jogamp.opengl.GL2.*; // GL2 constants
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import java.util.ArrayList;
import static mockupdv.xyzChooser.getMax;
import static mockupdv.xyzChooser.getMin;

import mockupdv.xyzPos;
import mockupdv.Listener;

/**
 *
 * @author MichaelH
 */
public class Visualization extends GLJPanel implements GLEventListener {
   // Define constants for the top-level container
//   private static final int CANVAS_WIDTH = 320;  // width of the drawable
//   private static final int CANVAS_HEIGHT = 240; // height of the drawable
//   private static final int FPS = 60; // animator's target frames per second
   
    java.util.List<xyzPos> xyzPosList;
    xyzPos positions = null;
    DistanceV dv;
   
    double scale = 1;
    double angle = 0;
    double shiftX = 0;
    double shiftY = 0;
    double shiftZ = 0;
    
    double red = 1.0;
    double green = 1.0;
    double blue = 1.0;
   
   /** The entry main() method to setup the top-level container and animator */
//   public static void main(String[] args) {
//      // Run the GUI codes in the event-dispatching thread for thread safety
//      SwingUtilities.invokeLater(new Runnable() {
//         @Override
//         public void run() {
//            // Create the OpenGL rendering canvas
//            GLCanvas canvas = new JOGL2Nehe03Color();
//            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
//
//            // Create a animator that drives canvas' display() at the specified FPS. 
//            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
//            
//            // Create the top-level container
//            final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
//            frame.getContentPane().add(canvas);
//            frame.addWindowListener(new WindowAdapter() {
//               @Override 
//               public void windowClosing(WindowEvent e) {
//                  // Use a dedicate thread to run the stop() to ensure that the
//                  // animator stops before program exits.
//                  new Thread() {
//                     @Override 
//                     public void run() {
//                        if (animator.isStarted()) animator.stop();
//                        System.exit(0);
//                     }
//                  }.start();
//               }
//            });
//            frame.pack();
//            frame.setVisible(true);
//            animator.start(); // start the animation loop
//         }
//      });
//   }
   
   // Setup OpenGL Graphics Renderer
   
   private GLU glu;  // for the GL Utility
   
   /** Constructor to setup the GUI for this Component */
   public Visualization() {
      this.xyzPosList = new ArrayList<>();
      this.addGLEventListener(this);
   }
   
   // ------ Implement methods declared in GLEventListener ------

   /**
    * Called back immediately after the OpenGL context is initialized. Can be used 
    * to perform one-time initialization. Run only once.
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
   }

   /**
    * Call-back handler for window re-size event. Also called when the drawable is 
    * first set to visible.
    */
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      
        Listener ml = new Listener(this); //Zooming rotating listener
        addMouseListener(ml);
        addMouseMotionListener(ml);
        addMouseWheelListener(ml);
            
      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;

      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.01, 1000000.0); // fovy, aspect, zNear, zFar
            
      // Enable the model-view transform
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

   /**
    * Called back by the animator to perform rendering.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();  // reset the model-view matrix

      if (!xyzPosList.isEmpty()) positions = xyzPosList.get(0);
      
      if(positions!=null && positions.x != null){
                  
        double c = Math.max((positions.maxX - positions.minX)/2,
                            (positions.maxY - positions.minY)/2);
        float markerSize = (float)c/100; //size of the shapes making up the vis
        double tmp1 = c/Math.sin(45.0/180.0*Math.PI);
        Double camDistance = Math.sqrt(tmp1*tmp1 - c*c); // Distance of the camera from the vis
        
        glu.gluLookAt((positions.centerX - shiftX)*scale + camDistance*Math.sin(angle), //Eye
                      (positions.centerY - shiftY)*scale, 
                      (positions.centerZ - shiftZ)*scale + camDistance*Math.cos(angle), 
                      (positions.centerX - shiftX)*scale, //center
                      (positions.centerY - shiftY)*scale, 
                      (positions.centerZ - shiftZ)*scale, 
                      0, 1, 0); //Up
        this.repaint();
               
        gl.glScaled(scale, scale, scale); //essentially the zooming function
        
        for(int i = 0; i < positions.x.length; i++){

            gl.glPushMatrix();
//            if () {           //2D and 3D
//                gl.glTranslated(positions.x[i], positions.y[i], 0);
//            } else {
                gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
//            }
            gl.glBegin(GL_TRIANGLES);
              gl.glLoadName(i);
              gl.glColor3d(red, green, blue); // White
              gl.glVertex3f(0.0f, markerSize, 0.0f);
              gl.glVertex3f(-markerSize, -markerSize, 0.0f);
              gl.glVertex3f(markerSize, -markerSize, 0.0f);
            gl.glEnd();
            gl.glPopMatrix();
            
        }
      }
      
      /*
      // ----- Render a triangle -----
      gl.glTranslatef(-1.5f, 0.0f, -12.0f); // translate left and into the screen
      gl.glBegin(GL_TRIANGLES); // draw using triangles
         gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
         gl.glVertex3f(0.0f, 1.0f, 0.0f);
         gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
         gl.glVertex3f(-1.0f, -1.0f, 0.0f);
         gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
         gl.glVertex3f(1.0f, -1.0f, 0.0f);
      gl.glEnd();

      // ----- Render a Quad -----
      // Translate right, relative to the previous translation
      gl.glTranslatef(3.0f, 0.0f, 0.0f);
      gl.glColor3f(0.5f, 0.5f, 1.0f); // Light-blue
      gl.glBegin(GL_QUADS); // draw using quads
         gl.glColor3f(1.0f, 0.0f, 0.0f);
         gl.glVertex3f(-1.0f, 1.0f, 0.0f);
         gl.glVertex3f(1.0f, 1.0f, 0.0f);
         gl.glVertex3f(1.0f, -1.0f, 0.0f);
         gl.glVertex3f(-1.0f, -1.0f, 0.0f);
      gl.glEnd();
      */
   }

   /** 
    * Called back before the OpenGL context is destroyed. Release resource such as buffers. 
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }
   
   public void addXyz(xyzPos pos) {
       xyzPosList.add(pos);
   }
}
