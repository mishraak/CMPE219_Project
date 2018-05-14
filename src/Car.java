import java.awt.Graphics;

public class Car extends DumbCharacter{
	static String filename = "Images/Car/moon", extension = ".png";
	static int xbound = Camera.x, ybound = 0-Camera.y, x2bound, y2bound;
	int middle;
	int speed = getSpeed();

	public Car() {
		super(filename,extension, 1440,900, 0, 4, 4, 4);
		this.middle = 450;
	}
	
	public Car(int width, int yLowerBound, int yUpperBound,int middle){
		super(filename,extension, width ,yLowerBound,yUpperBound, 4, 4, 16);
		this.middle = middle;		
	}

	public int getSpeed() {
		return this.random.nextInt(10-4) + 4;
		}


	@Override
	public DumbCharacter[] getDCArray(int quantity) {
		Car[] returnArray = new Car[quantity];
		for(int i = 0; i < quantity; i++){
				returnArray[i] = new Car(width,yLowerBound,yUpperBound,middle);
		}
		return returnArray;
	}
	
		public void drawInfinite(Graphics g, int n, boolean pause){
				if(!pause){
		if(this.y > middle){
			speed = this.random.nextInt(7-4) + 4;
			
			this.moveLt(speed);
		}
		else this.moveRt(speed);
		
		}
		super.drawInfinite(g, n);

	}
	
	
}
