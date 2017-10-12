package Main;

public class Cam {

	private float x, y;
	
	public Cam(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object) {
		x += ((object.getX() - x) - 1200 / 2) * 0.05f;
		y += ((object.getY() - y) - 700 / 2) * 0.05f;
		
		if(x <= 0) x = 0;
		if(y <= 0) y = 0;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
