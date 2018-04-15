/*
 * God mode i.e computer will play automatic
*/

public class God_Mode
{
	static int god_mode=0;													// God mode only work if it is 1
	double x,y;
	Ball ball;

	void getGodX()															// Getting current ball x-coordinate
	{
		this.x=Ball.currentX();
	}
	void getGodY()															// Getting current ball y-coordinate
	{
		this.y=Ball.currentY();
	}
	
}