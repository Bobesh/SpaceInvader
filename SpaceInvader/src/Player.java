

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
	
	Handler handler;
	Game game;
	private HUD hud;
	
	private BufferedImage[] images = new BufferedImage[4];

	public Player(int x, int y, ID id, SpriteSheet ss, HUD hud, Game game, Handler handler) {
		super(x, y, id);
		this.ss = ss;
		this.hud = hud;
		this.game = game;
		this.handler = handler;

		
		images[0] = ss.grabImage(1, 1, 64, 32);
		images[1] = ss.grabImage(3, 1, 64, 32);
		images[2] = ss.grabImage(5, 1, 64, 32);
		images[3] = ss.grabImage(7, 1, 64, 32);
		
		

		
	}

	public void tick() {
		x += velX;
		y += velY;
		x = (int) game.clamp(x, game.WIDTH - 64,  0);
		collision();
	}

	public void render(Graphics g) {
		int charge = hud.getPlayerTimer();
		int chargeMax = hud.getMaxPlayerTimer();
		if(charge < (chargeMax / 3)){
			g.drawImage(images[0], x, y, null);
		} 
		
		else if(charge >= chargeMax/3 && charge < (chargeMax / 3)*2){			
			g.drawImage(images[1], x, y, null);
		}
		
		else if(charge >= ((chargeMax/3)*2) && charge < chargeMax){
			g.drawImage(images[2], x, y, null);
		}
		
		else if(charge == chargeMax){
			g.drawImage(images[3], x, y, null);
		}
		else{
			System.out.println(charge);
		}
		
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
