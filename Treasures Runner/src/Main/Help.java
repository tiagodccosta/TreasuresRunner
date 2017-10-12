package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	public Rectangle backButton = new Rectangle(Game.WIDTH / 2 - 100, 600, 200, 80);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 40);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Hit the keys A,W,S,D for move the player!", 230, 120);
		g.setColor(new Color(255, 203,17));
		g.drawString("Hey Treasure Runner,", 400, 200);
		g.drawString("you will need to be fast to save your beloved princess", 100, 246);
		g.drawString("and the GIANT TREASURE from the hands of the evil Dark Lord", 10, 290);
		g.drawString("The Leliator, the King of this demoniac castle, where", 100, 340);
		g.drawString("he keeps your love prisoneer and where he keeps all", 100, 386);
		g.drawString("of his GOLD!", 460, 436);
		g.drawString("You are on  mission Runner!", 320, 486);
		g.drawString("Save the princess and conquer the Treasure!", 160, 536);

		
		g.setColor(new Color(105,205,95,120));
		g.fillRect(backButton.x, backButton.y, 200, 80);
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		
		g.drawString("Back", backButton.x + 40, backButton.y + 55);
		g2d.draw(backButton);
	}
}
