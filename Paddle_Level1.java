/*
 * 	COORDINATE, DRAWING AND MOVEMENT OF PADDLE OF LEVEL 1
 */




import java.awt.*;
//import java.util.*;

public class Paddle_Level1
{
	int x=0,y=0,width=120,height=9;							// corrdinate of the paddle
	static int xs=10,ys=10;								// speed of paddle in x and y direction
	public boolean goingLeft=false;
	public boolean goingRight=false;
	God_Mode gm;
//	Random r = new Random();
	
	public Paddle_Level1(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()								// Return width of the paddle
	{
		return width;
	}
	public int getHeight()								// Return height of the paddle
	{
		return height;
	}
	
	public void move(int maxWidth)
	{
			if(goingLeft)
				x -=xs;
			if(goingRight)
				x +=ys;
											// Boundary Conditions
			if(x<0)
				x=0;
			if(x+120>maxWidth)
				x=maxWidth-120;
	}
	public void move_byGod(int maxWidth)
	{
//		int  n = r.nextInt(50) + 5;
		this.x=Ball.currentX()-(60);
		
		if(x<0)
			x=0;
		if(x+120>maxWidth)
			x=maxWidth-120;
	}
	
	public void draw(Graphics g)
	{
		if(Start.power)								// Gun appears if power is hit paddle
		{
			g.setColor(Color.white);
			g.fillRect(x+55, y-13, 15, 8);
			g.setColor(Color.red);
			g.drawString("^",x+60, y-3);
		}
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);							// draw paddle
		g.setColor(Color.red);
		g.drawString("+~+~+~+~+~+~+~+~+",x+1,y+9);					// fill +~+~+ in paddle
	}

}
