

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	Handler handler;
	BulletID shooter;

	public Bullet(int x, int y, ID id, BulletID shooter, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.shooter = shooter;
		
		if(shooter == BulletID.EnemyBullet){
			velY = 10;
		}else if(shooter == BulletID.PlayerBullet){
			velY = -10;
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
		
		if(shooter == BulletID.EnemyBullet){
			g.setColor(Color.red);
		}else if(shooter == BulletID.PlayerBullet){
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
		if(x < 75 || x > 960){
			handler.removeObject(this);
		}
	}
	

}
