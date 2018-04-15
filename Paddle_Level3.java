/*
 * 	COORDINATE, DRAWING AND MOVEMENT OF PADDLE OF LEVEL 3
 */


import java.awt.*;

public class Paddle_Level3 
{													// x and y is coordinate of the lower paddle
	int x=0,y=0,a=0,b=0,width=120,height=9;								// a and b is coordinate of the left paddle
	static int xs=10,ys=10;										// xs and ys paddle speed in x and y direction
	int a1,y1=26;											// a1 is x coordinate of right paddle
													// y1 is y coordinate of upper paddle
	public boolean goingLeft=false;
	public boolean goingRight=false;
	public boolean goingUp=false;
	public boolean goingDown=false;
	
	public Paddle_Level3(int x,int y, int a, int b)
	{
		this.x=x;
		this.y=y;
		this.a=a;
		this.b=b;
		a1=a+1100;													// x coordinate of right paddle
	}
	public void move_byGod(int maxWidth)
	{
//		int  n = r.nextInt(50) + 5;
		this.x=Ball.currentX()-(60);
		this.b=Ball.currentY()-(60);
		
		if(x<0)
			x=0;
		if(x+120>maxWidth)
			x=maxWidth-120;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getY1()
	{
		return y1;
	}
	public int getA()
	{
		return a;
	}
	public int getB()
	{
		return b;
	}
	public int getA1()
	{
		return a1;
	}
	public int getWidth()										// Return width of the paddle
	{
		return width;
	}
	public int getHeight()										// Return height of the paddle
	{
		return height;
	}
	
	public void move(int maxWidth, int maxHeight)							// For movement of the paddle in Level 3
	{
			if(goingLeft)
			{
				x -=xs;
			}
			if(goingRight)
			{
				x +=ys;
			}
			if(goingUp)
			{
				b -=xs;
			}
			if(goingDown)
			{
				b +=ys;
			}
													// Boundary conditions
			if(b<0)
			{
				b=0;
			}
			if(b+120>maxHeight)
			{
				b=maxHeight-120;
			}
			if(x<0)
			{
				x=0;
			}
			if(x+120>maxWidth)
			{
				x=maxWidth-120;
			}
	}
	
	public void draw(Graphics g)
	{
		if(Start.power)										// Gun appears if power is hit paddle
		{
			g.setColor(Color.white);
			g.fillRect(x+55, y-13, 15, 8);
			g.setColor(Color.red);
			g.drawString("^",x+60, y-3);
		}
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);							// Lower paddle
		g.fillRect(x, y1, width, height);							// Upper paddle
		g.fillRect(a, b, height,width);								// Left paddle
		g.fillRect(a1+10,b, height,width);							// Right paddle
		
		g.setColor(Color.red);
		g.drawString("+~+~+~+~+~+~+~+~+",x+1,y+9);
		g.drawString("+~+~+~+~+~+~+~+~+",x+1,y1+9);
	}

}

