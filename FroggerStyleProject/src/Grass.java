import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Grass{
	private Image forward; //, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 0.6; 		//change to scale image

	public Grass() {
		
		//load main image front or forward view
		forward 	= getImage("/imgs/"+"grass-pixilart.png"); //load the image for Tree
		//backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
	//	left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these for hit box
		width = 600;
		height = 110;
		
		//use for placement in the Jframe
		x = 0;
		y = 450;
		
		//for "hopping" based movement
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	//2nd constructor allows setting x and y
	public Grass(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this();
		
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
		
		
		
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
			URL imageURL = Grass.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
