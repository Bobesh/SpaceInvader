/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = 2747436608876328919L;
	public final int WIDTH = 1280, HEIGHT = 960 ;
	
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private HUD hud;
	private StarSpawner spawner;
	
	private SpriteSheet ss_enemy;
	private SpriteSheet ss_player;
	
	private BufferedImage sprite_sheet_enemy = null;
	private BufferedImage sprite_sheet_player = null;
	
	private TextSpawner textSpawner;
	public STATE gameState = STATE.text;
	
	
	public Game(){
		
		spawner = new StarSpawner();
		handler = new Handler();
		hud = new HUD();
		textSpawner = new TextSpawner();
		spawner = new StarSpawner();
		
		new Window(WIDTH, HEIGHT, "Space invaders", this);
		start();
		
		
		this.addKeyListener(new KeyInput(hud, handler, this));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet_enemy = loader.loadImage("/monsters.png");
		ss_enemy = new SpriteSheet(sprite_sheet_enemy);
		
		sprite_sheet_player = loader.loadImage("/ship.png");
		ss_player = new SpriteSheet(sprite_sheet_player);
		
		
		
		
		
		//generate handler objects outside the window
		handler.addObject(new Player(600, 820 + 300, ID.Player, ss_player, hud, this, handler));
		handler.addObject(new BasicInvader(200, 100 - 300, ID.BasicInvader, handler, this, ss_enemy));
		handler.addObject(new BasicInvader(300, 200 - 300, ID.BasicInvader, handler, this, ss_enemy));
		
	    handler.addObject(new Shield(100, 770, ID.Shield, handler));
		handler.addObject(new Shield(120, 770, ID.Shield, handler));
		handler.addObject(new Shield(140, 770, ID.Shield, handler));
		handler.addObject(new Shield(100, 750, ID.Shield, handler));
		handler.addObject(new Shield(120, 750, ID.Shield, handler));
		handler.addObject(new Shield(140, 750, ID.Shield, handler));
		handler.addObject(new Shield(100, 730, ID.Shield, handler));
		handler.addObject(new Shield(120, 730, ID.Shield, handler));
		handler.addObject(new Shield(140, 730, ID.Shield, handler));
		
		
		
		//set velY for handler objects
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				tempObject.setVelX(0);
				tempObject.setVelY(-2);
			}
			else if (tempObject.getId() == ID.BasicInvader){
				tempObject.setVelX(0);
				tempObject.setVelY(2);
			}
		}
		
		
	}
	
	public void tick(){
		if(gameState == STATE.text){
			textSpawner.tick();
			if(textSpawner.getLastY() < 0){
				gameState = STATE.arrival;
			}
		}
		else if(gameState == STATE.arrival){
			
			spawner.tick();
			handler.tick();
			hud.tick();
			
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					if(tempObject.getY() == 820){
						gameState = STATE.game;
						tempObject.setVelY(0);
						for(int j = 0; j < handler.object.size(); j++){
							GameObject enemy = handler.object.get(j);
							if(enemy.getId() == ID.BasicInvader){
								enemy.setVelX(1);
								enemy.setVelY(0);
							}
						}
						return;
					}
				}
			}
		}
		
		else if (gameState == STATE.game){
			spawner.tick();
			handler.tick();
			hud.tick();
		}
		
	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(gameState == STATE.text){
			textSpawner.render(g);
		}
		else if(gameState == STATE.game || gameState == STATE.arrival){
			spawner.render(g);
			handler.render(g);
			hud.render(g);
		}
		
		
		g.dispose();
		bs.show();
		
	}
	
	private void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private void stop(){
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}


	public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;
                            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
		
        }
        stop();
	}
	
	public float clamp(float var, float max, float min){
		if(var > min && var < max){
			return var;
		}
		else if(var <= min){
			return min;
		}
		else{
			return max;
		}
	}
	
	/*
	public static void main(String[] args){
		new Game();
	}
	*/
}
