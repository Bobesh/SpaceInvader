

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int maxPlayerTimer = 100;
	private int playerTimer = maxPlayerTimer;
	private int enemyTimer = 0;
	
	public void tick(){
		playerTiming();
	}
	
	public void render(Graphics g){
		//line
		g.setColor(Color.orange);
		g.drawLine(0, 50, 1280, 50);
		
		//player timer
		
		g.setColor(Color.gray);
		g.fillRect(50, 5, 100, 30);
		
		g.setColor(Color.blue);
		g.fillRect(50, 5, playerTimer , 30);
		
		g.setColor(Color.GREEN);
		g.drawRect(50, 5, 100, 30);
		
		

		
	}

	public int getMaxPlayerTimer() {
		return maxPlayerTimer;
	}

	public void setMaxPlayerTimer(int maxPlayerTimer) {
		this.maxPlayerTimer = maxPlayerTimer;
	}

	public int getPlayerTimer() {
		return playerTimer;
	}

	public void setPlayerTimer(int playerTimer) {
		this.playerTimer = playerTimer;
	}

	public int getEnemyTimer() {
		return enemyTimer;
	}

	public void setEnemyTimer(int enemyTimer) {
		this.enemyTimer = enemyTimer;
	}
	
	private void playerTiming(){
		if(playerTimer < maxPlayerTimer){
			playerTimer += 1;
		}
	}

}
