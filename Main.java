package BrickBreaker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

 public class Main extends JFrame implements ActionListener {
	public static int life=3;
	public static JFrame f1;
	Gameplay level1 =new Gameplay();
	private JLabel intro,n,prashant,arpit,nitin,by;
	private JButton b,hscore,htplay;
	private JPanel p;
	public static TextField name;
	
	public Main()
	{
		gui();
	}
	public void gui()
	{
		setTitle("Brick Breaker");
		setVisible(true);
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p=new JPanel();
		p.setBackground(Color.DARK_GRAY);
		p.setLayout(null);
		
		b=new JButton("Start");
		b.addActionListener(this);
		b.setFont(new Font("serif", Font.BOLD, 20));
		b.setBounds(150,250, 100, 50);
		
		hscore=new JButton("High Score");
		hscore.addActionListener(this);
		hscore.setFont(new Font("serif", Font.BOLD,20));
		hscore.setBounds(350,250,170,50);
		
		htplay=new JButton("Instructions");
		htplay.addActionListener(this);
		htplay.setFont(new Font("serif", Font.BOLD,20));
		htplay.setBounds(100, 350, 180, 50);
		
		intro=new JLabel("WELCOME TO BRICK BREAKER");
		intro.setBounds(100,10, 450,50);
		intro.setForeground(Color.red);
		intro.setFont(new Font("serif" , Font.BOLD, 28 ));
		
		by= new JLabel("BY ~ ");
		by.setBounds(380, 350, 150, 50);
		by.setForeground(Color.cyan);
		by.setFont(new Font("serif",Font.BOLD, 20));
		
		prashant= new JLabel("PRASHANT (16BCS2930)");
		prashant.setBounds(400, 400, 250, 50);
		prashant.setForeground(Color.cyan);
		prashant.setFont(new Font("serif",Font.BOLD, 20));
		
		arpit= new JLabel("ARPIT (16BCS2918)");
		arpit.setBounds(400, 450, 200, 50);
		arpit.setForeground(Color.cyan);
		arpit.setFont(new Font("serif",Font.BOLD, 20));
		
		nitin= new JLabel("NITIN (16BCS2933)");
		nitin.setBounds(400,500, 200, 50);
		nitin.setForeground(Color.cyan);
		nitin.setFont(new Font("serif",Font.BOLD, 20));
		
		n=new JLabel("Enter Player Name");
		n.setBounds(100, 150, 300, 50);
		n.setForeground(Color.red);
		n.setFont(new Font("serif" , Font.BOLD, 25 ));
		
		name= new TextField();
		name.setBounds(350, 160, 150, 40);
		name.setBackground(Color.yellow);
		name.setFont(new Font("serif", Font.BOLD,25));
		
		p.add(name);
		p.add(b);
		p.add(hscore);
		p.add(htplay);
		p.add(intro);
		p.add(n);
		p.add(by);
		p.add(prashant);
		//p.add(arpit);
		//p.add(nitin);
		
		add(p);
		
	}
	
	public static void main(String args[])
	{
		new Main();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()== "Start")
		{
			int i=0;
			if(name.getText().equals(""))
			{
				JOptionPane.showMessageDialog(name, "Enter the player name");
				
			}else
			{
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
					Connection con=DriverManager.getConnection(""
							+ "jdbc:oracle:thin:@localhost:1521:xe","Prashant","123456"); 
					Statement stmt=con.createStatement();
					String s="Begin Insert into brickbreaker(name,score) values('"+Main.name.getText()+"',0);"+
							"Exception when dup_val_on_index then update brickbreaker set score=0 where name='"+Main.name.getText()+"';end;";
					CallableStatement cs= con.prepareCall(s);
					cs.execute();
					i=1;
					}
					catch(Exception c){
					 System.out.println(c);
					i=0;}
				
				
				
			}
			if(i==1)
			{
				f1= new JFrame();
				f1.setTitle("Brick Breaker");
				f1.setSize(720, 610);
				f1.setLocationRelativeTo(null);
				f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				f1.add(level1);
				f1.setVisible(true);
				  
			}
		}
		if(e.getActionCommand()=="High Score")
		{
			HighScore hs = new HighScore();
			f1= new JFrame();
			f1.setTitle("Brick Breaker");
			f1.setSize(720, 610);
			f1.setLocationRelativeTo(null);
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			f1.add(hs);
			f1.setVisible(true);
		}
		if(e.getActionCommand()=="Instructions")
		{
			Instructions ins = new Instructions();
			f1= new JFrame();
			f1.setTitle("Brick Breaker");
			f1.setSize(720, 610);
			f1.setLocationRelativeTo(null);
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			f1.add(ins);
			f1.setVisible(true);
			
			
		}
		
	}
	
}
