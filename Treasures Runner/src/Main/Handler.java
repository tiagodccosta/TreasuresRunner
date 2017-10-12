package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Handler {

	protected LinkedList<GameObject> go = new LinkedList<GameObject>();
	
	private boolean up = false, down = false, right = false, left = false;
	
	private Textures tex;
	private Cam cam;
	
    private BufferedImage level2 = null;
    private BufferedImage level3 = null;
    private BufferedImage level4 = null;
    private BufferedImage level5 = null;
    private BufferedImage level6 = null;
    private BufferedImage level7 = null;

	
	public Handler(Textures tex, Cam cam) {
		this.tex = tex;
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
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

	public void tick() {
		for(int i =0; i < go.size(); i++) {
			GameObject tempObject = go.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i =0; i < go.size(); i++) {
			GameObject tempObject = go.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void loadLevel(BufferedImage image) {
			
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255)
				addObject(new Block(xx * 32, yy * 32, ID.Block, tex));
				
				if(blue == 255)
				addObject(new Player(xx * 32, yy * 32, ID.Player, this, tex));
				
				if(green == 255)
				addObject(new Dead(xx * 32, yy * 32, ID.Dead, this, tex));
				
				if(red == 254 && green == 216 && blue == 0)
					addObject(new Flag(xx * 32, yy * 32, ID.Flag, tex));
			
				if(red == 120 && green == 117 && blue == 197)
					addObject(new Coins(xx * 32, yy * 32, ID.Coins, tex));
				}
			}
		}
	
	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL) {
		
		case 1:
			
			loadLevel(level2);
			
			break;
			
		case 2: 
			
			loadLevel(level3);
			
			break;
			
		case 3:
			
			loadLevel(level4);
			
			break;
			
		case 4:
			loadLevel(level5);
			
			break;
			
		case 5:
			
			loadLevel(level6);
			
			break;
			
		case 6:
			
			loadLevel(level7);
			
			break;
		}
		Game.LEVEL++;
	}
	
	
	public void clearLevel() {
		go.clear();
	}

	public void addObject(GameObject tempObject) {
		go.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		go.remove(tempObject);
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
}
