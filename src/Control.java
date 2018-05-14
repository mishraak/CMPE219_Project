public abstract class Control{

	
	//basic Controls
	boolean Up = false;
	boolean Down = false;
	boolean Left = false;
	boolean Right = false;
	//action keys (X,O,SQUARE, TRIANGLE, etc)
	//for now i'll leave it as 4 buttons (mainly cause this is a basic class)
	boolean ACT1 = false;
	boolean ACT2 = false;
	boolean ACT3 = false;
	boolean ACT4 = false;
	//pause, select,
	boolean Select = false;
	boolean Start = false;
	
	public Control(){
	}
	public boolean pressUp() {
		return Up;
	}
	public boolean pressDown(){
		return Down;
	}
	public boolean pressLeft(){
		return Left;
	}
	public boolean pressRight(){
		return Right;
	}
	public boolean pressAct1(){
		return ACT1;
	}
	public boolean pressAct2(){
		return ACT2;
	}
	public boolean pressAct3(){
		return ACT3;
	}
	public boolean pressAct4(){
		return ACT4;
	}
	public boolean pressSelect(){
		return Select;
	}
	public boolean pressStart(){
		return Start;
	}
}
