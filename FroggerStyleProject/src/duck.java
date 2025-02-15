import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class duck{
	private Image forward; //, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.2;		//change to scale image
	double scaleHeight = 1.2; 		//change to scale image

	public duck() {
		
		//load main image front or forward view
		forward 	= getImage("/imgs/"+"duck-pixilart (1).png"); //load the image for Tree
		//backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
	//	left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these for hit box
		width = 24;
		height = 30;
		
		//use for placement in the Jframe
		x = 286;
		y = 500;
		
		//for "hopping" based movement
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	//collision detection with main character class
	public boolean collided (duck character) {
		
		//represent each object as a rectangle
		Rectangle main = new Rectangle(
			character.getX(),
			character.getY(),
			character.getWidth(),
			character.getHeight()
			);
			
		Rectangle thisObject = new Rectangle(x, y, width, height);
			
			
		//user built-in method to check intersection (collision)	
		return main.intersects(thisObject);	
	}
	
	//2nd constructor allows setting x and y
	public duck(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this();
		
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
		
		
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setX(int newX) {
		
		x= newX;
	}
	public void setY(int newY) {
		
		y = newY;
	}
	public int getVX() {
		return vx;
	}
	public void setVX(int newVX) {
		vx = newVX;
	}
	
	
	public void move(int dir) {
		
		switch(dir) {
		
		case 0: //hop up
			y -= height; //move up a body length
			
			
			break;
		case 1: //hop left
			x -= width; // move left
			
			
			
			
			break;
		case 2: //hop right
			x += width;
			
			
			
			
			break;
		case 3: //hop down
			y += height; //move down a body length
			
			
			
			
			break;
		
		}
	}
	
	
	
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update x and y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		init(x,y);
		

			g2.drawImage(forward, tx, null);

			//draw a hit box based on x, y, width, height
			//for collision detection
			//if(Frame.debugging) {
			//	g.setColor(Color.green);
			//	g.drawRect(x, y, width, height);
				
			//}

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = duck.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
