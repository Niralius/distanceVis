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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.awt.TextRenderer;

/**
 *
 * @author MichaelH
 */
public class Visualization extends GLJPanel implements GLEventListener {
	// Define constants for the top-level container
	// private static final int CANVAS_WIDTH = 320; // width of the drawable
	// private static final int CANVAS_HEIGHT = 240; // height of the drawable
	// private static final int FPS = 60; // animator's target frames per second

	TextRenderer renderer;
	static List<xyzPos> xyzPosList;
	xyzPos positions = null;
	// List<Colors> colorList;
	// Colors colors = null;
	// ...
	static Labeling labels;
	DistanceV dv;
	static Listener ml;

	double buckets = 9;

	static double scale = 1;
	static double angle = 0;
	static double shiftX = 0;
	static double shiftY = 0;
	static double shiftZ = 0;

	static HashMap<String, double[]> colorCodes = new HashMap<String, double[]>(); // caching
																					// purposes
	static ArrayList<String> toIgnore = new ArrayList<String>();
	static HashMap<String, Integer> positionController = new HashMap<String, Integer>();
	static String selectedPositionName = "";
	static HashMap<String, Labeling> allTheLabels = new HashMap<String, Labeling>();
	static String[] continuousLabelsToShow = new String[3];
	static LinkedHashMap<Double, Double[]> cLabelColors = new LinkedHashMap<Double, Double[]>();
	static boolean outlineEnabled = false;

	// Setup OpenGL Graphics Renderer

	private GLU glu; // for the GL Utility

	/** Constructor to setup the GUI for this Component */
	public Visualization() {
		// this.colorList = new ArrayList<>();
		this.xyzPosList = new ArrayList<>();
		this.addGLEventListener(this);
	}

	// ------ Implement methods declared in GLEventListener ------

	/**
	 * Called back immediately after the OpenGL context is initialized. Can be
	 * used to perform one-time initialization. Run only once.
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context
		glu = new GLU(); // get GL Utilities
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f); // set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL_LEQUAL); // the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best
																// perspective
																// correction
		gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out
									// lighting

		ml = new Listener(this); // Zooming & rotating listener
		addMouseListener(ml);
		addMouseMotionListener(ml);
		addMouseWheelListener(ml);

		renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 36));
	}

	/**
	 * Call-back handler for window re-size event. Also called when the drawable
	 * is first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context

		if (height == 0)
			height = 1; // prevent divide by zero
		float aspect = (float) width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.01, 1000000.0); // fovy, aspect,
															// zNear, zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}

	/**
	 * Called back by the animator to perform rendering.
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color
																// and depth
																// buffers
		gl.glLoadIdentity(); // reset the model-view matrix
		if (!xyzPosList.isEmpty())
			positions = xyzPosList.get(positionController.get(selectedPositionName));
		// if(!colorList.isEmpty()) colors = colorList.get(0);

		if (positions != null && positions.x != null) {

			double c = Math.max((positions.maxX - positions.minX) / 2, (positions.maxY - positions.minY) / 2);
			float markerSize = (float) c / 100; // size of the shapes making up
												// the vis
			double tmp1 = c / Math.sin(45.0 / 180.0 * Math.PI);
			Double camDistance = Math.sqrt(tmp1 * tmp1 - c * c); // Distance of
																	// the
																	// camera
																	// from the
																	// vis

			gl.glPushMatrix();
			glu.gluLookAt((positions.centerX - shiftX) * scale + camDistance * Math.sin(angle), // EyeX
					(positions.centerY - shiftY) * scale, // EyeY
					(positions.centerZ - shiftZ) * scale + camDistance * Math.cos(angle), // EyeZ
					(positions.centerX - shiftX) * scale, // centerX
					(positions.centerY - shiftY) * scale, // centerY
					(positions.centerZ - shiftZ) * scale, // centerZ
					0, 1, 0); // Up vector
			this.repaint();
			for (int i = 0; i < positions.x.length; i++) {
				if (!labels.isContinuous) {
					String label = labels.dLabels.get(i);

					if (!toIgnore.contains(label)) { // Seeing if point is on
														// the ignore list
						gl.glPushMatrix();
						gl.glScaled(scale, scale, scale); // essentially the
															// zooming function

						// if () { //2D and 3D
						// gl.glTranslated(positions.x[i], positions.y[i], 0);
						// } else {
						gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
						// }
						gl.glBegin(GL_TRIANGLES);
						gl.glLoadName(i);

						// Veeery expensive circles. Comment out {glBegin,
						// glLoadName, glVertex3f}
						// GLUquadric point = glu.gluNewQuadric();
						// glu.gluSphere(point, 0.05, 3, 3); // Adjust last two
						// parameters based on performance. Higher = more
						// expensive

						// Getting color data if it exists, else creating color
						// data
						double[] colors = new double[3];
						if (colorCodes.containsKey(label)) {
							colors = colorCodes.get(label);
						} else {
							// New label
							colors = toColor(label);
							colorCodes.put(label, colors); // caching
						}

						gl.glColor3d(colors[0], colors[1], colors[2]);

						gl.glVertex3f(0.0f, markerSize, 0.0f);
						gl.glVertex3f(-markerSize, -markerSize, 0.0f);
						gl.glVertex3f(markerSize, -markerSize, 0.0f);

						gl.glEnd();
						gl.glPopMatrix();
						if (outlineEnabled) { // Wireframe anybody?
							gl.glPushMatrix();
							gl.glScaled(scale, scale, scale);

							gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
							gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
							gl.glBegin(GL_TRIANGLES);
							gl.glLoadName(i);

							gl.glColor3d(0, 0, 0);

							gl.glVertex3f(0.0f, markerSize, 0.0f);
							gl.glVertex3f(-markerSize, -markerSize, 0.0f);
							gl.glVertex3f(markerSize, -markerSize, 0.0f);

							gl.glEnd();
							gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
							gl.glPopMatrix();
						}
					}
				}

				else {
					// Is Continuous.
					for (int contI = 0; contI < continuousLabelsToShow.length; contI++) {
						try {
							List<Double> colors = allTheLabels.get(continuousLabelsToShow[contI]).cLabels;
							gl.glPushMatrix();
							gl.glScaled(scale, scale, scale); // essentially
																// the
																// zooming
																// function

							gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
							gl.glBegin(GL_TRIANGLES);
							gl.glLoadName(i);

							// Veeery expensive circles
							// GLUquadric point = glu.gluNewQuadric();
							// glu.gluSphere(point, 0.05, 16, 16);

							// Time to get them colors...
							Double toAdd = colors.get(i);
							Double[] cLabelColorVals = new Double[] { 0.0, 0.0, 0.0 };

							if (!cLabelColors.containsKey(toAdd)) {
								for (int col = 0; col < 3; col++) {
									double colorVal;
									try {
										double min = allTheLabels.get(continuousLabelsToShow[col]).contMin;
										double max = allTheLabels.get(continuousLabelsToShow[col]).contMax;
										double intermid = max - min;
										colorVal = (toAdd / intermid) - (min / intermid);
										// colorVal = (toAdd - min) / (max -
										// min);
									} catch (Exception e) {
										colorVal = 0;
									}
									cLabelColorVals[col] = colorVal;
									cLabelColors.put(toAdd, cLabelColorVals);
								}
							} else {
								cLabelColorVals = cLabelColors.get(toAdd);
							}
							gl.glColor3d(cLabelColorVals[0], cLabelColorVals[1], cLabelColorVals[2]);
							gl.glVertex3f(0.0f, markerSize, 0.0f);
							gl.glVertex3f(-markerSize, -markerSize, 0.0f);
							gl.glVertex3f(markerSize, -markerSize, 0.0f);
							gl.glEnd();
							gl.glPopMatrix();
							if (outlineEnabled) { // Wireframe anybody?
								gl.glPushMatrix();
								gl.glScaled(scale, scale, scale);

								gl.glTranslated(positions.x[i], positions.y[i], positions.z[i]);
								gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
								gl.glBegin(GL_TRIANGLES);
								gl.glLoadName(i);

								gl.glColor3d(0, 0, 0);

								gl.glVertex3f(0.0f, markerSize, 0.0f);
								gl.glVertex3f(-markerSize, -markerSize, 0.0f);
								gl.glVertex3f(markerSize, -markerSize, 0.0f);

								gl.glEnd();
								gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
								gl.glPopMatrix();
							}

						} catch (NullPointerException | IndexOutOfBoundsException e) {
							// User left the middle box blank...
						}
					}
				}

			}
			gl.glPopMatrix();

			// Legend(ary) stuff
			// if(dv.isDiscrete){
			try

			{
				if (!labels.isContinuous) {
					for (int i = 0; i < labels.discrete.size(); i++) {
						//
						// Object color =
						// Labeling.labelColors.get(Labeling.discrete.get(i));
						// Double R = ((Colors)color).getR(i);
						// Double G = ((Colors)color).getG(i);
						// Double B = ((Colors)color).getB(i);

						// Colors colors = new Colors(Labeling.discrete);
						gl.glPushMatrix();
						gl.glDisable(GL_DEPTH_TEST); // legend
						glu.gluLookAt(0, 0, 30, 0, 0, 0, 0, 1, 0);
						gl.glTranslated(-17.5, 10 - (i * 1.7), 0);

						double[] colors = colorCodes.get(labels.discrete.get(i));

						gl.glBegin(GL_QUADS); // draw using quads
						gl.glColor3d(colors[0], colors[1], colors[2]);
						gl.glVertex3f(-1.0f, 1.0f, 0.0f);
						gl.glVertex3f(1.0f, 1.0f, 0.0f);
						gl.glVertex3f(1.0f, -0.3f, 0.0f);
						gl.glVertex3f(-1.0f, -0.3f, 0.0f);
						gl.glEnd();

						renderer.beginRendering(this.getWidth(), this.getHeight());
						renderer.draw3D(labels.discrete.get(i), 60f, this.getHeight() - (i * 30 + 30), 0f, 0.3f);
						renderer.endRendering();

						gl.glEnable(GL_DEPTH_TEST);
						gl.glPopMatrix();
					}
				} else if (labels.isContinuous) {
					// Is continuous
					for (int i = 0; i < continuousLabelsToShow.length; i++) {
						Labeling cLabel = allTheLabels.get(continuousLabelsToShow[i]);
						List<Double> data = cLabel.cLabels;

						// show labels! Colors are stored in a.. hashmap.
						gl.glPushMatrix();
						gl.glDisable(GL_DEPTH_TEST); // legend
						glu.gluLookAt(0, 0, 30, 0, 0, 0, 0, 1, 0);
						gl.glTranslated(-17.5 + (3 * i), 10, 0);
						double r = 0, g = 0, b = 0;
						switch (i) {
						case 0:
							r = 1;
							break;
						case 1:
							g = 1;
							break;
						case 2:
							b = 1;
							break;
						}
						gl.glBegin(GL_QUADS); // draw using quads
						gl.glColor3d(0, 0, 0);
						gl.glVertex3f(-1.0f, 1.0f, 0.0f);
						gl.glVertex3f(1.0f, 1.0f, 0.0f);
						gl.glColor3d(r, g, b);
						gl.glVertex3f(1.0f, -20f, 0.0f);
						gl.glVertex3f(-1.0f, -20f, 0.0f);
						gl.glEnd();

						renderer.beginRendering(this.getWidth(), this.getHeight());
						renderer.draw3D(String.format("%.3f", cLabel.contMin), -17.5f + (3 * i),
								(float) (10 - (i * 1.7)), 0f, 0.3f);
						renderer.endRendering();

						gl.glEnable(GL_DEPTH_TEST);
						gl.glPopMatrix();
					}
				}
			} catch (

			NullPointerException e)

			{
				// e.printStackTrace();
			}
		}

		// } else {
		// gl.glDisable(GL_DEPTH_TEST);
		// glu.gluLookAt(0,0,30,
		// 0,0,0,
		// 0,1,0);
		// gl.glBegin(GL_QUADS);
		// gl.glColor3f(1.0f, 0.0f, 0.0f);
		// gl.glVertex3f(-1.0f, 1.0f, 0.0f);
		// gl.glVertex3f(1.0f, 1.0f, 0.0f);
		// gl.glColor3f(0.0f, 1.0f, 0.0f);
		// gl.glVertex3f(1.0f, -1.0f, 0.0f);
		// gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		// gl.glEnd();
		// gl.glEnable(GL_DEPTH_TEST);
		// }
		//
	}

	public static double[] toColor(String input) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			String md5 = sb.toString();
			double[] toReturn = new double[3];
			for (int i = 0; i < 21; i += 10) {
				String sub = md5.substring(i, i + 10);

				// This gives more colorful results
				sub = sub.replaceAll("[^0-9]", "");
				toReturn[i / 10] = Double.parseDouble("0." + sub);

				// This might guarantee more distinction
				// int sum = 0;
				// for (int a = 0; a < sub.length(); a++) {
				// sum += (int) sub.charAt(a);
				// }
				// toReturn[i / 10] = Double.parseDouble("0." + sum);
			}
			return toReturn;
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return new double[3];
	}

	/**
	 * Called back before the OpenGL context is destroyed. Release resource such
	 * as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	public void addXyz(xyzPos pos) {
		xyzPosList.add(pos);
	}

	// public void addColor(Colors color) {
	// colorList.add(color);
	// }
}
