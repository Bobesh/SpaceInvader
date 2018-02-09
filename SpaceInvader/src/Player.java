

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	Game game;

	public Player(int x, int y, ID id, Game game, Handler handler) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;


	}

	public void tick() {
		x += velX;
		y += velY;
		x = (int) game.clamp(x, game.WIDTH - 64,  0);
		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 64, 32);
		g.fillRect(x + 27, y - 10, 10, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 32);
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.EnemyBullet){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}
	}

}
