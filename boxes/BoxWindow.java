/**************************************************************
 * 
 * 	BoxWindow
 * 
 * 		A window with a centered set of boxes drawn recursively
 * 
 **************************************************************/

package boxes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JFrame;


public class BoxWindow extends JFrame {
	
	private int depth; // recursive depth of the HTree
	private Box box = null;
	
	// double buffering objects
	// used to eliminate flicker when resizing window
    private Image offscreen; 
	private Graphics buffer;
	
	
	/**
	 * Constructor
	 * 
	 * @param width - width of the window
	 * @param height - height of the window
	 * @param depth - recursive depth of the HTree
	 */
	public BoxWindow(int width, int height, int depth) {
		super("BoxWindow");
		this.depth = depth;
		this.setBounds(200, 200, width, height);
		this.setBackground(Color.LIGHT_GRAY);
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
		
		box.draw(buffer);
		
		g.drawImage(offscreen, 0, 0, this);
	}
	
	
	/**
	 * reset
	 *		clear the background, 
	 *		center and scale the tree dependent on the dimensions of the window
	 */
	private void reset() {
		Rectangle b = this.getBounds();
		
		offscreen = createImage(b.width,b.height);
		buffer = offscreen.getGraphics();
		
		buffer.clearRect(0,0,b.width,b.height); 
		
		if (box == null) { // tree not created yet
			box = new Box(b.width / 2, b.height / 2 + 12, 
					(int)(Math.min(b.width, b.height)*0.4), depth);
		} else {
			box.setX(b.width / 2);
			box.setY(b.height / 2 + 12);
			box.setSize((int)(Math.min(b.width, b.height)*0.4));
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
	 * 		create a Box window with an appropriate recursive depth
	 * 
	 * @param args - args[1] is the depth of recursion
	 */
	public static void main(String[] args) {
		
		// set the depth to 3 or the command line argument
		int depth = 3;
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
		
		BoxWindow htwin = new BoxWindow(600, 520, depth);

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

}
