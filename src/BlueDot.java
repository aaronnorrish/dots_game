package dots_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BlueDot implements Dot{
	Random rand;
	int xPos, yPos, xVel, yVel;
	double centreX, centreY;
	int radius = 15;
	
	public BlueDot(){
		xPos = getRandomPosition(0, 700 - radius * 2);
		yPos = getRandomPosition(0, 500 - radius * 2);
		centreX = xPos + radius;
		centreY = yPos + radius;
	}

	private int getRandomPosition(int min, int max){
		rand = new Random();
		return rand.nextInt(max);
	}

	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval((int) xPos,(int) yPos, radius, radius);
	}

	public void move(){
		xPos += xVel;
		yPos += yVel;

		if(yPos < 10){
			yVel = -yVel;
		}
		else if(yPos > 490){
			yVel = -yVel;
		}
	}

	public void redraw(){
		xPos = getRandomPosition(0, 700 - radius * 2);
		yPos = getRandomPosition(0, 500 - radius * 2);
		centreX = xPos + radius;
		centreY = yPos + radius;
	}
	
	public int getX(){
		return (int) xPos;
	}

	public int getY(){
		return (int) yPos;
	}
	
	public int getCentreX(){
		return (int) centreX;
	}

	public int getCentreY(){
		return (int) centreY;
	}

}
