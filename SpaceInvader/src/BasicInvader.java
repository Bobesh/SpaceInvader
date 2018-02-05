

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BasicInvader extends GameObject{
	
    Animation animation;
	private Handler handler;
	private Game game;
	private BufferedImage [] images = new BufferedImage[3]; 
	private boolean lowest = true;
	private boolean charged = true;
	
	Random r = new Random();
	private int shootTimer = r.nextInt(200);
	

	public int getShootTimer() {
		return shootTimer;
	}


	public void setShootTimer(int shootTimer) {
		this.shootTimer = shootTimer;
	}


	public BasicInvader(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		this.ss = ss;
		for(int i = 0; i < 3; i++){
			images[i] = ss.grabImage(i+1, 1, 32, 32);
		}	
		animation = new Animation(7, images);
		velX = 1;
	}


	public void tick() {
		x += velX;
		animation.runAnimation();
		if((int) game.clamp(x,  game.WIDTH - 32, 0) != x){
			y += 32;
			velX *= -1.5;
			animation.setSpeed(animation.getSpeed() - 1);
		}
		getDestroied();
		isLowest();
		isCharged();
		if(lowest && charged){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					if(tempObject.getX() + 64 > getX() && tempObject.getX() < getX() + 32){
						shoot();
						System.out.println("Shooting");
					}
				}
			}
		}
	}


	public void render(Graphics g) {
		animation.drawAnimation(g, x, y, 0);
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	private void getDestroied(){
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.PlayerBullet){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	private void isLowest(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicInvader){ // it is enemy
				if(tempObject.getX() > getX() - 16 && tempObject.getX() < getX() + 16 && tempObject.getY() > getY()){ // over gun // under me
					setLowest(false);
				}else setLowest(true);
			}
		}
	}
	
	private void isCharged(){
		if(shootTimer > 0) shootTimer --;
		else{
			charged = true;
		}
	}
	
	private void shoot(){
		handler.addObject(new EnemyBullet(x + 15, y + 32, ID.EnemyBullet));
		setShootTimer(r.nextInt(200));
		setCharged(false);
		
	}


	public void setLowest(boolean lowest) {
		this.lowest = lowest;
	}


	public void setCharged(boolean charged) {
		this.charged = charged;
	}

}
