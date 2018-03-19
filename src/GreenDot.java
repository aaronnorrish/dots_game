package dots_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GreenDot implements Dot{
	Random rand;
	int xPos, yPos, xVel, yVel;
	double centreX, centreY;
	int[] direction = {-1, 1};
	int dir;
	int radius = 7;
	
	public GreenDot(){
		xPos = getRandomNumber(0, 700 - radius * 2);
		yPos = getRandomNumber(0, 500 - radius * 2);
		centreX = xPos + radius;
		centreY = yPos + radius;
		dir = getRandomNumber(0, 2);
		if(dir == 0){
			xVel = 0;
			yVel = 2 * direction[getRandomNumber(0, 2)];
		}
		else{
			xVel = 2 * direction[getRandomNumber(0, 2)];
			yVel = 0;
		}
		
	}

	private int getRandomNumber(int min, int max){
		rand = new Random();
		return rand.nextInt(max);
	}

	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval((int) xPos, (int) yPos, radius * 2, radius * 2);
	}

	public void move(){
		xPos += xVel;
		yPos += yVel;
		
		if(xPos < 0 || xPos > 700 - radius * 2){
			xVel = -xVel;
		}
		
		if(yPos < 0 || yPos > 500 - radius * 2){
			yVel = -yVel;
		}
		
		centreX = xPos + radius;
		centreY = yPos + radius;
		
	}
	
	public int getCentreX(){
		return (int) centreX;
	}

	public int getCentreY(){
		return (int) centreY;
	}

}
