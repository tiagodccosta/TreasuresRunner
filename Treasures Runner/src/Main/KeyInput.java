package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Handler h;
	
	public static States State = States.Menu;
	
	public KeyInput(Handler h) {
		this.h = h;
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < h.go.size(); i++) {
			GameObject tempObject = h.go.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W && !tempObject.isJumping())
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-11);
				}
				//if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
			}
			
			if(key == KeyEvent.VK_ESCAPE) {
				Game.State = States.Menu;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < h.go.size(); i++) {
			GameObject tempObject = h.go.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				//if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}
	}
}
