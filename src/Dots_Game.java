package dots_game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.time.Duration;

public class Dots extends Applet implements Runnable, KeyListener{
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	Player p;
	BlueDot b;
	ArrayList<GreenDot> dots;
	boolean menu, gameStarted;
	Graphics gfx;
	Image img;
	int highscore, score, old_score;
	boolean powerUp, startTimer;
	LocalTime start, now;
	ArrayList<Integer> nums;
	
	/**
	* Initialises applet and associated variables.
	**/
	public void init(){
		this.resize(WIDTH, HEIGHT);
		menu = true;
		gameStarted = false;
		this.addKeyListener(this);
		
		highscore = 0;
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}

	/**
	* Initialises a new game and associated (game-specific) variables.
	**/
	public void newGame(){
		p = new Player();
		b = new BlueDot();
		dots = new ArrayList<GreenDot>();
		dots.add(new GreenDot());
		score = 0;
		powerUp = false;
		startTimer = false;
	}

	
	public void paint(Graphics g){
		gfx.setColor(Color.WHITE);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);

		if(menu){
			gfx.setColor(Color.BLACK);
			gfx.drawString("Dots", 340, 100);
			gfx.drawString("Press Enter to Begin", 310, 130);
			newGame();
		}
		else if(gameStarted){
		    p.draw(gfx);
			b.draw(gfx);
			for(GreenDot gd : dots){
				gd.draw(gfx);
			}
			gfx.setColor(Color.BLACK);
			gfx.drawString("Score", 100, 15);
			gfx.drawString(String.valueOf(score), 100, 30);
			gfx.drawString("High Score", 500, 15);
			gfx.drawString(String.valueOf(highscore), 500, 30);
			gfx.drawRect(165, 5, 300, 30);
			gfx.setColor(Color.BLUE);
			if(!startTimer){
				gfx.fillRect(166, 6, 299 * (score % 8) /8 , 29);
			}
			else{
				gfx.fillRect(166, 6, 299 * (3000 - (int) (Duration.between(start, now)).toMillis())/3000, 29);
			}
		}
		else if(!gameStarted){
			gfx.setColor(Color.BLACK);
			gfx.drawString("Game Over", 340, 100);
			gfx.drawString("Score: " + String.valueOf(old_score), 340, 115);
			gfx.drawString("Press Enter to Play Again", 340, 200);
			newGame();
		}
		g.drawImage(img, 0, 0, this);
	}

	/**
	* Updates the paint method to redraw the graphics display.
	**/
	public void update(Graphics g){
		paint(g);
	}

	
	public void run(){
		while(true){
			if(gameStarted){
			 	p.move();
				for(GreenDot gd : dots){
					gd.move();
				}
				
			 	if(p.checkCollision(b)){
			 		score++;
			 		if(score > highscore){
			 			highscore++;
			 		}
			 		if(score > 0 && score % 8 == 0){
			 			startTimer = true;
			 			powerUp = true;
			 			start = LocalTime.now();
			 			
			 		}
			 		else{
			 			powerUp = false;
			 		}
			 		b.redraw();
			 		dots.add(new GreenDot());
			 	}
			 	
			 	if(startTimer){
			 		checkPowerUpTimer(start);
			 	}
			 	
			 	Integer pos = 0;
			 	if(powerUp){
			 		nums = new ArrayList<Integer>();
			 	}
			 	
				for(GreenDot gd : dots){
					if(p.checkCollision(gd)){
						if(!powerUp){
							gameStarted  = false;
							old_score = score;
							break;
						}
						else{
							nums.add(pos);
						}
					}
					pos++;
				}
			}
			
				if(powerUp){
					for(Integer num : nums){
						dots.remove(num.intValue());
					}
			}

			repaint();

			try{
				Thread.sleep(10);
			} catch (InterruptedException e){
				e.printStackTrace();
			}

		}
	}
	
	/**
	* A private helper method that checks whether powerUp should still be
	* enabled. If so, do nothing, otherwise disable powerUp and stop the run
	* method from calling this method.
	**/
	private void checkPowerUpTimer(LocalTime start){
		now = LocalTime.now();
		if(now.isAfter(start.plusSeconds(3))){
			powerUp = false;
			startTimer = false;
		}
	}
	
	public void keyPressed(KeyEvent e){
		 if(e.getKeyCode() == KeyEvent.VK_UP){
		 	p.setUpAccel(true);
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_DOWN){
		 	p.setDownAccel(true);
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			 p.setLeftAccel(true);
		}
		 else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			 p.setRightAccel(true);
		}
	}

	public void keyReleased(KeyEvent e){
		 if(e.getKeyCode() == KeyEvent.VK_UP){
		 	  p.setUpAccel(false);
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_DOWN){
		 	  p.setDownAccel(false);
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			 	p.setLeftAccel(false);
		}
		 else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			 	p.setRightAccel(false);
		}
		 else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			 menu = false; 
			 gameStarted = true;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

}
