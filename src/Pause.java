import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Pause extends Animation{
	
	static String[] players = SaveGame.getAllNames();
	static int[] scores = SaveGame.getAllScores();

	public Pause(int width, int height) {
		super("Images/Pause/pause_", 10, 1 , ".png", width, height);
		// TODO Auto-generated constructor stub
	}
	
	//pause screen should have the score
	public void draw(Graphics g,int x,int y){
		super.draw(g, x, y);
		int stringY = (int)(height/3);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("Player Name", 570,stringY-20);
		g.drawString("Distance Covered", 710,stringY-20);
		for(int i = 0; i < players.length; i++){
			g.drawString(players[i] , 570,stringY);
			g.drawString(""+ scores[i], 710,stringY);

			stringY += 20;
		}
	}
	
	/**
	 * this should be handle somewhere else but for now it's here
	 * this method update the data
	 */
	public void updateData(){
		players = SaveGame.getAllNames();
		scores = SaveGame.getAllScores();
	}
	

}
