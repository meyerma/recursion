/**************************************************************
 * 
 * 	SierpinskiWindow
 * 
 * 		A window with a centered Sierpinski Triangle
 * 
 **************************************************************/

package sierpinski;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class SierpinskiWindow extends JFrame implements MouseListener {
	
	private int depth; // recursive depth of the HTree
	private SierpinskiTriangle tri = null;
	
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
	public SierpinskiWindow(int width, int height, int depth) {
		super("Sierpinski Triangle | Depth "+depth);
		this.depth = depth;
		this.setBounds(200, 200, width, height);
		this.setBackground(Color.LIGHT_GRAY);
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
		tri.draw(buffer);
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
		
		if (tri == null) { // tree not created yet
			tri = new SierpinskiTriangle(b.width / 2, b.height / 2 + (int) (b.height*0.16), 
					(int)(Math.min(b.width, b.height)*0.9), depth);
		} else {
			tri.setX(b.width / 2);
			tri.setY(b.height / 2 + (int) (b.height*0.16));
			tri.setSize((int)(Math.min(b.width, b.height)*0.9));
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
		int depth = 1;
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
		
		SierpinskiWindow htwin = new SierpinskiWindow(600, 520, depth);

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
		if (tri.contains(e.getPoint())) {
			tri.nextDepth();
			this.setTitle("Sierpinski Triangle | Depth "+ tri.getDepth());
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
