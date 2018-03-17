package dots_game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dots extends Applet implements Runnable, KeyListener{
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	Player p;
	boolean gameStarted;
	Graphics gfx;
	Image img;

	public void init(){
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p = new Player();
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
		score = 0;
	}

	public void paint(Graphics g){
		gfx.setColor(Color.WHITE);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);

	    p.draw(gfx);

		if(!gameStarted){
			gfx.setColor(Color.BLACK);
			gfx.drawString("Dots", 340, 100);
			gfx.drawString("Press Enter to Begin", 310, 130);
		}

		g.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g){
		paint(g);
	}

	public void run(){
		while(true){
			if(gameStarted){
			 	p.move();
			}

			repaint();

			try{
				Thread.sleep(10);
			} catch (InterruptedException e){
				e.printStackTrace();
			}

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
			  gameStarted = true;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

}
