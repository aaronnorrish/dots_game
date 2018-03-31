package dots_game;

import java.awt.Graphics;

/**
 * Dot interface implemented by the GreenDot and BlueDot classes
 **/
public interface Dot {
	public void draw(Graphics g);
	public int getCentreX();
	public int getCentreY();
}
