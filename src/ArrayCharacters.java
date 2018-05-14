
public class ArrayCharacters {
	int width = 1440, height = 900; //size of screen
	int roadUp = 288, roadDown = 438; //the lower and upper bound of the road
	int grassUp = 438, grassDown = 900; //the lower and upper bound for the grass part
	int skyUp, skyDown; //the lower and upper bound for the grass part
	
	
	//this constructor leaves the defaults
	public ArrayCharacters(){
	}
	//this constructor changes the defaults
	public ArrayCharacters(int width, int height, int roadUp, int roadDown, 
			int grassUp, int grassDown, int skyUp, int skyDown){
		this.width = width;
		this.height = height;
		this.roadUp = roadUp;
		this.roadDown = roadDown;
		this.grassUp = grassUp;
		this.grassDown = grassDown;
		this.skyDown = skyDown;
		this.skyUp = skyUp;
	}
	
	

}
