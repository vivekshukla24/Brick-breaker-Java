package BrickBreaker;


import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Instructions extends JPanel implements KeyListener {
	Instructions()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	
	public void paint(Graphics g)
	{
		 
		
			
			//background
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0, 720,610);
			
			//content
			g.setColor(Color.yellow);
			g.setFont(new Font("serif" , Font.BOLD, 32 ));
			g.drawString("INSTRUCTIONS", 220, 40);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("serif",Font.BOLD ,21 ));
			g.drawString("The Objective of this game is to destroy as many bricks as possible by", 10, 80);
			g.drawString("bouncing the ball into them with the paddle. If it destroy the brick then you ", 10, 103);
			g.drawString("will gain 5 points for each. If you clear the bricks in the level then you ",10,126);
			g.drawString("proceed to next level.There will be only three life to complete the game" , 10,149);
			g.drawString("For a specific coloured brick there is a number of hit is predefined to ", 10,172);
			g.drawString("completely destroy it like:- ", 10, 195);
			g.drawString("4 hits", 150,250);
			g.drawString("3 hits", 150,290);
			g.drawString("2 hits", 150,330);
			g.drawString("1 hit", 150,370);
			g.setColor(Color.ORANGE);
			g.drawString("Press Backspace for main menu", 410,540);
			
			g.setColor(Color.blue);
			g.fillRect(20, 230, 80, 30);
			g.setColor(Color.red);
			g.fillRect(20, 270, 80, 30);
			
			g.setColor(Color.green);
			g.fillRect(20, 310, 80, 30);
			g.setColor(Color.white);
			g.fillRect(20, 350, 80, 30);
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD ,25 ));
			g.drawString("CONTROLS:", 15, 420);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("serif",Font.BOLD ,18 ));
			g.drawString("Left Arrow = Move Left", 20, 441);
			g.drawString("Right Arrow = Move Right", 20, 464);
			g.drawString("Space = Next Level (When prompted)", 20, 487);
			g.drawString("Enter = Restart the level (When prompted)", 20, 510);
			g.drawString("Press ESC any time to quit and save the game.", 20, 533);
			

}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
		{
			Main m= new Main();
			Main.f1.dispose();
		}
		
	}
}

