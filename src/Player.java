package dots_game;

import java.awt.Graphics;
import java.awt.Color;

public class Player{
  double xPos, yPos, xVel, yVel, centreX, centreY;
  boolean upAccel, downAccel, leftAccel, rightAccel;

  /**
  * Constructor for the player object. Initialises the player's starting
  * position to the middle of the window, with no initial velocity.
  **/
  public Player(){
		upAccel = false; downAccel = false;
		xPos = 350; yPos = 250;
		xVel = 0; yVel = 0;
	}

  /**
  * Draws the player at its current x and y coordinates in the window.
  * Called by the paint method in the main (Dots) class.
  **/
  public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawOval((int) xPos, (int) yPos, 30, 30);
	}

  /**
  * Mutates the position and velocity fields of the player.
  * Called by the run method in the main (Dots) class.
  **/
  public void move(){
		if(upAccel){
			yVel -= 2;
		}

		else if(downAccel){
			yVel += 2;
		}
		
		else if(yVel > 0){
			yVel -=1;
		}
		
		else if(yVel < 0){
			yVel +=1;
		}

		if(leftAccel){
			xVel -= 2;
		}

		else if(rightAccel){
			xVel += 2;
		}
		
		else if(xVel > 0){
			xVel -=1;
		}
		
		else if(xVel < 0){
			xVel +=1;
		}

		if(yVel >= 4){
			yVel = 4;
		}
		else if(yVel <= -4){
			yVel = -4;
		}

    if(xVel >= 4){
			xVel = 4;
		}
		else if(xVel <= -4){
			xVel = -4;
		}

		yPos += yVel;
		xPos += xVel;
		centreX = xPos + 15;
		centreY = yPos + 15;

		if(yPos < 0){
			yPos = 0;
		}

		else if(yPos > 470){
			yPos = 470;
		}

		if(xPos < 0){
			xPos = 0;
		}

		else if(xPos > 670){
			xPos = 670;
		}
	}

	/**
  	* Sets the upAccel field of player to the (boolean) input
 	**/
	public void setUpAccel(boolean input){
		upAccel = input;
	}

 	 /**
  	* Sets the downAccel field of player to the (boolean) input
  	**/
	public void setDownAccel(boolean input){
		downAccel = input;
	}

  	/**
  	* Sets the leftAccel field of player to the (boolean) input
  	**/
	public void setLeftAccel(boolean input){
		leftAccel = input;
	}

  	/**
  	* Sets the rightAccel field of player to the (boolean) input
  	**/
	public void setRightAccel(boolean input){
		rightAccel = input;
	}

  	/**
   	* Determines whether the player and Dot d have collided. If the distance
   	* between the player and d is less than the sum of the radii of the respective
   	* objects, then there must have been a collision, hence return true, otherwise
   	* return false.
   	* @return true if the player and dot have collided, false otherwise
  	**/
	public boolean checkCollision(Dot d){
		return ((Math.pow(centreX - d.getCentreX(), 2) + Math.pow(centreY - d.getCentreY(), 2)) <= Math.pow((15 + 5), 2));
	}

}
