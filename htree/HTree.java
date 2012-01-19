/**************************************************************
 * 
 * 	HTree
 * 
 * 		Recursively draws an HTree
 * 
 **************************************************************/

package htree;

import java.awt.Graphics;

public class HTree {
	
	private int x, y, length, depth;
	private Graphics g;
	
	/**
	 * Constuctor
	 * 
	 * @param x - center x position of the htree
	 * @param y - center y position of the htree
	 * @param length - length of a line in the largest h in the tree
	 * @param depth - the recursive depth of the tree
	 */
	public HTree(int x, int y, int length, int depth) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.depth = depth;
	}

	
	/**
	 * draw
	 * 		draw the htree to the screen recursively
	 * 		
	 * 		an accessor method that calls the real private
	 * 		recursive method
	 * 
	 * @param g - the Graphics object associated with the window
	 */
	public void draw(Graphics g) {
		this.g = g; // save the graphics so it does not need to be passed during recursion
		recursiveDraw(depth, x, y, length);
	}
	
	
	/**
	 * recursiveDraw
	 * 		the actual recursive method
	 * 
	 * @param n - recursive depth
	 * @param a - center x position of the current depth
	 * @param b - center y position of the current depth
	 * @param len - length of a line in the current depth
	 * 
	 * 		g must not be null
	 */
	private void recursiveDraw(int n, int a, int b, int len) {
		if (n <= 0) {
			return;
		}
		
		int halfLength = len / 2;
		
		// horizontal line
		int x1 = a - halfLength;
		int y1 = b;
		int x2 = a + halfLength;
		int y2 = b;
		g.drawLine(x1, y1, x2, y2);
		
		// vertical left line
		y1 = b - halfLength;
		x2 = x1;
		y2 = b + halfLength;
		g.drawLine(x1, y1, x2, y2);
		
		// recurse left side
		recursiveDraw(n-1, x1, y1, halfLength);
		recursiveDraw(n-1, x2, y2, halfLength);
		
		// vertical right line
		x1 = a + halfLength;
		x2 = x1;
		g.drawLine(x1, y1, x2, y2);
		
		// recurse left side
		recursiveDraw(n-1, x1, y1, halfLength);
		recursiveDraw(n-1, x2, y2, halfLength);
			
	}

	
	/********************************************
	 * 
	 * Getters and Setters
	 * 
	 *******************************************/
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
