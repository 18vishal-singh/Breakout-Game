/*
 * 	COORDINATE, DRAWING AND MOVEMENT OF PADDLE OF LEVEL 2
 */




import java.awt.*;

public class Paddle_Level2 
{
	int x=0,y1=26,y=0,width=120,height=9;						// x and y are coordinate of the lower paddle
											// y1 is y coordinate of upper paddle
	static int xs=10,ys=10;								// xs and ys are speed of the paddle
	public boolean goingLeft=false;
	public boolean goingRight=false;
	public Paddle_Level2(int x,int y)
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
	public int getY1()
	{
		return y1;
	}
	public int getWidth()								// Return width of the paddle
	{
		return width;
	}
	public int getHeight()								// Return Height of the paddle
	{
		return height;
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
	public void move(int maxWidth)							// For moving of the paddle in Level 2
	{
			if(goingLeft)
			{
				x -=xs;
			}
			if(goingRight)
			{
				x +=ys;
			}
											// Boundary conditions
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
		if(Start.power)								// Gun appears if power is hit paddle
		{
			g.setColor(Color.white);
			g.fillRect(x+55, y-13, 15, 8);
			g.setColor(Color.red);
			g.drawString("^",x+60, y-3);
		}
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
		g.fillRect(x, y1, width, height);
		
		g.setColor(Color.red);
		g.drawString("+~+~+~+~+~+~+~+~+",x+1,y+9);
		g.drawString("+~+~+~+~+~+~+~+~+",x+1,y1+9);
	}

}

