/**************************************************************
 * 
 * 	Sierpinski Triangle
 * 
 * 		Recursively draws a Sierpinski Triangle
 * 
 **************************************************************/

package sierpinski;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public class SierpinskiTriangle {
	
	private int x, y, size, depth;
	Point[] verts; // the three vertices of the large triangle in clockwise order from the top
	private Graphics g;
	
	public static final int MAX_DEPTH = 9;
	
	/**
	 * Constuctor
	 * 
	 * @param x - center x position of the Triangle
	 * @param y - center y position of the Triangle
	 * @param size - size of a side of the Triangle
	 * @param depth - the recursive depth
	 */
	public SierpinskiTriangle(int x, int y, int size, int depth) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.depth = depth;
		
		setVertices();
	}

	
	/**
	 * draw
	 * 		draw the Triangle to the screen recursively
	 * 		
	 * 		an accessor method that calls the real private
	 * 		recursive method
	 * 
	 * @param g - the Graphics object associated with the window
	 */
	public void draw(Graphics g) {
		this.g = g; // save the graphics so it does not need to be passed during recursion
		recursiveDraw(depth, verts);
	}
	
	
	/**
	 * recursiveDraw
	 * 		the actual recursive method
	 * 
	 * @param n - recursive depth
	 * @param pts - array of Points that are the vertices of the current triangle
	 * 					arranged in clockwise order from the top
	 * 
	 * 		g must not be null
	 */
	private void recursiveDraw(int n, Point[] pts) {
		g.setColor(Color.black);
		drawTriangle(pts);
		
		if (n == 1) {
			return;
		}
		
		Point[] mids = new Point[3];
		mids[0] = midpoint(pts[0], pts[1]);
		mids[1] = midpoint(pts[1], pts[2]);
		mids[2] = midpoint(pts[2], pts[0]);
		g.setColor(Color.white);
		drawTriangle(mids);
		
		Point[] npts = new Point[3];
		npts[0] = pts[0];
		npts[1] = mids[0];
		npts[2] = mids[2];
		recursiveDraw(n-1, npts);
		
		npts[0] = mids[0];
		npts[1] = pts[1];
		npts[2] = mids[1];
		recursiveDraw(n-1, npts);
		
		npts[0] = mids[2];
		npts[1] = mids[1];
		npts[2] = pts[2];
		recursiveDraw(n-1, npts);
	}

	
	private void drawTriangle(Point[] pts) {
		Polygon tri = new Polygon();
		for (Point p : pts) {
			tri.addPoint(p.x, p.y);
		}
		g.fillPolygon(tri);
	}
	
	
	/**
	 * midpoint
	 * 		find the midpoint between two points
	 * 
	 * @param a - point 1
	 * @param b - point 2
	 * @return midpoint of point 1 and 2
	 */
	private Point midpoint(Point a, Point b) {
		return new Point((int)((a.x+b.x)/2.0), (int)((a.y+b.y)/2.0));
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
	
	
	/**
	 * contains
	 * 		determines if a point is within the triangle
	 * 
	 * @param p - Point to test 
	 * @return true if point is inside the triangle
	 */
	public boolean contains(Point p) {
		// Compute vectors        
		Point2D.Double v0 = new Point2D.Double(verts[2].x-verts[0].x, verts[2].y-verts[0].y);
		Point2D.Double v1 = new Point2D.Double(verts[1].x-verts[0].x, verts[1].y-verts[0].y);
		Point2D.Double v2 = new Point2D.Double(p.x-verts[0].x, p.y-verts[0].y);

		// Compute dot products
		double dot00 = dot(v0, v0);
		double dot01 = dot(v0, v1);
		double dot02 = dot(v0, v2);
		double dot11 = dot(v1, v1);
		double dot12 = dot(v1, v2);

		// Compute barycentric coordinates
		double invDenom = 1.0 / (dot00 * dot11 - dot01 * dot01);
		double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
		double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

		// Check if point is in triangle
		return (u >= 0.0) && (v >= 0.0) && (u + v < 1.0);
	}

	
	/**
	 * dot
	 * 		finds the dot product of two 2d vectors
	 * 
	 * @param v1
	 * @param v2
	 * @return dot product
	 */
	private double dot(Point2D.Double v1, Point2D.Double v2) {
		return v1.x*v2.x + v1.y*v2.y;
	}
	
	
	
	/********************************************
	 * 
	 * Getters and Setters
	 * 
	 *******************************************/
	
	/**
	 * setVertices
	 * 		set the points of the equilateral triangle based on the centerpoint
	 */
	private void setVertices() {
		// set up vertices
		verts = new Point[3];
		int halfSize = size / 2;
		int h = (int)((size/2.0)/Math.sqrt(3.0)); // the distance from the center to the bottom
		
		// top
		verts[0] = new Point(x,y - 2*h);
		
		// right
		verts[1] = new Point(x - halfSize, y + h);
		
		// left
		verts[2] = new Point(x + halfSize, y + h);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		setVertices();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		setVertices();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		setVertices();
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
