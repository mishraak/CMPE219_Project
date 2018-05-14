import java.awt.Graphics;

public class CreditScreen {
	//static Background b = new Background(15);
	
	static int w;
	static int h;
	Credit c;
	public CreditScreen(int w, int h){
		this.w = w;
		this.h = h;
		c = new Credit(w,h);
	}
	public void draw(Graphics g){
		//b.draw(g, 0, 0);
		c.draw(g, 0, 0);
	}

}
