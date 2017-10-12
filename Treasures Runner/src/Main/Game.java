package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	private final String titulo = "Treasures Runner";
	
	private int frames = 0;
	private int updates = 0;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private BufferedImage level = null;
	private BufferedImage SpriteSheet = null;
	private BufferedImage back = null;
	
	private Handler handler;
	private Cam cam;
	private Textures tex;
	private Menu menu;
	private Help help;
	
	public static States State = States.Menu;
	
	public static int LEVEL = 1;
	private  int secondsPassed = 0;
	
    public static int coinsCollected = 0;
    
    public static int HEALTH = 3;
    
    
	private Timer timer = new Timer();
	
	public void init() {
		this.requestFocus();
		
		timer.scheduleAtFixedRate(task, 1000, 1000);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			SpriteSheet = loader.loadImage("/SpriteSheet.png");
			level = loader.loadImage("/level1.png");
			back = loader.loadImage("/back3.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cam = new Cam(0, 0);
		tex = new Textures(this);
		
		handler = new Handler(tex, cam);
		
		menu = new Menu();
		help = new Help();
		
		addKeyListener(new KeyInput(handler));
		addMouseListener(new MouseInput());
		
		handler.loadLevel(level);
		
	}

	
	TimerTask task = new TimerTask() {
		
		public void run() {
			secondsPassed++;
		}
		
	};
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				frames++;
				delta--;
			}
			render();
			updates++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	
	public void tick() {
		if(State == States.Game){
		for( int i = 0; i < handler.go.size(); i++) {
			if(handler.go.get(i).getId() == ID.Player) {
				cam.tick(handler.go.get(i));
			}
		}
		handler.tick();
	}
}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////////////////////////////////////////////////	
		
		g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
		
		if(State == States.Game) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(-cam.getX(), -cam.getY());
		
		handler.render(g);
		
		g2d.translate(cam.getX(), cam.getY());
		
		Font f = new Font("arial", Font.PLAIN, 40);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Level: " + LEVEL, 1050, 680);
		
		Font f1 = new Font("arial", Font.PLAIN, 40);
		g.setFont(f1);
		g.setColor(Color.WHITE);
		g.drawString("Timer: " + secondsPassed, 960, 30);
		
		g.setColor(new Color(255, 203,17));
		g.drawString("Gold: " + coinsCollected , 850, 680);
			
		}else if(State == States.Menu) {
			menu.render(g);
		}else if(State == States.Help) {
			help.render(g);
		}
		/////////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if(isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if(!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	public static void main(String []args) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(game.titulo);
		
		frame.add(game);
		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return SpriteSheet;
	}
	
}
