package Main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Flag extends GameObject{

	private Textures tex;

	public Flag(int x, int y, ID id, Textures tex) {
		super(x, y, id);
		this.tex = tex;
}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.flag, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
