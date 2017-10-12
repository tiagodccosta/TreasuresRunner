package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX(); 
		int my = e.getY();
		
		/*
		public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 200, 200, 80);
		public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 100, 350, 200, 80);
		public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 100, 500, 200, 80);
	*/
		
		if(Game.State != States.Game) {
			
			//Play
			if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
				if(my >= 200 && my <= 280) {
					Game.State = States.Game;
					}						
				}
			
			//Help
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 350 && my <= 430) {
							Game.State = States.Help;
					}
				}
			//Exit
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 500 && my <= 580) {
						System.exit(1);
					}
				}
			//Back
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 600 && my <= 680) {
						Game.State = States.Menu;
					}
				}
			}
		
		}	
	}
