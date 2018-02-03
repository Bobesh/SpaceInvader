

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public int spd = 5;
	LinkedList<GameObject> object= new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ID.PlayerBullet && tempObject.getY() < 75){
				removeObject(tempObject);
			}
			else{
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject tempObject){
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject){
		object.remove(tempObject);
	}
	
	

}
