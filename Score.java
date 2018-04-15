/*
 * 	RECORD AND DISPLAY SCORE OF PLAYER
 */



import java.awt.Color;
import java.awt.Graphics;

public class Score 
{
	static int score=0;									// Score of the game
	static int benifit=5;									// Increase score by
	static int loss=5;									// Decreases score by
	static int high_score1=350;								// Highest Score of Level 1 
	static int high_score2=460;								// Highest Score of Level 2
	static int high_score3=570;								// Highest Score of Level 3
	
	static void score_plus()								// Increment in the score
	{
		score +=benifit;
	}
	
	static void score_minus()								// Decrement in the score
	{
		score -=loss;
	}
	
	static String getScore()								// Return present score
	{
		return "Your Score : "+score;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.yellow);
		g.drawString(getScore(), 20, 20);
		if(Start.power || Start.bullet)								// draw if bullet is in picture
		{
			g.drawString("Number of bullets left :"+Start.no_of_bullet, 900, 20);
		}
		if(God_Mode.god_mode==1)
		{
			g.setColor(Color.WHITE);
			g.drawString("****** GOD MODE ******", 550, 18);
			g.drawString("   (press E to exit)   ", 560, 30);
		}
	}
}
