package Main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Coins extends GameObject{

	private Textures tex;

	public Coins(int x, int y, ID id, Textures tex) {
		super(x, y, id);
		this.tex = tex;
}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.coins, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
