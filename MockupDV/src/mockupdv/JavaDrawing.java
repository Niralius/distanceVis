package mockupdv;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class JavaDrawing extends JPanel
{
	/** The window holding this panel */
	private JFrame m_ParentFrame;
	

	JavaDrawing()
	{
		m_ParentFrame = new JFrame();
		m_ParentFrame.setSize(640, 480);   // Set frame size
		m_ParentFrame.setLocation(50, 50); // Set location on screen
		m_ParentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_ParentFrame.setTitle("Java Drawing Demo");
		
		// Set the properties of this panel
		this.setLocation(0, 0); // Set at upper left corner of the JFrame
		// this.setSize(width, height); // Don't do this, let the JFrame set its size
		m_ParentFrame.getContentPane().add(this);  // Add this to the JFrame
		
		// All drawing in the panel is done in the overridden paint() function below
		m_ParentFrame.setVisible(true);
	} // end constructor
	
	//-------------------------------------------------
	/** Override the paint() function. */
	//-------------------------------------------------
	public void paint(Graphics g)
	{
		// Create a white background for the entire panel
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
//		// Draw 3 filled squares
//		g.setColor(Color.RED);
//		g.fillRect(10, 10, 50, 50);
//		g.setColor(Color.GREEN);
//		g.fillRect(75, 75, 50, 50);
//		g.setColor(Color.BLUE);
//		g.fillRect(140, 140, 50, 50);
//		g.setColor(Color.BLACK);
//		g.drawString("Filled Rectangles", 75, 220);
//		
//		// Draw 3 filled circles
//		g.setColor(Color.RED);
//		g.fillOval(150, 10, 50, 50);
//		g.setColor(Color.GREEN);
//		g.fillOval(215, 75, 50, 50);
//		g.setColor(Color.BLUE);
//		g.fillOval(280, 140, 50, 50);
//		g.setColor(Color.BLACK);
//		g.drawString("Filled Ovals", 215, 220);

//		// Draw 3 rounded rectangles
//		g.setColor(Color.RED);
//		g.fillRoundRect(290, 10, 50, 50, 20, 20);
//		g.setColor(Color.GREEN);
//		g.fillRoundRect(355, 75, 50, 50, 20, 20);
//		g.setColor(Color.BLUE);
//		g.fillRoundRect(420, 140, 50, 50, 20, 20);
//		g.setColor(Color.BLACK);
//		g.drawString("Filled Rounded Rectangles", 355, 220);
		
		// Draw 3 filled arcs
		g.setColor(Color.RED);
		g.fillArc(430, 10, 50, 50, 0, 120);
		g.setColor(Color.GREEN);
		g.fillArc(495, 75, 50, 50, 120, 120);
		g.setColor(Color.BLUE);
		g.fillArc(560, 140, 50, 50, 240, 120);
		g.setColor(Color.BLACK);
		g.drawString("Filled Arcs", 525, 220);
	
//		// Draw 3 squares
//		g.setColor(Color.RED);
//		g.drawRect(10, 230, 50, 50);
//		g.setColor(Color.GREEN);
//		g.drawRect(75, 295, 50, 50);
//		g.setColor(Color.BLUE);
//		g.drawRect(140, 355, 50, 50);
//		g.setColor(Color.BLACK);
//		g.drawString("Rectangles", 75, 435);
//	
//		// Draw 3 circles
//		g.setColor(Color.RED);
//		g.drawOval(150, 230, 50, 50);
//		g.setColor(Color.GREEN);
//		g.drawOval(215, 295, 50, 50);
//		g.setColor(Color.BLUE);
//		g.drawOval(280, 355, 50, 50);
//		g.setColor(Color.BLACK);
//		g.drawString("Ovals", 215, 435);
//
//		// Draw 3 rounded rectangles
//		g.setColor(Color.RED);
//		g.drawRoundRect(290, 230, 50, 50, 20, 20);
//		g.setColor(Color.GREEN);
//		g.drawRoundRect(355, 295, 50, 50, 20, 20);
//		g.setColor(Color.BLUE);
//		g.drawRoundRect(420, 355, 50, 50, 20, 20);
//		g.setColor(Color.BLACK);
//		g.drawString("Rounded Rectangles", 355, 435);
//
//		// Draw 3 arcs
//		g.setColor(Color.RED);
//		g.drawArc(430, 230, 50, 50, 0, 120);
//		g.setColor(Color.GREEN);
//		g.drawArc(495, 295, 50, 50, 120, 120);
//		g.setColor(Color.BLUE);
//		g.drawArc(560, 355, 50, 50, 240, 120);
//		g.setColor(Color.BLACK);
//		g.drawString("Arcs", 525, 435);
		
	} 
	
	//-------------------------------------------------
	/** main */
	//-------------------------------------------------
//	public static void main(String[] args)
//	{
//		JavaDrawing jd = new JavaDrawing();
//	}
}