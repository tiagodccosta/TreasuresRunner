package Main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage player, block, dead, flag, coins, health;
	
	private SpriteSheet ss;
	
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
		
	}
	
	public void getTextures() {
		player = ss.grabImage(3, 1, 32, 32);
		block = ss.grabImage(1, 1, 32, 32);
		dead = ss.grabImage(2, 1, 32, 32);
		flag = ss.grabImage(4, 1, 32, 32);
		coins = ss.grabImage(5, 1, 32, 32);
		health = ss.grabImage(1, 2, 64, 64);
	}
	
}
