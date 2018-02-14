

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	Handler handler;

	public Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		if(id == ID.EnemyBullet){
			velY = 20;
		}else if(id == ID.PlayerBullet){
			velY = -20;
		}else{
			velY = 1;
			System.out.println("Neplatne ID bullet");
		}
	}


	public void tick() {
		y += velY;
		getDestroied();
	}

	public void render(Graphics g) {
		
		if(id == ID.EnemyBullet){
			g.setColor(Color.red);
		}else if(id == ID.PlayerBullet){
			g.setColor(Color.green);
		}else{
			g.setColor(Color.white);
			System.out.println("Neplatne ID bullet");
		}
		
		g.drawRect(x, y, 4, 8);
		g.fillRect(x, y, 4, 8);
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, 4, 8);
	}
	
	private void getDestroied(){
		for(int i = 0; i < handler.object.size(); i++ ){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player || tempObject.getId() == ID.BasicInvader || tempObject.getId() == ID.Shield){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}
		if(y < 75 || y > 960){
			handler.removeObject(this);
		}
	}
	

}
