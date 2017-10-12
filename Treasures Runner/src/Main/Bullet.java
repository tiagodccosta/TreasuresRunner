package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler, int mx, int my) {
		super(x, y, id);
		this.handler = handler;
		
		velX = (mx - x) / 10;
		velY = (my - y) / 10;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.go.size(); i++) {
			GameObject tempObject = handler.go.get(i);
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 10, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}

}
