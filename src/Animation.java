

import java.awt.*;

public class Animation {

	Image[] image;
	
	int current = 0;
	int numImage;
	
	int duration;
	int countdown;
	int start = -1;

	int width = 1440; //default width
	int height = 900; //default height
	
	
	
	
	public Animation(String name, int duration, int n, String extension){
		image = new Image[n];
		numImage = n;
		this.duration = duration;
		countdown = duration;
		
		for(int i = 0; i < n; i++){
			image[i] = Toolkit.getDefaultToolkit().getImage(name + i + extension);
		}
	}
	public Animation(String name, int duration, int n, String extension, int w, int h){
		image = new Image[n];
		numImage = n;
		this.duration = duration;
		countdown = duration;
		width = w;
		height = h;
		for(int i = 0; i < n; i++){
			image[i] = Toolkit.getDefaultToolkit().getImage(name + i + extension);
		}
		
	}
	
	
	public void setDuration(int dur){
		this.duration = dur;
	}
	
	public Image getImage(){
		if(start != numImage){
			if(start == -1){
				countdown = 1;
			}
			countdown--;
			if(countdown == 0){
				countdown = 2;
				current++;
				if(current == image.length){
					current = 0;
				}
				start++;
			}
			return image[current];
			
		}
		countdown--;
		if(countdown == 0){
			countdown = duration;
			current++;
			if(current == image.length){
				current = 0;
			}
		}
		return image[current];
	}
	
	public Image getStaticImage(){
		
		return image[0];
		
	}
	 public void draw(Graphics g,int x, int y) {
		 
		 if (getImage() != null){ 
			 g.drawImage(getImage(), x, y,width,height, null);
		 }

	 }
		
	
}
