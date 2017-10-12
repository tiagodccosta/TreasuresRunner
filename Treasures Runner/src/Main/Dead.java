package Main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Dead extends GameObject{
	
	private Textures tex;
	
	public Dead(int x, int y, ID id, Handler handler, Textures tex) {
		super(x, y, id);
		this.tex = tex;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.dead, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
