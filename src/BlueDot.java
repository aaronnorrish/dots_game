package dots_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BlueDot implements Dot{
	Random rand;
	int xPos, yPos, xVel, yVel;
	double centreX, centreY;
	int radius = 7;

	/**
	* Constructor for a blue dot object. Initialises the dot's
	* coordinates to a random position.
	**/
	public BlueDot(){
		xPos = getRandomPosition(0, 700 - radius * 2);
		yPos = getRandomPosition(40, 500 - radius * 2);
		centreX = xPos + radius;
		centreY = yPos + radius;
	}

	/**
	* Draws the blue dot at its current x and y coordinates in the window.
	* Called by the paint method in the main (Dots) class.
	**/
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval((int) xPos,(int) yPos, radius * 2, radius * 2);
	}

	/**
	* Reassigns the coordinate fields of the blue dot to a new random position.
	* Called by the run method in the main class if the player collides with
	* with the blue dot.
	**/
	public void redraw(){
		xPos = getRandomPosition(0, 700 - radius * 2);
		yPos = getRandomPosition(40, 500 - radius * 2);
		centreX = xPos + radius;
		centreY = yPos + radius;
	}

	/**
	* Returns the x coordinate of the upper left hand corner of the square
	* containing the blue dot.
	* @return the x coordinate of the upper left hand corner of the blue dot
	**/
	public int getX(){
		return (int) xPos;
	}

	/**
	* Returns the y coordinate of the upper left hand corner of the square
	* containing the blue dot.
	* @return the y coordinate of the upper left hand corner of the blue dot
	**/
	public int getY(){
		return (int) yPos;
	}

	/**
	* Returns the x coordinate of the blue dot's centre
	* @return the x coordinate of the blue dot's centre
	**/
	public int getCentreX(){
		return (int) centreX;
	}

	/**
	* Returns the y coordinate of the blue dot's centre
	* @return the y coordinate of the blue dot's centre
	**/
	public int getCentreY(){
		return (int) centreY;
	}

	/**
	* A private helper method that returns a random integer between the
	* provided integers.
	* @return a random integer between min (inclusive) and max (exclusive)
	**/
	private int getRandomPosition(int min, int max){
		rand = new Random();
		return rand.nextInt(max - min) + min;
	}
}
