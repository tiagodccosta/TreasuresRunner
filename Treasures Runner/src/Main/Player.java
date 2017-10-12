package Main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject{
	
	private Handler handler;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
    private Textures tex;

    private BufferedImage level1 = null;
    private BufferedImage level2 = null;
    private BufferedImage level3 = null;
    private BufferedImage level4 = null;
    private BufferedImage level5 = null;
    private BufferedImage level6 = null;
    private BufferedImage level7 = null;

    
        
	public Player(int x, int y, ID id, Handler handler, Textures tex) {
		super(x, y, id);
		this.handler = handler;
		this.tex = tex;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			level1 = loader.loadImage("/level1.png");
			level2 = loader.loadImage("/level2.png");
			level3 = loader.loadImage("/level3.png");
			level4 = loader.loadImage("/level4.png");
			level5 = loader.loadImage("/level5.png");
			level6 = loader.loadImage("/level6.png");
			level7 = loader.loadImage("/level7.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick(){
		
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		collision();
		
	}
	
	public void collision() {
		for(int i = 0; i < handler.go.size(); i++) {
			GameObject tempObject = handler.go.get(i);
			
			if(tempObject.getId() == ID.Block) {
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
			}
			

			if(tempObject.getId() == ID.Block) {
				if(getBounds ().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - 32;
					velY = 0;
					falling = false;
					jumping = false;
				}else 
					falling = true;
			}

			if(tempObject.getId() == ID.Block) {
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
				}
			}

			if(tempObject.getId() == ID.Block) {
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 32;
				}
			}
			
			if(tempObject.getId() == ID.Dead) {
				if(getBoundsTop().intersects(tempObject.getBounds())) {  
					if(Game.LEVEL == 1) {
						handler.clearLevel();
						handler.loadLevel(level1);
						Game.coinsCollected = 0;
						}
						if(Game.LEVEL == 2) {
							handler.clearLevel();
							handler.loadLevel(level2);
						}
				}
			}
			if(tempObject.getId() == ID.Dead) {
				if(getBounds().intersects(tempObject.getBounds())) {  
					if(Game.LEVEL == 1) {
						handler.clearLevel();
						handler.loadLevel(level1);
						Game.coinsCollected = 0;
						}
						if(Game.LEVEL == 2) {
							handler.clearLevel();
							handler.loadLevel(level2);
						}
						if(Game.LEVEL == 3) {
							handler.clearLevel();
							handler.loadLevel(level3);
						}
						if(Game.LEVEL == 4) {
							handler.clearLevel();
							handler.loadLevel(level4);
						}
						if(Game.LEVEL == 5) {
							handler.clearLevel();
							handler.loadLevel(level5);
						}
						if(Game.LEVEL == 6) {
							handler.clearLevel();
							handler.loadLevel(level6);
						}
						if(Game.LEVEL == 7) {
							handler.clearLevel();
							handler.loadLevel(level7);
						}
				}
			}
			
			if(tempObject.getId() == ID.Flag) {
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					handler.switchLevel();
				}
			}
			
			if (tempObject.getId() == ID.Coins) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					Game.coinsCollected++;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, x, y, null);
}	

	public Rectangle getBounds() {
		return new Rectangle((int)x + 16 - 8, (int)y + 16, 16, 16);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x + 16 - 8, (int)y, 16, 16);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, 5, 22);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x + 27, (int)y + 5, 5, 22);
	}
}
