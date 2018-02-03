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
	
	private SpriteSheet ss;
	private BufferedImage sprite_sheet = null;
	
	
	public Game(){
		
		handler = new Handler();
		hud = new HUD();
		
		new Window(WIDTH, HEIGHT, "Space invaders", this);
		start();
		
		this.addKeyListener(new KeyInput(hud, handler));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/monsters.png");
		ss = new SpriteSheet(sprite_sheet);
		
		handler.addObject(new Player(600, 820, ID.Player, this));
		handler.addObject(new BasicInvader(200, 200, ID.BasicInvader, handler, this, ss));
		handler.addObject(new BasicInvader(300, 300, ID.BasicInvader, handler, this, ss));
		
		
	}
	
	public void tick(){
		hud.tick();
		handler.tick();
		
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
		
		hud.render(g);
		handler.render(g);
		
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
	
	public static void main(String[] args){
		new Game();
	}
	
}
