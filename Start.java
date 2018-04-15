/*
 * MADE BY : VISHAL SINGH
 * ROLL NO : 1209710121
 * BRANCH  : CS-B
 * YEAR    : 3rd (THIRD)
 * COLLEGE : GALGOTIA COLLEGE OF ENGGENERING AND TECHNOLOGY
 */

/*
 * STARTING CLASS - CONROLLING ALL OTHER CLASSES
 */

/*
<html>
<body>
<applet code="Start.class" height=620 width=1180>
</applet>
</body>
</html>
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")									// Suppression of warning
public class Start extends Applet implements KeyListener,Runnable,ActionListener 
{
	static int Level=0;									// Level is different from level
	static int flag=0,flag1=0,flag2=1,flag3=1;						// flag2 is for falling power1 active once
												// flag3 is for falling power2 active once
												// flag1 is for pause in starting of game
												// flag is for starting a thread if different level selected in beginning
	int fps=50;
	static int no_of_bullet=6;
												//Double buffering variable
	Image dbImage;
	Graphics dbGraphics;
	
	God_Mode gm;
	
	Thread th=new Thread(this);
										/*
										 * running1, running2, running3 is for pause and resume function
										 * running is always true, so that thread always run until exit press
										 */
	
	boolean running1=false;									// for Level 1 running
	boolean running2=false;									// for Level 2 running
	boolean running3=false;									// for Level 3 running
	boolean running=true;									//for run()
	static boolean power=false;								// for gun
	static boolean power1=false;								// for +1 ball
	static boolean bullet=false;								// if true then only bullet show on space bar pressing
	
	Paddle_Level1 paddle1;
	Paddle_Level2 paddle2;
	Paddle_Level3 paddle3;
	Ball ball;
	Score s;
	
	ArrayList<Blocks>blocks=new ArrayList<Blocks>();					// Array list of blocks
																					// Locations of blocks
	private String level[] = {"",								// Where ever 'b' comes block will appear
			  				  "",
			  				  "",
			  				  "",
			  				  "",
							  "",
							  "",
							  "          b    b    b    b    b    b    b    b    b    b    b",
							  "",
							  "             b    b    b    b    b    b    b    b    b    b",
							  "",
							  "          b    b    b    b    b    b    b    b    b    b    b",
							  "",
							  "             b    b    b    b    b    b    b    b    b    b",
							  "",
							  "          b    b    b    b    b    b    b    b    b    b    b",
							  "",
							  "             b    b    b    b    b    b    b    b    b    b",
							  "",
							  "          b    b    b    b    b    b    b    b    b    b    b",
							  "",
							  "             b    b    b    b    b    b    b    b    b    b",
							  "",
							  "          b    b    b    b    b    b    b    b    b    b    b",
							  "",
							  "",
							  "",
							  ""
							};
	
//	private String level[] = {"","","","","","","","",""," b"};						// Testing
	
	public void init()
	{
		menu();
		JOptionPane.showMessageDialog(null," Select New Game from Game tab.");
		addKeyListener(this);											
		setFocusable(true);										//focusing keylistener
		
		s=new Score();
		ball=new Ball(getSize().width/2+60,getSize().height-32);
		ball=new Ball(getSize().width/2+60,getSize().height-32);
		ball=new Ball(getSize().width/2+60,getSize().height-32);
		paddle1=new Paddle_Level1(getSize().width/2,getSize().height-32);
		paddle2=new Paddle_Level2(getSize().width/2,getSize().height-32);
		paddle3=new Paddle_Level3(getSize().width/2,getSize().height-32,26,getSize().height/2);
		
		for(int y=0; y<level.length; y++)								// Creating new blocks
		{
			for(int x=0; x<level[y].length();x++)
			{
				if(level[y].charAt(x) == 'b')
					blocks.add(new Blocks(x*16,y*16));
			}
		}
	}
	public void start()
	{
		repaint();
	}
	public void run()
	{
		while(running)											// always true until exit click
		{
			
		if(Level==1)
		{
			while(running1)										
			{
				if(no_of_bullet<=0)								// Disabling the power
				{
					power=false;
					Ball.power_fall1=false;
				}
				ball.move(getSize(),paddle1);							// Ball will move
				paddle1.move(getSize().width);							// Paddle will move
				block_left(blocks);								// Check game is over or not
				ball.fire();									// If power, then paddle fire
				ball.drop_power();								// If power block hit, then power fall
				if(God_Mode.god_mode == 1)						// if god mode active then paddle move automatically
				{
					paddle1.move_byGod(getSize().width);
				}
				
				for(int i=0;i< blocks.size();i++)
				{
					ball.checkCollision(blocks.get(i),i);					// If ball strike block, it get destroy
					ball.checkBulletCollison(blocks.get(i));				// If bullet strike block, it get destroy
					
					if(!blocks.get(i).alive)
					{
						blocks.remove(i);
					}
				}
			
				repaint();
				try
				{
					Thread.sleep(1000/fps);
				}
				catch(InterruptedException e)
				{
					JOptionPane.showMessageDialog(null," SORRY !!\n Game crashed. Open again.");
				}
				if(flag1==0)										// Game only start after R or left-right arrow clicked
				{
					running1=false;
					flag1=1;
				}
			}
		}
		
		if(Level==2)
		{
			while(running2)
			{
				if(no_of_bullet<=0)
				{
					power=false;
					Ball.power_fall1=false;
				}
				ball.move(getSize(),paddle2);
				paddle2.move(getSize().width);
				block_left(blocks);
				ball.drop_power();
				ball.fire();
				if(God_Mode.god_mode == 1)
				{
					paddle2.move_byGod(getSize().width);
				}
				
				for(int i=0;i< blocks.size();i++)
				{
					ball.checkCollision(blocks.get(i),i);
					ball.checkBulletCollison(blocks.get(i));
					
					if(!blocks.get(i).alive)
					{
						blocks.remove(i);
					}
				}
			
				repaint();
				try
				{
					Thread.sleep(1000/fps);
				}
				catch(InterruptedException e)
				{
					JOptionPane.showMessageDialog(null," SORRY !!\n Game crashed. Open again.");
				}
				if(flag1==0)
				{
					running2=false;
					flag1=1;
				}
			}
		}
		
		if(Level==3)
		{
			while(running3)
			{
				if(no_of_bullet<=0)
				{
					power=false;
					Ball.power_fall1=false;
				}
				ball.move(getSize(),paddle3);
				paddle3.move(getSize().width,getSize().height);
				block_left(blocks);
				ball.drop_power();
				ball.fire();
				if(God_Mode.god_mode == 1)
				{
					paddle3.move_byGod(getSize().width);
				}
				
				for(int i=0;i< blocks.size();i++)
				{
					ball.checkCollision(blocks.get(i),i);
					ball.checkBulletCollison(blocks.get(i));
					
					if(!blocks.get(i).alive)
					{
						blocks.remove(i);
					}
				}
			
				repaint();
				try
				{
					Thread.sleep(1000/fps);
				}
				catch(InterruptedException e)
				{
					JOptionPane.showMessageDialog(null," SORRY !!\n Game crashed. Open again.");
				}
				if(flag1==0)
				{
					running3=false;
					flag1=1;
				}
			}
		}
		}
	}
	
	public void block_left(ArrayList<Blocks> b)							//for exit of game
	{
		int f=0;
		for(int i=0;i< b.size();i++)
		{
			if(b.get(i).alive)
			{
				f=1;									// Blocks are left
			}
		}
		if(f==0)										// No blocks left
		{
			if(Level==1)
			{
				if(Score.score > Score.high_score1)
				{
					JOptionPane.showMessageDialog(null,"CONGRATULATION\n You made a new high score : "+Score.score);
					System.exit(0);
				}
			}
			if(Level==2)
			{
				if(Score.score > Score.high_score2)
				{
					JOptionPane.showMessageDialog(null,"CONGRATULATION\n You made a new high score : "+Score.score);
					System.exit(0);
				}
			}
			if(Level==3)
			{
				if(Score.score > Score.high_score3)
				{
					JOptionPane.showMessageDialog(null,"CONGRATULATION\n You made a new high score : "+Score.score);
					System.exit(0);
				}
			}
			JOptionPane.showMessageDialog(null,Score.getScore());
			System.exit(0);
		}
	}
	
    public void update(Graphics g)										//remove flickering
	{
		if(dbImage ==null)
		{
			dbImage=createImage(this.getSize().width, this.getSize().height);
			dbGraphics=dbImage.getGraphics();
		}
		dbGraphics.setColor(this.getBackground());
		dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		dbGraphics.setColor(this.getForeground());
		paint(dbGraphics);
		
		g.drawImage(dbImage,0,0,this);
	
	}
	
	public void paint(Graphics g)
	{
	//	g.setColor(Color.gray);											// Background screen color
		if(Level==1)
		{
			g.fillRect(0,0,getSize().width, getSize().height);
			ball.draw(g);
			paddle1.draw(g);
			s.draw(g);
			
			for(Blocks b : blocks)										// for-each loop
			{
				b.draw(g);										// Draw each blocks
			}
		}
		if(Level==2)
		{
			g.fillRect(0,0,getSize().width, getSize().height);
			ball.draw(g);
			paddle2.draw(g);
			s.draw(g);
	
			for(Blocks b : blocks)
			{
				b.draw(g);
			}
		}
		if(Level==3)
		{
			g.fillRect(0,0,getSize().width, getSize().height);
			ball.draw(g);
			paddle3.draw(g);
			s.draw(g);
	
			for(Blocks b : blocks)
			{
				b.draw(g);
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{	
		if(e.getKeyChar() == KeyEvent.VK_SPACE)								// for firing
		{
			if(power)										// only fire if power is activated
			{
				bullet=true;
				if(no_of_bullet>0)								// only fire is bullet is left
				{
					if(Level==1)
					{
						ball.xb=paddle1.getX()+60;
						ball.yb=paddle1.getY()-19;
					}
					if(Level==2)
					{
						ball.xb=paddle2.getX()+60;
						ball.yb=paddle2.getY()-19;
					}
					if(Level==3)
					{
						ball.xb=paddle3.getX()+60;
						ball.yb=paddle3.getY()-19;
					}
					
					no_of_bullet--;								// Limited bullets
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_G)								// God mode active
		{
			God_Mode.god_mode=1;
		}
		if(e.getKeyCode() == KeyEvent.VK_E)								// God mode exit
		{
			God_Mode.god_mode=0;
		}
		if(e.getKeyCode() == KeyEvent.VK_P)								// Pause
		{
			running1=false;
			running2=false;
			running3=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_R)								// Resume
		{
			if(Level==1)
			{
				running1=true;
			}
			if(Level==2)
			{
				running2=true;
			}
			if(Level==3)
			{
				running3=true;
			}
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)								// left arrow
		{
			if(Level==1)
			{
				paddle1.goingLeft=true;
				running1=true;
			}
			if(Level==2)
			{
				paddle2.goingLeft=true;
				running2=true;
			}
			if(Level==3)
			{
				paddle3.goingLeft=true;
				running3=true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)								// right arrow
		{
			if(Level==1)
			{
				paddle1.goingRight=true;
				running1=true;
			}
			if(Level==2)
			{
				paddle2.goingRight=true;
				running2=true;
			}
			if(Level==3)
			{
				paddle3.goingRight=true;
				running3=true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)								// up arrow
		{
			if(Level==3)
			{
				paddle3.goingUp=true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)								// down arrow
		{
			if(Level==3)
			{
				paddle3.goingDown=true;
			}
		}
	}
	
	public void keyReleased(KeyEvent e)									// Do action until key is release
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(Level==1)
			{
				paddle1.goingLeft=false;
			}
			if(Level==2)
			{
				paddle2.goingLeft=false;
			}
			if(Level==3)
			{
				paddle3.goingLeft=false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(Level==1)
			{
				paddle1.goingRight=false;
			}
			if(Level==2)
			{
				paddle2.goingRight=false;
			}
			if(Level==3)
			{
				paddle3.goingRight=false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(Level==3)
			{
				paddle3.goingUp=false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(Level==3)
			{
				paddle3.goingDown=false;
			}
		}
	}

	void menu()													// Menu Bar
	{
		
		MenuBar menubar = new MenuBar();
        Menu menu1 = new Menu("Game");
        Menu menu2 = new Menu("Setting");
        Menu menu3 = new Menu("Help");
        
        Menu submenu1=new Menu("Game");
        MenuItem item2 = new MenuItem("Pause");
        MenuItem item3 = new MenuItem("Resume");
        MenuItem item4 = new MenuItem("Exit");
        menu1.add(submenu1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.addSeparator();
        menu1.add(item4);
        
        Menu submenu2=new Menu("Ball Speed");
        Menu submenu3=new Menu("Paddle Speed");
        Menu submenu4=new Menu("God Mode");
        MenuItem item1 = new MenuItem("Highest Score");
        menu2.add(submenu2);
        menu2.add(submenu3);
        menu2.add(submenu4);
        menu2.addSeparator();
        menu2.add(item1);
        
        MenuItem item11 = new MenuItem("Level 1");
        MenuItem item12 = new MenuItem("Level 2");
        MenuItem item13 = new MenuItem("Level 3");
        submenu1.add(item11);
        submenu1.add(item12);
        submenu1.add(item13);
        
        MenuItem item21 = new MenuItem("0.6x");
        MenuItem item22 = new MenuItem("1.0x");
        MenuItem item23 = new MenuItem("1.4x");
        MenuItem item24 = new MenuItem("1.9x");
        submenu2.add(item21);
        submenu2.add(item22);
        submenu2.add(item23);
        submenu2.add(item24);
        
        MenuItem item31 = new MenuItem("1.0x");
        MenuItem item32 = new MenuItem("1.5x");
        MenuItem item33 = new MenuItem("2.0x");
        MenuItem item34 = new MenuItem("3.0x");
        submenu3.add(item31);
        submenu3.add(item32);
        submenu3.add(item33);
        submenu3.add(item34);
        
        MenuItem item41 = new MenuItem("Activate");
        MenuItem item42 = new MenuItem("Deactivate");
        submenu4.add(item41);
        submenu4.add(item42);
        
        MenuItem item5 = new MenuItem("Controls");
        MenuItem item6 = new MenuItem("About Game");
        MenuItem item7 = new MenuItem("About Developer");
        menu3.add(item5);
        menu3.add(item6);
        menu3.add(item7);

        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);
        
        item1.addActionListener(this);						// highest score
        item2.addActionListener(this);						// pause
        item3.addActionListener(this);						// resume
        item4.addActionListener(this);						// exit
        item5.addActionListener(this);						// control
        item6.addActionListener(this);						// about game
        item7.addActionListener(this);						// about developer
        item11.addActionListener(this);						// level 1
        item12.addActionListener(this);						// level 2
        item13.addActionListener(this);						// level 3
        item21.addActionListener(this);						// 0.6x
        item22.addActionListener(this);						// 1.0x
        item23.addActionListener(this);						// 1.4x
        item24.addActionListener(this);						// 1.9x
        item31.addActionListener(this);						// 1.0x
        item32.addActionListener(this);						// 1.5x
        item33.addActionListener(this);						// 2.0x
        item34.addActionListener(this);						// 3.0x
        item41.addActionListener(this);						// God Mode activate
        item42.addActionListener(this);						// God Mode deactivate
        
        Component c = this;
        while (c != null && !(c instanceof Frame)) 				// Removing default menubar of applet
        {
                c = c.getParent();
        }
        ((Frame)c).setMenuBar(menubar);					
	}				
	
	public void keyTyped(KeyEvent e)					// No use
	{
					// As it was compulsory to define
	}

	public void actionPerformed(ActionEvent e)
	{	
		if(e.getActionCommand().equals("Exit"))
			System.exit(0);
		
		if(e.getActionCommand().equals("Highest Score"))
			JOptionPane.showMessageDialog(null," HIGHEST SCORE : \n"+"\n LEVEL 1 : "+Score.high_score1+"\n LEVEL 2 : "+Score.high_score2+"\n LEVEL 3 : "+Score.high_score3);
		
		if(e.getActionCommand().equals("Pause"))						// Letter 'P'
		{
			running1=false;
			running2=false;
			running3=false;
		}
		
		if(e.getActionCommand().equals("Activate"))
		{
			God_Mode.god_mode=1;
		}
		
		if(e.getActionCommand().equals("Deactivate"))
		{
			God_Mode.god_mode=0;
		}
		
		if(e.getActionCommand().equals("Resume"))						// Letter 'R'
		{
			if(Level==1)
			{
				running1=true;
			}
			if(Level==2)
			{
				running2=true;
			}
			if(Level==3)
			{
				running3=true;
			}
		}
		
		if(e.getActionCommand().equals("Level 1"))
		{
			Level=1;
			
			Score.benifit=5;
			Score.loss=10;
			running1=true;
			running2=false;
			running3=false;
			
			if(flag==0)
			{	
				th.start();
				flag=1;
			}
		}
		
		if(e.getActionCommand().equals("Level 2"))
		{
			Level=2;
			
			Score.benifit=6;
			Score.loss=9;
			running1=false;
			running2=true;
			running3=false;
			
			if(flag==0)
			{	
				th.start();
				flag=1;
			}
		}
		
		if(e.getActionCommand().equals("Level 3"))
		{
			Level=3;
			
			Score.benifit=7;
			Score.loss=8;
			running1=false;
			running2=false;
			running3=true;
			
			if(flag==0)
			{	
				th.start();
				flag=1;
			}
		}
		
		if(e.getActionCommand().equals("0.6x"))							// of ball
		{
			if(Level==1)
			{
				Score.benifit=1;
				Score.loss=12;
			}
			if(Level==2)
			{
				Score.benifit=2;
				Score.loss=12;
			}
			if(Level==3)
			{
				Score.benifit=3;
				Score.loss=11;
			}
			
			Ball.xspeed=7;
			Ball.yspeed=7;
			Ball.xspeed*=0.6;
			Ball.yspeed=0.6;
		}
		
		if(e.getActionCommand().equals("1.0x"))							// for both ball and paddle
		{
			if(Level==1)
			{	
				Score.benifit=3;
				Score.loss=11;
				
				Paddle_Level1.xs=10;
				Paddle_Level1.ys=10;
			}
			if(Level==2)
			{
				Score.benifit=4;
				Score.loss=10;
				
				Paddle_Level2.xs=10;
				Paddle_Level2.ys=10;
			}
			if(Level==3)
			{
				Score.benifit=5;
				Score.loss=9;
				
				Paddle_Level3.xs=10;
				Paddle_Level3.ys=10;
			}
			Ball.xspeed=7;
			Ball.yspeed=7;
		}
		
		if(e.getActionCommand().equals("1.4x"))								// for ball
		{
			if(Level==1)
			{
				Score.benifit=4;
				Score.loss=7;
			}
			if(Level==2)
			{
				Score.benifit=5;
				Score.loss=6;
			}
			if(Level==3)
			{
				Score.benifit=6;
				Score.loss=5;
			}
			
			Ball.xspeed=7;
			Ball.yspeed=7;
			Ball.xspeed*=1.2;
			Ball.yspeed*=1.4;
		}
		
		if(e.getActionCommand().equals("1.9x"))								// for ball
		{
			if(Level==1)
			{
				Score.benifit=6;
				Score.loss=7;
			}
			if(Level==2)
			{
				Score.benifit=8;
				Score.loss=6;
			}
			if(Level==3)
			{
				Score.benifit=10;
				Score.loss=5;
			}
			
			Ball.xspeed=7;
			Ball.yspeed=7;
			Ball.xspeed*=1.4;
			Ball.yspeed*=1.8;
		}
		
		if(e.getActionCommand().equals("1.5x"))								// for paddle
		{
			if(Level==1)
			{
				Paddle_Level1.xs=10;
				Paddle_Level1.ys=10;
				Paddle_Level1.xs*=1.5;
				Paddle_Level1.ys*=1.5;
			}
			if(Level==2)
			{
				Paddle_Level2.xs=10;
				Paddle_Level2.ys=10;
				Paddle_Level2.xs*=1.5;
				Paddle_Level2.ys*=1.5;
			}
			if(Level==3)
			{
				Paddle_Level3.xs=10;
				Paddle_Level3.ys=10;
				Paddle_Level3.xs*=1.5;
				Paddle_Level3.ys*=1.5;
			}
			
		}
		
		if(e.getActionCommand().equals("2.0x"))								// for paddle
		{
			if(Level==1)
			{
				Paddle_Level1.xs=10;
				Paddle_Level1.ys=10;
				Paddle_Level1.xs*=2.0;
				Paddle_Level1.ys*=2.0;
			}
			if(Level==2)
			{
				Paddle_Level2.xs=10;
				Paddle_Level2.ys=10;
				Paddle_Level2.xs*=2.0;
				Paddle_Level2.ys*=2.0;
			}
			if(Level==3)
			{
				Paddle_Level3.xs=10;
				Paddle_Level3.ys=10;
				Paddle_Level3.xs*=2.0;
				Paddle_Level3.ys*=2.0;
			}
		}
		
		if(e.getActionCommand().equals("3.0x"))								// for paddle
		{
			if(Level==1)
			{
				Paddle_Level1.xs=10;
				Paddle_Level1.ys=10;
				Paddle_Level1.xs*=3.0;
				Paddle_Level1.ys*=3.0;
			}
			if(Level==2)
			{
				Paddle_Level2.xs=10;
				Paddle_Level2.ys=10;
				Paddle_Level2.xs*=3.0;
				Paddle_Level2.ys*=3.0;
			}
			if(Level==3)
			{
				Paddle_Level3.xs=10;
				Paddle_Level3.ys=10;
				Paddle_Level3.xs*=3.0;
				Paddle_Level3.ys*=3.0;
			}
		}
		
		if(e.getActionCommand().equals("Controls"))
		{
			JOptionPane.showMessageDialog(null," CONTROLS : \n Right Arrow : paddle move right\n Left Arrow : paddle move left\n Up Arrow : paddle move up\n Down Arrow : paddle move down\n P : Pause\n R : Resume\n Space Bar : Fire\n G : God Mode active\n E : God Mode exit");
		}
		
		if(e.getActionCommand().equals("About Game"))
		{
			JOptionPane.showMessageDialog(null,"Game is developed in java.\nAIM : you have to destroy all blocks,\nwithout droping the ball.\n\nGOOD LUCK !");
		}
		
		if(e.getActionCommand().equals("About Developer"))
		{
			JOptionPane.showMessageDialog(null,"Name : VISHAL SINGH.\nRoll No : 1209710121\nClass : CS-B\nYear : THIRD(3)");
		}
	}
}