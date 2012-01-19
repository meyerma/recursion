package snowflake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class KochCurve {

	private Point a, b;
	private int depth;
	private Graphics g;
	
	public static final int MAX_DEPTH = 9;
	
	public KochCurve(Point a, Point b, int depth) {
		this.a = a;
		this.b = b;
		this.depth = depth;
	}
	
	/**
	 * draw
	 * 		draw the curve to the screen recursively
	 * 		
	 * 		an accessor method that calls the real private
	 * 		recursive method
	 * 
	 * @param g - the Graphics object associated with the window
	 */
	public void draw(Graphics g) {
		this.g = g; // save the graphics so it does not need to be passed during recursion
		recursiveDraw(depth, a, b);
	}
	
	/**
	 * recursiveDraw
	 * 		the actual recursive method
	 * 
	 * @param n  - recursive depth
	 * @param p1 - endpoint 1 
	 * @param p5 - endpoint 2
	 * 
	 * 		g must not be null
	 */
	private void recursiveDraw(int n, Point p1, Point p5) {
		if (n == 1) {
			//g.setColor(new Color((float)Math.random(),(float) Math.random(),(float) Math.random()));
			g.setColor(Color.blue);
			g.drawLine(p1.x, p1.y, p5.x, p5.y);
			return;
		}
		
		Point p2  = thirdpoint(p1, p5);
		Point p4  = twothirdspoint(p1,p5);
		
		double r = 2.0*(distance(p1, p5))/6.0;
		double angle = Math.atan2(p5.y-p1.y, p5.x-p1.x);
		double sin = Math.sin(Math.PI / 3.0+angle);
		double cos = Math.cos(Math.PI / 3.0+angle);
		
		Point p3 = new Point((int)(p2.x+r*cos), (int)(p2.y+r*sin));
		
		recursiveDraw(n-1, p1, p2);
		recursiveDraw(n-1, p2, p3);
		recursiveDraw(n-1, p3, p4);
		recursiveDraw(n-1, p4, p5);
	}
	
	
	/**
	 * thirdpoint
	 * 		find the point one third of the way between two points
	 * 
	 * @param a - point 1
	 * @param b - point 2
	 * @return 1/3 point of a and b
	 */
	private Point thirdpoint(Point a, Point b) {
		return new Point((int)(a.x+(b.x-a.x)/3.0), (int)(a.y+(b.y-a.y)/3.0));
	}
	
	/**
	 * twothirdspoint
	 * 		find the point two thirds between two points
	 * 
	 * @param a - point 1
	 * @param b - point 2
	 * @return 2/3 point of a and b
	 */
	private Point twothirdspoint(Point a, Point b) {
		return new Point((int)(a.x+2.0*(b.x-a.x)/3.0), (int)(a.y+2.0*(b.y-a.y)/3.0));
	}
	
	private double distance(Point p1, Point p2) {
		return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
	}
	
	/**
	 * nextDepth
	 * 		cycles the depth of the triangle
	 */
	public void nextDepth() {
		depth++;
		if (depth > MAX_DEPTH) {
			depth = 1;
		}
	}
	
	/********************************************
	 * 
	 * Getters and Setters
	 * 
	 *******************************************/

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
