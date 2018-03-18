package dots_game;

import java.awt.Graphics;
import java.awt.Color;

public class Player{
  double xPos, yPos, xVel, yVel, centreX, centreY;
  boolean upAccel, downAccel, leftAccel, rightAccel;

  public Player(){
		upAccel = false; downAccel = false;
		xPos = 350; yPos = 250;
		xVel = 0; yVel = 0;
	}

  public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawOval((int) xPos, (int) yPos, 30, 30);
	}

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

	public void setUpAccel(boolean input){
		upAccel = input;
	}

	public void setDownAccel(boolean input){
		downAccel = input;
	}

	public void setLeftAccel(boolean input){
		leftAccel = input;
	}

	public void setRightAccel(boolean input){
		rightAccel = input;
	}
	
	public boolean checkCollision(Dot d){
		return ((Math.pow(centreX - d.getCentreX(), 2) + Math.pow(centreY - d.getCentreY(), 2)) <= Math.pow((15 + 5), 2));
	}

}
