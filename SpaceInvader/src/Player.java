

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Game game;

	public Player(int x, int y, ID id, Game game) {
		super(x, y, id);
		this.game = game;


	}

	public void tick() {
		x += velX;
		x = (int) game.clamp(x, game.WIDTH - 64,  0);
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 64, 32);
		g.fillRect(x + 27, y - 10, 10, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 32);
	}
	

}
