import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class HUD {

	static Image[] hud = loadImageSet();
	int width, height;
	
	public HUD(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	  public static Image[] loadImageSet()
	   {
	      Image[] img = new Image[61];

	      for (int i = 0; i < 61; i++){
	         img[i] = Toolkit.getDefaultToolkit().getImage("Images/HUD/HUD_" + i + ".gif");
	      }
	      return img;
	   }
	   
	  
	  public void draw(Graphics g, int speed){
		  g.drawImage(hud[speed], 750,550,null);
	  }
	
}
