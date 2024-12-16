import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	
	//for any debugging code we add
	public static boolean debugging = true;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	Font newFont = new Font("Courier", Font.BOLD, 30);
	int level = 0;
	
	//score and lives variables
	int score = 0;
	int lives = 5;
	String score1 = "Score: ";
	String lives1 = "Lives: ";
	String over = "GAME OVER";
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	duck duck = new duck();
	Grass grass = new Grass();
	Road road = new Road();
	Bread bread = new Bread();
	Grass2 grass2 = new Grass2();
	Grass grass3 = new Grass(0 , 0);
	Broken road2 = new Broken(0, 105);
	EndScreen end = new EndScreen();
	barrier barrier = new barrier(82, 230);
	barrier barrier2 = new barrier(351, 230);
	barrier barrier3 = new barrier(620, 230);
	barrier barrier4 = new barrier(82, 110);
	barrier barrier5 = new barrier(351, 110);
	barrier barrier6 = new barrier(620, 110);
	Barrier2 barrier7 = new Barrier2(341, 170);
	Barrier2 barrier8 = new Barrier2(82, 170);
	Barrier2 barrier9 = new Barrier2(615, 170);
	Win winScreen = new Win();
	//a row of DuckScrolling objects
	F40[] row1 = new F40[10];
	Buga[] row2 = new Buga[10];
	F40[] row3 = new F40[10];
	Platform[] row4 = new Platform[3];
	Platform2[] row5 = new Platform2[3];
	Platform[] row6 = new Platform[3];
	//Laser[] row7 = new Laser[4];
	Laser2[] row8 = new Laser2[4];
	ArrayList<Laser> row7List = new ArrayList<Laser>();
	//frame width/height
	int width = 600;
	int height = 600;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//paint the other objects on the screen
		
		grass3.paint(g);
		
		grass2.paint(g);
		
		bread.paint(g);
		
		grass.paint(g);
		
		road2.paint(g);
		
		road.paint(g);
		
		barrier.paint(g);
		
		barrier2.paint(g);
		
		barrier3.paint(g);
		
		barrier4.paint(g);
		
		barrier5.paint(g);
		
		barrier6.paint(g);
		
		barrier7.paint(g);
		
		barrier8.paint(g);
		
		barrier9.paint(g);
		
		//score and lives
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		g.drawString(score1 + score, 450, 50);
		g.drawString(lives1 + lives, 20, 50);
		
		
		// have the row 1 objects paint on the screen
		for(F40 obj : row1) {
			obj.paint(g);
		}
		
		//have row 2 objects paint on screen
		for(Buga obj : row2) {
			obj.paint(g);
		}
		
		//have row 3 objects paints on screen
		for(F40 obj : row3) {
			obj.paint(g);
		}
		for(Platform obj : row4) {
			obj.paint(g);
		}
		for(Platform2 obj : row5) {
			obj.paint(g);
		}
		for(Platform obj : row6) {
			obj.paint(g);
		}
		for(Laser obj : row7List) {
			obj.paint(g);
		}
		for(Laser2 obj : row8) {
			obj.paint(g);
		}
		//collision detection
		//for each f40 object in row 3
		for(F40 obj : row3) {
			
			if(obj.collided(duck)) {
				duck.setX(286);
				duck.setY(500);
				lives--;
			}
		
		
			if(bread.collided(duck)) {
				obj.setVX(obj.getVX() + (score- (score-1)));
			}
			
		}	
		
		for(Buga obj : row2)	 {
			
			if(obj.collided(duck)) {
				duck.setX(286);
				duck.setY(500);
				lives--;
			}
			if(bread.collided(duck)) {
				obj.setVX(obj.getVX() - (score- (score-1)));
			}
			
		}
		 for(F40 obj : row1) {
			 
			if(obj.collided(duck)) {
				duck.setX(286);
				duck.setY(500);
				lives--;
			 }
			if(bread.collided(duck)) {
				obj.setVX(obj.getVX() + (score- (score-1)));
			}
			
		 }
		 for(Laser obj : row7List) {
			 
			 if(obj.collided(duck)) {
					duck.setX(286);
					duck.setY(500);
					lives--;
				 }
		 }
		 
		 for(Laser2 obj : row8) {
			 
			 if(obj.collided(duck)) {
					duck.setX(286);
					duck.setY(500);
					lives--;
				 }
		 }
		 //platforms/riding
		 boolean riding = false;
		 for(Platform obj : row4) {
				
				if(obj.collided(duck)) {
					duck.setVX(obj.getVX());
					riding = true;
					break;
			}	
		 	if(!riding) {
		 		riding = false;
		 		duck.setVX(0);
		 	}
		 } 	
			
			for(Platform2 obj2 : row5)	 {
				
				if(obj2.collided(duck)) {
					duck.setVX(obj2.getVX());
					riding = true;
					break;
			}	
		 	if(!riding) {
		 		riding = false;
		 		duck.setVX(0);
		 	}
		 }	
		 	
		 	
		 	
			 for(Platform obj3 : row6) {
				 
				if(obj3.collided(duck)) {
					duck.setVX(obj3.getVX());
					riding = true;
					break;
			}	
		 	if(!riding) {
		 		riding = false;
		 		duck.setVX(0);
		 	}
		 }	
		 	
		//collision code
			 duck.paint(g);
		if(bread.collided(duck)) {
			score++;
			duck.setX(286);
			duck.setY(500);
		}
		//win/end screen
		if(lives <= 0) {
			end.paint(g);
		}
		
		if(score >= 5) {
			winScreen.paint(g);
		}
		
		if(duck.getX()> 610) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		
		if(duck.getX() < 0) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		
		if(barrier.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		
		if(barrier2.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		if(barrier3.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		if(barrier4.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		
		if(barrier5.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		if(barrier6.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		if(barrier7.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		
		if(barrier8.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
		if(barrier9.collided(duck)) {
			duck.setX(286);
			duck.setY(500);
			lives--;
		}
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
		
	
		//backgroundMusic.play();

		/*
		 * setup any 1D array here - create the objects that go in them ;)
		 * 
		 * 
		 */
		//traverse the array
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new F40(i*170 , 330);
		}
		
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new Buga(i*161, 385);
		}
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new F40(i*170, 440);
		}
		
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new Platform(i*270, 230);
		}
		for(int i = 0; i < row5.length; i++) {
			row5[i] = new Platform2(i*260, 170);
		}
		for(int i = 0; i < row6.length; i++) {
			row6[i] = new Platform(i*270, 110);
		}
		//for(int i = 0; i < row7.length; i++) {
		//	row7[i] = new Laser(i*175, 200);
		//}
		
		//practice row for ArrayList
		for(int i = 0; i < 4; i++) {
			this.row7List.add(new Laser(i*175, 200));
		}
		
		for(int i = 0; i < row8.length; i++) {
			row8[i] = new Laser2(i*175, 140);
		}
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==87) {
			//move main character up
			duck.move(0);
			
		}else if(arg0.getKeyCode()==83) {
			//move main character down
			duck.move(3);	
			
		}else if(arg0.getKeyCode()==68) {
			duck.move(2);
			
		}else if(arg0.getKeyCode()==65) {
			duck.move(1);
		}
		
		if(arg0.getKeyCode()==82) {
			duck.setX(286);
			duck.setY(500);
			lives += 3;
			score += 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
