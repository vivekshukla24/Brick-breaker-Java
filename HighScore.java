package BrickBreaker;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JPanel;

public class HighScore extends JPanel implements KeyListener {
	HighScore()
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
		g.setColor(Color.yellow);
		g.setFont(new Font("serif" , Font.BOLD, 32 ));
		g.drawString("HIGH SCORES", 220, 40);
		g.setColor(Color.orange);
		g.setFont(new Font("serif",Font.BOLD ,25 ));
		g.drawString("RANK", 30, 80);
		g.drawString("NAME", 180, 80);
		g.drawString("Score", 330, 80);
		g.setFont(new Font("serif",Font.BOLD ,21 ));
		g.drawString("Press Backspace for main menu", 410,540);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("serif",Font.BOLD ,21 ));
		try{
			int x, y=80;
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","Prashant","123456"); 
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from brickbreaker order by score desc");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			int k=1;
			while (rs.next()) {
				y=y+40;
				x=40;
				g.drawString(String.valueOf(k),40,y);
			for(int i = 1 ; i <= columnsNumber; i++){
				  	x=x+150;
				  	g.drawString(rs.getString(i), x, y);
			      }           
			  k++;
			  if(k==11){ break;}
			    }
			con.close();  
			  
			}catch(Exception d){ System.out.println(d);}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
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

