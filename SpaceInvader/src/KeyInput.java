

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	HUD hud;
	Handler handler;
	Sound blast;
	private boolean [] keyDown = new boolean[2]; // 0 = left, 1 = right
	
	public KeyInput(HUD hud, Handler handler){
		this.hud = hud;
		this.handler = handler;
		blast = new Sound("/blast.wav");
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-handler.spd);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(handler.spd);
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_SPACE){
					if(hud.getPlayerTimer() == hud.getMaxPlayerTimer()){
						blast.play();
						handler.addObject(new Bullet(tempObject.getX() + 30, tempObject.getY() - 10, ID.PlayerBullet, BulletID.PlayerBullet, handler));
						hud.setPlayerTimer(0);
					}
				}
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_LEFT){
					keyDown[0] = false;
					if(!keyDown[1]) tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_RIGHT){
					keyDown[1] = false;
					if(!keyDown[0]) tempObject.setVelX(0);
				}
				
			}
		}

	}

}
