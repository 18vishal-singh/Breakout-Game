/*
 * 	HERE DECLARATION OF BLOCKS IS DONE
 */



import java.awt.*;


public class Blocks
{
	int x,y,width=64,height=14;					// coordinate and dimension of the block
	public boolean alive = true;				// Tell a block is alive or not
	
	public Blocks(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics g)
	{
		
		g.setColor(Color.red);
		
	    g.fillRoundRect(x, y, width, height, 15, 15);
	//	g.fillRect(x, y, width, height);							// Draw block
	//	g.setColor(Color.green);
	//	g.drawString("########", x+4, y+height-3);				// fill ### in blocks
	    
	    
/*  	int [ ] xx = {20, 20, 30, 50, 30, 20};				helps in drawing any shape(polygon)
		int [ ] yy = {10, 25, 35, 40, 35, 30};
		g.fillPolygon(xx, yy, 6);					*/
	}
}
