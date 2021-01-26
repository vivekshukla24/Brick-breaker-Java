package BrickBreaker;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

import javax.swing.*;

public class Gameplay5 extends JPanel implements KeyListener, ActionListener {
	
	public class innerMapgenerator {
		public int map[][];
		public int brickwidth;
		public int brickheight;
		public innerMapgenerator(int row, int col) {
			map= new int[row][col];
			for(int i=0;i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(i==0)
						map[i][j]=3;
					else if(i==3)
						map[i][j]=2;
					else if(j==0 || j==3)
						map[i][j]=1;
					else
						map[i][j]=4;
					
				}
			}
			
			brickwidth = 540/col;
			brickheight = 150/row;
		}
		public void draw(Graphics2D g) {
			for(int i=0;i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j]==3)
						{
							g.setColor(Color.red);
							g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
						}
						else if(map[i][j]==2)
						{
							g.setColor(Color.green);
							g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
						}
						else if(map[i][j]==1)
						{
							g.setColor(Color.white);
							g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
						}
						else if(map[i][j]==4)
						{
							g.setColor(Color.blue);
							g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
						}
							
						
					}
				}
			}
		
		public void setbrickvalue(int value, int row, int col) {
			value--;
			map[row][col] =value;
		}
		
	}
	
	Random randnum = new Random();
	
	private boolean play = false;
	private int totalbricks=16;
	private Timer timer;
	private int score=330;
	private int delay =4;
	private int playerX=310;
	private int ballposX =randnum.nextInt(680);
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	
	private innerMapgenerator map;
	
	public Gameplay5() {
		
		map = new innerMapgenerator(4,4);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1, 692,592);
		//level
		g.setColor(Color.orange);
		g.setFont(new Font("serif" , Font.BOLD, 25 ));
		g.drawString("Level 5", 30, 30);
		//Life
		g.drawString("Life: "+Main.life,200 ,30 );
		//map
		map.draw((Graphics2D)g);
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		//scores
		g.setColor(Color.orange);
		g.setFont(new Font("serif" , Font.BOLD, 25 ));
		g.drawString(""+score, 590, 30);
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalbricks <=0) {
			play =false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD, 30 ));
			g.drawString("Congrats!! You Have Completed the Game! ", 100, 300);
			
			g.setFont(new Font("serif" , Font.BOLD, 20 ));
			g.drawString("THANKS FOR PLAYING", 230, 350);
			g.setFont(new Font("serif" , Font.BOLD, 22 ));
			g.drawString("Final Score: "+score, 280, 390);
			g.drawString("Press ESC to EXIT", 260, 430);
			}
		
		if(ballposY>570) {
			play =false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD, 30 ));
			g.drawString("Level failed!!, Scores: "+score, 190, 300);
			
			g.setFont(new Font("serif" , Font.BOLD, 22 ));
			g.drawString("Press Enter to Restart the Level ", 190, 350);
		}
		g.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
	
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			 A : for( int i=0; i<map.map.length; i++) {
				for(int j=0;j<map.map[0].length; j++) {
					if(map.map[i][j] >0) {
						int brickX= j* map.brickwidth +80;
						int brickY = i* map.brickheight +50;
						int brickwidth = map.brickwidth;
						int brickheight =map.brickheight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballrect = new Rectangle( ballposX, ballposY, 20, 20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setbrickvalue(map.map[i][j],i,j);
							if(map.map[i][j]==0)
								totalbricks--;
							score +=5; 
							
							if(ballposX + 19 <= brickrect.x || ballposX+1 >= brickrect.x + brickrect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
							 break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}
		
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(playerX >=600){
				playerX =600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(playerX < 10){
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play)
			{
				play= true;
				ballposX= randnum.nextInt(680);
				ballposY= 350;
				ballXdir= -1;
				score=330;
				ballYdir= -2;
				playerX = 310;
				totalbricks =16;
				map = new innerMapgenerator(4,4);
				repaint();
				Main.life--;
				if(Main.life==0)
				{
					play=false;
					
					try{
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
					Connection con=DriverManager.getConnection(""
							+ "jdbc:oracle:thin:@localhost:1521:xe","Prashant","123456"); 
					Statement stmt=con.createStatement();
					String s= "Update brickbreaker set score="+score+"where name='"+Main.name.getText()+"'";
					stmt.executeUpdate(s);
					
					}
					catch(Exception c){System.out.println(c);}
					  
				
				Gameplay4.f5.dispose();
				JOptionPane.showMessageDialog(null, "Game Over!! Your Score: "+score);
				}
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_ESCAPE)
		{
			play=false;
			
				try{
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection con=DriverManager.getConnection(""
						+ "jdbc:oracle:thin:@localhost:1521:xe","Prashant","123456"); 
				Statement stmt=con.createStatement();
				String s= "Update brickbreaker set score="+score+"where name='"+Main.name.getText()+"'";
				stmt.executeUpdate(s);
			
				}
				catch(Exception c){System.out.println(c);}
				Gameplay4.f5.dispose();
				JOptionPane.showMessageDialog(null, "Your Score: "+score);
					
		}
		
	}
	public void moveRight() {
		play = true;
		playerX+=40;
	}
	public void moveLeft() {
		play = true;
		playerX-=40;
	}

}

