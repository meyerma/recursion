/**************************************************************
 * 
 * 	KochCurveWindow
 * 
 * 		A window with a centered Koch Snowflake
 * 
 **************************************************************/

package snowflake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class KochCurveWindow extends JFrame implements MouseListener {
	
	private int sides;
	private int depth; // recursive depth of the HTree
	private KochCurve[] koch = null;
	
	public static final int MAX_SIDES = 10;
	
	// double buffering objects
	// used to eliminate flicker when resizing window
    private Image offscreen; 
	private Graphics buffer;
	
	
	/**
	 * Constructor
	 * 
	 * @param width - width of the window
	 * @param height - height of the window
	 * @param depth - recursive depth of the Triangle
	 */
	public KochCurveWindow(int sides, int width, int height, int depth) {
		super("Koch Curve | Depth "+depth+" | Sides "+sides);
		this.sides = sides;
		this.depth = depth;
		this.setBounds(200, 200, width, height);
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	/**
	 * paint
	 * 		draws the contents of the window
	 * 
	 * 		called automatically when the window needs updating
	 */
	@Override
	public void paint(Graphics g) {
		reset();
		for (KochCurve curve: koch) {
			curve.draw(buffer);
		}
		g.drawImage(offscreen, 0, 0, this);
	}
	
	
	/**
	 * reset
	 *		clear the background, 
	 *		center and scale the triangle dependent on the dimensions of the window
	 */
	private void reset() {
		Rectangle b = this.getBounds();
		
		offscreen = createImage(b.width,b.height);
		buffer = offscreen.getGraphics();
		
		buffer.clearRect(0,0,b.width,b.height); 
		
		Point p1 = new Point(100, 100);
		Point p2 = new Point(100, 400);
		
		if (koch == null) { // curve not created yet
			
			createKochCurves(depth);
		} else {
			/*koch.setX(b.width / 2);
			koch.setY(b.height / 2 + (int) (b.height*0.16));
			koch.setSize((int)(Math.min(b.width, b.height)*0.9));*/
		}
	}
	
	
	private void createKochCurves(int newDepth) {
		Point[] pts = new Point[sides];
		Rectangle b = this.getBounds();
		Point c = new Point((int)(b.width/2), (int)(b.height/2+12));
		for (int i=0;i<sides;i++) {
			double r = 200;
			double sin = Math.sin(i * 2.0*Math.PI / sides - Math.PI/2.0);
			double cos = Math.cos(i * 2.0*Math.PI / sides - Math.PI/2.0);
			
			pts[i] = new Point((int)(c.x+r*cos), (int)(c.y+r*sin));
		}
		
		koch = new KochCurve[sides];
		for (int i=0, j=1;i<sides;i++,j++) {
			if (j == sides) {
				j = 0;
			}
			
			koch[i] = new KochCurve(pts[j],pts[i], newDepth);
		}
	}
	
	/**
	 * update
	 * 		paint the window on each update,
	 * 		necessary for double buffering
	 */
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	
	/**
	 * main
	 * 		create a Sierpinski window with an appropriate recursive depth
	 * 
	 * @param args - args[1] is the depth of recursion
	 */
	public static void main(String[] args) {
		
		// set the depth to 3 or the command line argument
		int depth = 2;
		if (args.length > 1) {
			try {
				depth = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				quit(args[1]);
			}
			if (depth < 1) {
				quit(args[1]);
			}
		}
		
		KochCurveWindow htwin = new KochCurveWindow(3, 600, 520, depth);

	}
	
	/**
	 * quit
	 * 		exit the program if an invalid depth was entered
	 * 
	 * @param entry - the String the user typed at the command line
	 */
	private static void quit(String entry) {
		System.out.println(entry + " is not a valid depth.");
		System.exit(0);
	}


	/********************************************
	 * 
	 * Mouse Listener Methods
	 * 
	 *******************************************/
	
	/** 
	 * mousePressed
	 * 		makes the triangle change recursion depth when clicked
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) { // left click
			for (KochCurve curve : koch) {
				curve.nextDepth();
			}
			
			this.setTitle("Koch Curve | Depth "+ koch[0].getDepth() + " | Sides "+sides);
			repaint();
		} else if (e.getButton() == 3) { // right click
			sides++;
			if (sides > MAX_SIDES) {
				sides = 3;
			}
			createKochCurves(koch[0].getDepth());
			this.setTitle("Koch Curve | Depth "+ koch[0].getDepth() + " | Sides "+sides);
			repaint();
		}
		
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
