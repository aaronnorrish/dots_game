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

	/**
	* Constructor for a green dot object. Initialises the dot's
	* coordinates to a random position, and assigns it a random velocity
	 * (in either the x direction or y direction).
	**/
	public GreenDot(){
		xPos = getRandomNumber(0, 700 - radius * 2);
		yPos = getRandomNumber(40, 500 - radius * 2);
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

	/**
	* Draws the green dot at its current x and y coordinates in the window.
	* Called by the paint method in the main (Dots) class.
	**/
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval((int) xPos, (int) yPos, radius * 2, radius * 2);
	}

	/**
	* Mutates the position and velocity fields of the green dot.
	* Called by the run method in the main class.
	**/
	public void move(){
		xPos += xVel;
		yPos += yVel;

		if(xPos < 0 || xPos > 700 - radius * 2){
			xVel = -xVel;
		}

		if(yPos < 40 || yPos > 500 - radius * 2){
			yVel = -yVel;
		}

		centreX = xPos + radius;
		centreY = yPos + radius;

	}

	/**
	* Returns the x coordinate of the green dot's centre
	* @return the x coordinate of the green dot's centre
	**/
	public int getCentreX(){
		return (int) centreX;
	}

	/**
	* Returns the y coordinate of the green dot's centre
	* @return the y coordinate of the green dot's centre
	**/
	public int getCentreY(){
		return (int) centreY;
	}

	/**
	* A private helper method that returns a random integer between the
	* provided integers.
	* @return a random integer between min (inclusive) and max (exclusive)
	**/
	private int getRandomNumber(int min, int max){
		rand = new Random();
		return rand.nextInt(max - min) + min;
	}
}
