package mockupdv;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// GL constants
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;

/**
 *
 * @author MichaelH
 */
public class Visualization extends GLJPanel implements GLEventListener {
   // Define constants for the top-level container
//   private static final int CANVAS_WIDTH = 320;  // width of the drawable
//   private static final int CANVAS_HEIGHT = 240; // height of the drawable
//   private static final int FPS = 60; // animator's target frames per second
   
    TextRenderer renderer;
    List<xyzPos> xyzPosList;
    xyzPos positions = null;
    List<Colors> colorList;
    Colors colors = null;
    // ...
    Labeling labels;
    DistanceV dv;
    
    double buckets = 9;
    
    double scale = 1;
    double angle = 0;
    double shiftX = 0;
    double shiftY = 0;
    double shiftZ = 0;
    
    ArrayList<String> cleanLabels = new ArrayList<String>();
    
      
   // Setup OpenGL Graphics Renderer
   
   private GLU glu;  // for the GL Utility
   
   /** Constructor to setup the GUI for this Component */
   public Visualization() {
      this.colorList = new ArrayList<>();
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
      
      Listener ml = new Listener(this); //Zooming & rotating listener
      addMouseListener(ml);
      addMouseMotionListener(ml);
      addMouseWheelListener(ml);
      
      renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 36));
      
      // Generate labels so the coloring method can read
      try {
  		cleanLabels = getLabel();
  	  } catch (FileNotFoundException e) {
  		e.printStackTrace();
  	  } catch (IOException e) {
  		e.printStackTrace();
  	  }
   }

   /**
    * Call-back handler for window re-size event. Also called when the drawable is 
    * first set to visible.
    */
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
            
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
      
      
      if(!xyzPosList.isEmpty()) positions = xyzPosList.get(0);
      if(!colorList.isEmpty()) colors = colorList.get(0);
      
      if(positions!=null && positions.x != null){
                  
        double c = Math.max((positions.maxX - positions.minX)/2,
                            (positions.maxY - positions.minY)/2);
        float markerSize = (float)c/100; //size of the shapes making up the vis
        double tmp1 = c/Math.sin(45.0/180.0*Math.PI);
        Double camDistance = Math.sqrt(tmp1*tmp1 - c*c); // Distance of the camera from the vis
        
        gl.glPushMatrix();
        glu.gluLookAt((positions.centerX - shiftX)*scale + camDistance*Math.sin(angle), //EyeX
                      (positions.centerY - shiftY)*scale,                               //EyeY
                      (positions.centerZ - shiftZ)*scale + camDistance*Math.cos(angle), //EyeZ
                      (positions.centerX - shiftX)*scale, //centerX
                      (positions.centerY - shiftY)*scale, //centerY
                      (positions.centerZ - shiftZ)*scale, //centerZ
                      0, 1, 0); //Up vector
        this.repaint();
                       
        for(int i = 0; i < positions.x.length; i++){
                gl.glPushMatrix();
                gl.glScaled(scale, scale, scale); //essentially the zooming function

    //            if () {           //2D and 3D
    //                gl.glTranslated(positions.x[i], positions.y[i], 0);
    //            } else {
                        gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
    //            }
                    gl.glBegin(GL_TRIANGLES);
                      gl.glLoadName(i);
                      String label = Labeling.dLabels.get(i);
                      System.out.println(label + i);
                      double[] colors = toColor(label);
                      gl.glColor3d(colors[0], colors[1], colors[2]);
                      
                      gl.glVertex3f(0.0f, markerSize, 0.0f);
                      gl.glVertex3f(-markerSize, -markerSize, 0.0f);
                      gl.glVertex3f(markerSize, -markerSize, 0.0f);
                    gl.glEnd();
                gl.glPopMatrix();
        }
      }
        gl.glPopMatrix();

//        if(dv.isDiscrete){
            for(int i=0; i<Labeling.discrete.size(); i++){
//                
//                Object color = Labeling.labelColors.get(Labeling.discrete.get(i));
//                Double R = ((Colors)color).getR(i);
//                Double G = ((Colors)color).getG(i);
//                Double B = ((Colors)color).getB(i);

//                Colors colors = new Colors(Labeling.discrete);
                gl.glPushMatrix();
                gl.glDisable(GL_DEPTH_TEST); //legend
                glu.gluLookAt(0,0,30,
                              0,0,0,
                              0,1,0);
                gl.glTranslated(-17.5, 10-(i*1.7), 0);
                String label = Labeling.discrete.get(i);
                double[] colors =toColor(label);
                
                gl.glBegin(GL_QUADS); // draw using quads
                   gl.glColor3d(colors[0], colors[1], colors[2]);
                   gl.glVertex3f(-1.0f, 1.0f, 0.0f);
                   gl.glVertex3f(1.0f, 1.0f, 0.0f);
                   gl.glVertex3f(1.0f, -0.3f, 0.0f);
                   gl.glVertex3f(-1.0f, -0.3f, 0.0f);
                gl.glEnd();
                
                renderer.beginRendering(this.getWidth(), this.getHeight());
                renderer.draw3D(Labeling.discrete.get(i), 60f, this.getHeight()-(i*30+30), 0f, 0.3f);
                renderer.endRendering();
                
                gl.glEnable(GL_DEPTH_TEST);
                gl.glPopMatrix();
           }
//        } else {
//            gl.glDisable(GL_DEPTH_TEST);
//            glu.gluLookAt(0,0,30,
//                          0,0,0,
//                          0,1,0);
//            gl.glBegin(GL_QUADS);
//                gl.glColor3f(1.0f, 0.0f, 0.0f);
//                gl.glVertex3f(-1.0f, 1.0f, 0.0f);
//                gl.glVertex3f(1.0f, 1.0f, 0.0f);
//                gl.glColor3f(0.0f, 1.0f, 0.0f);
//                gl.glVertex3f(1.0f, -1.0f, 0.0f);
//                gl.glVertex3f(-1.0f, -1.0f, 0.0f);
//            gl.glEnd();
//            gl.glEnable(GL_DEPTH_TEST);
//        }
//        
      

   }
   
   private ArrayList<String> getLabel() throws FileNotFoundException, IOException {
	   ArrayList<String> toReturn = new ArrayList<String>();
	   File f = new File("cleanLabels.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	line = line.replaceAll("[0-9]", "");
		    	line = line.replaceAll("	", "");
		    		toReturn.add(line);
		    	}
		    }
		
		return toReturn;
   }
   
   public static double[] toColor(String input) {
	   try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(input.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        String md5 = sb.toString();
	        double[] toReturn = new double[3];
	        int index = 0;
	        for (int i = 0; i < 21; i+=10) {
	        	String sub = md5.substring(i,i + 10);
	        	sub = sub.replaceAll("[^0-9]", "");
	        	toReturn[index] = Double.parseDouble("0." + sub);
	        	index++;
	        }
	        return toReturn;
	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return new double[3];
	}

   /** 
    * Called back before the OpenGL context is destroyed. Release resource such as buffers. 
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }
   
    public void addXyz(xyzPos pos) {
       xyzPosList.add(pos);
    }
    public void addColor(Colors color) {
       colorList.add(color);
    }
}
