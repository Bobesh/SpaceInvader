import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends GameObject{

	
	private Handler handler;
	
	
	public Shield(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}


	public void tick() {
	}


	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect(x, y, 10, 10);
		g.fillRect(x, y, 10, 10);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	

}
