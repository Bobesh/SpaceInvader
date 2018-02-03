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
		getDestroied();
	}


	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect(x, y, 5, 5);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, 5, 5);
	}
	
	private void getDestroied(){
		for(int i = 0; i < handler.object.size(); i++ ){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.EnemyBullet){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(this);
				}
			}
		}
	}

}
