import java.awt.Graphics;
import java.util.Random;


/**
 * this class is a dumb ai character class that will move one direction or not at all. 
 * this class is not a character but rather an enemy that will draw itself at random times. 
 */
public abstract class DumbCharacter extends Character{
	
	static Random random = new Random(); //a random number
	int width, yLowerBound,yUpperBound, direction, frames, delay;
	int speed = getSpeed();
	String filename, extension;

	public DumbCharacter(String filename, String extension, int width, int yLowerBound, int yUpperBound, int direction, int frames, int delay) {
		super(filename, extension, width + random.nextInt(width*2), random.nextInt(yLowerBound - yUpperBound) + yUpperBound, direction, frames, delay);
		this.yLowerBound = yLowerBound;
		this.yUpperBound = yUpperBound;
		this.frame = frames;
		this.direction = direction;
		this.delay = delay;
		this.width = width;
	}

	public void drawInfinite(Graphics g, int n){
		
		if(this.x < Camera.x - 100){
			//made n sort of an offset
			this.x += random.nextInt(n) + width; //draw it somewhere else in the end
			//also change the y to make it more random looking
			this.y = random.nextInt(yLowerBound - yUpperBound) + yUpperBound;
		}
		super.draw(g);
	}
	
	
	public abstract int getSpeed();
	
	/*
	 * every dumb character class needs to have a way to return an array for a bunch of dumb characters
	 */
	public abstract DumbCharacter[] getDCArray(int n);
	
}
