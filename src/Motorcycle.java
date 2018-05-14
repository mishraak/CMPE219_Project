
public class Motorcycle extends Character{

	static String filename = "Images/Character/Motorcycle/motorcycle";
	static String extension = ".png";
	int speed = 10;
	int turnSpeed = 10; //this speed is used for turning to make it smooth
	int life = 100; 
	int delay = 2; //used for speeding
	int currentTime = 0; //used with delay
	public Motorcycle() {
		super(filename, extension,700, 400, 1, 3,4);
		// TODO Auto-generated constructor stub
	}
	public void pressA1(boolean p){
		if(p){
			if(speed < 30){
				if(delay == currentTime){
					speed++;
					currentTime = 0;
				}
				currentTime++;
			}
			if(turnSpeed < 15){
				turnSpeed++;
			}
		}
		else {
			if(speed > 10){
				speed--;
			}
			if(turnSpeed > 10){
				turnSpeed--;
			}
			
		}
		
	}
	public void moveRt(){
		super.moveRt(speed);
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	

}
