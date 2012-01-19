/**************************************************************
 * 
 * 	Box
 * 
 * 		Recursively draws Boxes centered on the vertices
 * 
 **************************************************************/

package boxes;

import java.awt.Color;
import java.awt.Graphics;

public class Box {
	
	private int x, y, size, depth;
	private Graphics g;
	private Color[] colors; // generated colors for depths
	
	/**
	 * Constuctor
	 * 
	 * @param x - center x position of the box
	 * @param y - center y position of the box
	 * @param size - size of a side of the largest box
	 * @param depth - the recursive depth of the tree
	 */
	public Box(int x, int y, int size, int depth) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.depth = depth;
		
		// set up color range
		colors = new Color[depth];
		for (int i=0;i<depth;i++) {
			int n = (int)(255*(i/((double)depth-1)));
			colors[i] = new Color(n,n,n);
			System.out.println(n);
		}
	}

	
	/**
	 * draw
	 * 		draw the box to the screen recursively
	 * 		
	 * 		an accessor method that calls the real private
	 * 		recursive method
	 * 
	 * @param g - the Graphics object associated with the window
	 */
	public void draw(Graphics g) {
		this.g = g; // save the graphics so it does not need to be passed during recursion
		recursiveDraw(depth, x, y, size);
	}
	
	
	/**
	 * recursiveDraw
	 * 		the actual recursive method
	 * 
	 * @param n - recursive depth
	 * @param a - center x position of the current depth
	 * @param b - center y position of the current depth
	 * @param s - size of a side of the box in the current depth
	 * 
	 * 		g must not be null
	 */
	private void recursiveDraw(int n, int a, int b, int s) {
		if (n <= 0) {
			return;
		}
		
		int halfSize = s / 2;
		
		// coordinates - points are clockwise starting at top left
		int x1 = a - halfSize;
		int y1 = b - halfSize;
		int x2 = a + halfSize;
		int y2 = b - halfSize;
		int x3 = a + halfSize;
		int y3 = b + halfSize;
		int x4 = a - halfSize;
		int y4 = b + halfSize;
		int w = s;
		int h = s;
		
		// middle box
		g.setColor(colors[n-1]);
		g.fillRect(x1, y1, w, h);
		
		// top left box
		recursiveDraw(n-1, x1, y1, halfSize);
		
		// top right box
		recursiveDraw(n-1, x2, y2, halfSize);
		
		// bottom right box
		recursiveDraw(n-1, x3, y3, halfSize);
		
		// bottom left box
		recursiveDraw(n-1, x4, y4, halfSize);
			
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
