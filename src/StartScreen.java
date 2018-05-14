import java.awt.Graphics;

public class StartScreen {
	
	static int width = 1440;
	static int height = 900;
	Title t;
	public StartScreen(int width, int height){
		this.width = width;
		this.height = height;
		t = new Title(width, height);

	}
	public void draw(Graphics g){
		t.draw(g, 0,-60);
	}
}
