/*
 *	BALL CLASS SPECIFY BALL COORDINATE AND ITS MOTION
 *	ALSO CONTAINS COORDINATE OF POWER (GUN) AND ITS CATCHING BY PADDLE
 *	AND POWER BALL MOTION
 */




import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ball 
{
	static private double x=0,y=0,radius=8;							// Coordinate of ball (center and radius)
	private double px=0,py=0;									// Coordinate of power ball
	double xp=0,yp=0;											// xp && yp is coordinate of falling power 1
	double pxp=0,pyp=0;											// pxp && pyp is coordinate of falling power 2
	
	double xb=0,yb=0;											// xb && yb is coordinate of bullet
	static double xspeed=10,yspeed=10;							// Speed of the ball
	static double pxspeed=5,pyspeed=5;							// Speed of the power ball

	static boolean  power_fall1=false;							// For gun
	static boolean  power_fall2=false;							// For power ball
	
	God_Mode gm;												// God mode object
	
	public Ball(int x,int y)
	{
		Ball.x=x;
		Ball.y=y;
	}
	static int currentX()
	{
		return (int)x;
	}
	static int currentY()
	{
		return (int)y;
	}
	public void drop_power()										// drop power
	{
		if(power_fall1)											// Power is catch by paddle
		{
			yp=yp+5;
		}
		if(power_fall2)											// Power is catch by paddle
		{
			pyp=pyp+5;
		}
	}
	
	public void fire()											// For firing bullet
	{
		if(Start.bullet)										// Space bar is pressed
		{
			yb=yb-5;
		}
	}
	
	public void checkBulletCollison(Blocks b)								// checks collison of bullet and blocks
	{
		Rectangle2D.Double self =new Rectangle2D.Double(xb-9,yb-9,9*2,9*2);
		Rectangle2D.Double block =new Rectangle2D.Double(b.x,b.y,b.width,b.height);
		if(self.intersects(block))									// self is for bullet
		{
			b.alive=false;
			Start.bullet=false;									// disable bullet after hitting first block
			Score.score_minus();
		}
		
	}
	
	public void checkCollision(Blocks b,int i)								//check collision of ball and blocks
	{
		Rectangle2D.Double self =new Rectangle2D.Double(x-radius,y-radius,radius*2,radius*2);
		Rectangle2D.Double block =new Rectangle2D.Double(b.x,b.y,b.width,b.height);
		if(self.intersects(block))									// self is for ball
		{
			Score.score_plus();
			b.alive=false;
			if((i==6||i==12||i==24||i==48) && Start.flag2==1)				// 6,12,24,48 block contains power
			{
				xp=x;
				yp=y;
				power_fall1=true;								// power will fall from block
			}	
			if((i==10||i==20||i==30||i==40) && Start.flag3==1)
			{
				pxp=x;
				pyp=y;
				power_fall2=true;
			}
			
			if(x>b.x && x+radius*2< b.x+b.width)							// ball deflect from blocks
			{
				yspeed = -yspeed;
			}
			else
			{
				xspeed = -xspeed;
			}
		}
		if(Start.power1)
		{
			Rectangle2D.Double self1 =new Rectangle2D.Double(px-radius,py-radius,radius*2,radius*2);
			if(self1.intersects(block))
			{
				Score.score_plus();
				b.alive=false;
				
	/*			if(px>b.x && px+radius*2< b.x+b.width)							// power ball deflect from blocks
				{
					pyspeed = -pyspeed;
				}
				else
				{
					pxspeed = -pxspeed;
				}
	*/
			}
		}
	}
	
	public void move(Dimension dim, Paddle_Level1 paddle)							// paddle movement for level 1
	{	
	
		x+=xspeed;
		y+=yspeed;
		
		if(Start.power1)
		{	
			px+=pxspeed;
			py+=pyspeed;
			
			if(px<0)										// Power Ball strike from left wall
			{
				pxspeed=Math.abs(pxspeed);
			}
			if(px>dim.width)								// Power Ball strike from right wall
			{
				pxspeed=-Math.abs(pxspeed);
			}
			if(py<0)												// Power Ball strike from upper wall
			{
				pyspeed=Math.abs(pyspeed);
			}
			if(py > dim.height+200)
			{
				Start.power1=false;
			}
		}
		if(x<0)												// Ball strike from left wall
		{
			xspeed=Math.abs(xspeed);
		//	Score.score_minus();
		}
		if(x>dim.width)											// Ball strike from right wall
		{
			xspeed=-Math.abs(xspeed);
	//		Score.score_minus();
		}
		if(y<0)												// Ball strike from upper wall
		{
			yspeed=Math.abs(yspeed);
		}
														// Power only activate if fall on paddle
//****************************************		
		if(xp > paddle.getX() && xp < paddle.getX() + paddle.getWidth())
		{
			if(yp>=paddle.getY() && yp < paddle.getY() + paddle.getHeight())
			{
				Start.power=true;
				Start.flag2=0;									// Only once power will fall
			}
		}
		if(pxp > paddle.getX() && pxp < paddle.getX() + paddle.getWidth())
		{
			if(pyp>=paddle.getY() && pyp < paddle.getY() + paddle.getHeight())
			{
				Start.power1=true;
				Start.flag3=0;									// Only once power will fall
			}
		}
//***************************************
														// for reflecting the ball from paddle
		if(x > paddle.getX() && x < paddle.getX() + paddle.getWidth())
		{
			if(y>=paddle.getY() && y < paddle.getY() + paddle.getHeight())
			{
				if(God_Mode.god_mode == 1)										// If god mode active then deflection is by normal
				{
					yspeed =-Math.abs(yspeed);
				}
				else
				{
					yspeed =-Math.abs(yspeed);
					double dist = x-(paddle.getX()+(paddle.getWidth()/2));
					xspeed=dist/10;
				}
			}
		}
		if(Start.power1)
		{												// for reflecting the power ball from the paddle
			if(px > paddle.getX() && px < paddle.getX() + paddle.getWidth())
			{
				if(py>=paddle.getY() && py < paddle.getY() + paddle.getHeight())
				{
					pyspeed =-Math.abs(pyspeed);
					double dist1 = px-(paddle.getX()+(paddle.getWidth()/2));
					pxspeed=dist1/10;
				}
			}
		}
		if(y > dim.height+200)										// Ball emerge from up if miss lower paddle
		{
			y = 0;
			Score.score_minus();
		}
	}

	public void move(Dimension dim, Paddle_Level2 paddle)							// paddle movement for level 2
	{	
		x+=xspeed;
		y+=yspeed;
		
		if(Start.power1)
		{
			px+=pxspeed;
			py+=pyspeed;
			
			if(px<0)
			{
				pxspeed=Math.abs(pxspeed);
			}
			if(px>dim.width)
			{
				pxspeed=-Math.abs(pxspeed);
			}
			if(py<0)
			{
				Start.power1=false;
			}
			if(py > dim.height+200)
			{
				Start.power1=false;
			}
		}
		
		if(x<0)
		{
			xspeed=Math.abs(xspeed);
//			Score.score_minus();
		}
		if(x>dim.width)
		{
			xspeed=-Math.abs(xspeed);
	//		Score.score_minus();
		}
		if(y<0)
		{
//			System.exit(0);
			yspeed=Math.abs(yspeed);
			Score.score_minus();
			
		}

		//***************************
		
		if(xp > paddle.getX() && xp < paddle.getX() + paddle.getWidth())
		{
			if(yp>=paddle.getY() && yp < paddle.getY() + paddle.getHeight())
			{
				Start.power=true;
				Start.flag2=0;									// Only once power will fall
			}
		}
		if(pxp > paddle.getX() && pxp < paddle.getX() + paddle.getWidth())
		{
			if(pyp>=paddle.getY() && pyp < paddle.getY() + paddle.getHeight())
			{
				Start.power1=true;
				Start.flag3=0;									// Only once power will fall
			}
		}
		//***************************

		
		if(x > paddle.getX() && x < paddle.getX() + paddle.getWidth())
		{
			if(y<=paddle.getY1() + paddle.getHeight())
			{
				if(God_Mode.god_mode == 1)
				{
					yspeed =Math.abs(yspeed);
				}
				else
				{
					yspeed =Math.abs(yspeed);
					double dist = x-(paddle.getX()+(paddle.getWidth()/2));
					xspeed=dist/10;
				}
			}
			if(y>=paddle.getY())
			{
				if(God_Mode.god_mode == 1)
				{
					yspeed =-Math.abs(yspeed);
				}
				else
				{
					yspeed =-Math.abs(yspeed);
					double dist = x-(paddle.getX()+(paddle.getWidth()/2));
					xspeed=dist/10;
				}
			}
		}
		if(Start.power1)
		{
			if(px > paddle.getX() && px < paddle.getX() + paddle.getWidth())
			{
				if(py<=paddle.getY1() + paddle.getHeight())
				{
					pyspeed =Math.abs(pyspeed);
					double dist = px-(paddle.getX()+(paddle.getWidth()/2));
					pxspeed=dist/10;
				}
				if(py>=paddle.getY())
				{
					pyspeed =-Math.abs(pyspeed);
					double dist = px-(paddle.getX()+(paddle.getWidth()/2));
					pxspeed=dist/10;
				}
			}
		}
		if(y > dim.height+200)										// Ball emerge from up if miss lower paddle							
		{
//			System.exit(0);
			Score.score_minus();
			y = 0;
		}						
	}
	
	public void move(Dimension dim, Paddle_Level3 paddle)							// paddle movement for level 3
	{
		x+=xspeed;
		y+=yspeed;
		
		if(Start.power1)
		{
			px+=pxspeed;
			py+=pyspeed;
			
			if(px<0)
			{
				Start.power1=false;
			}
			if(px>dim.width)
			{
				Start.power1=false;
			}
			if(py<0)
			{
				Start.power1=false;
			}
			if(py > dim.height+200)
			{
				Start.power1=false;
			}
		}
		
		if(x<0)
		{
			xspeed=Math.abs(xspeed);
			Score.score_minus();
		}
		if(x>dim.width)
		{
			xspeed=-Math.abs(xspeed);
			Score.score_minus();
		}
		if(y<0)
		{
			yspeed=Math.abs(yspeed);
			Score.score_minus();
		}
		
		//***************************
		
		if(xp > paddle.getX() && xp < paddle.getX() + paddle.getWidth())
		{
			if(yp >= paddle.getY() && yp < paddle.getY() + paddle.getHeight())
			{
				Start.power=true;
				Start.flag2=0;									// Only once power will fall
			}
		}
		if(pxp > paddle.getX() && pxp < paddle.getX() + paddle.getWidth())
		{
			if(pyp>=paddle.getY() && pyp < paddle.getY() + paddle.getHeight())
			{
				Start.power1=true;
				Start.flag3=0;									// Only once power will fall
			}
		}
		//***************************
		
		if(x > paddle.getX() && x < paddle.getX() + paddle.getWidth())
		{
			
			if(y<=paddle.getY1() + paddle.getHeight())
			{
				if(God_Mode.god_mode == 1)
				{
					yspeed =Math.abs(yspeed);
				}
				else
				{
					yspeed =Math.abs(yspeed);
					double dist = x-(paddle.getX()+(paddle.getWidth()/2));
					xspeed=dist/10;
				}
			}
			if(y>=paddle.getY())
			{
				if(God_Mode.god_mode == 1)
				{
					yspeed =-Math.abs(yspeed);
				}
				else
				{
					yspeed =-Math.abs(yspeed);
					double dist = x-(paddle.getX()+(paddle.getWidth()/2));
					xspeed=dist/10;
				}
			}
		}
		if(y >paddle.getB() && y < paddle.getB() + 120)
		{
			
			if(x>=paddle.getA1())// + paddle.getWidth())
			{
				xspeed =-Math.abs(xspeed);
			}
			if(x<35)
			{
				xspeed =Math.abs(xspeed);
			}
		}	
		
		if(Start.power1)
		{
			if(px > paddle.getX() && px < paddle.getX() + paddle.getWidth())
			{
				
				if(py<=paddle.getY1() + paddle.getHeight())
				{
					pyspeed =Math.abs(pyspeed);
					double dist = px-(paddle.getX()+(paddle.getWidth()/2));
					pxspeed=dist/10;
				}
				if(py>=paddle.getY())
				{
					pyspeed =-Math.abs(pyspeed);
					double dist = px-(paddle.getX()+(paddle.getWidth()/2));
					pxspeed=dist/10;
				}
			}
			if(py >paddle.getB() && py < paddle.getB() + 120)
			{
				
				if(px>=paddle.getA1())// + paddle.getWidth())
				{
					pxspeed =-Math.abs(pxspeed);
				}
				if(px<35)
				{
					pxspeed =Math.abs(pxspeed);
				}
			}	
		}
		if(y > dim.height+200)										// Ball emerge from up if miss lower paddle
		{
			y = 0;
			Score.score_minus();
		}						
	}
	
	public void draw(Graphics g)
	{
		if(power_fall1)											// Falling power appears if power block is hit
		{
			g.setColor(Color.white);
			g.drawString("\"*GUN*\"",(int)xp+5,(int)yp);
			g.fillOval((int)xp,(int) yp,(int) 9,(int) 9);
		}
		if(power_fall2)											
		{
			g.setColor(Color.blue);
			g.drawString("\"+1 BALL\"",(int)pxp+5,(int)pyp);
			g.fillOval((int) pxp,(int) pyp,(int) 9,(int) 9);
		}
		if(Start.bullet)										// bullet appears if space bar pressed
		{
			g.setColor(Color.GREEN);
			g.fillOval((int)xb,(int) yb,(int) 8,(int) 8);
		}
		if(Start.power1)
		{
			g.setColor(Color.blue);
			g.fillOval((int)(px-radius), (int)(py-radius), (int)(radius*3), (int)(radius*3));
		}
		g.setColor(Color.GREEN);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
	}
	
}