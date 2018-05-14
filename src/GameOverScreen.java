import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class GameOverScreen extends Animation{

	public GameOverScreen(int width, int height){
		super("Images/GameOver/gameover_", 10, 1 , ".png", width, height);
	}
	
	
	//needs to be fix for different sizes
	public void draw(Graphics g,int x,int y){
		super.draw(g, x, y);
		//get some code from pause screen
		int stringY = (int)(320);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("Player Name", 570,stringY-20);
		g.drawString("Distance Travel", 710,stringY-20);
		for(int i = 0; i < Pause.players.length; i++){
			g.drawString(Pause.players[i] , 570,stringY);
			g.drawString(""+ Pause.scores[i], 710,stringY);

						stringY += 20;
		}
	}
}
