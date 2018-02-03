

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject{
	
	Player player;

	public PlayerBullet(int x, int y, ID id) {
		super(x, y, id);
		velY = -10;
	}


	public void tick() {
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(x, y, 4, 8);
		g.fillRect(x, y, 4, 8);
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, 4, 8);
	}
	
	

}
