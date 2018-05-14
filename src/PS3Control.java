
public class PS3Control extends Control{
	
	private PS3Controller control;

	/**
	 * This constructor is default for player 1
	 */
	public PS3Control(){
		control = new PS3Controller(1);
	}
	/**
	 * this constructor can be for any player
	 * @param player
	 */
	public PS3Control(int player){
		//this can be any player
		control = new PS3Controller(player);
	}
	
	//all the following classes override the super
	public boolean pressUp() {
		return control.isPressed(PS3Controller.UP);
	}
	public boolean pressDown(){
		return control.isPressed(PS3Controller.DOWN);
	}
	public boolean pressLeft(){
		return control.isPressed(PS3Controller.LEFT);
	}
	public boolean pressRight(){
		return control.isPressed(PS3Controller.RIGHT);
	}
	public boolean pressAct1(){
		return control.isPressed(PS3Controller.SQUARE);
	}
	public boolean pressAct2(){
		return control.isPressed(PS3Controller.TRIANGLE);
	}
	public boolean pressAct4(){
		return control.isPressed(PS3Controller.X);
	}
	public boolean pressAct3(){
		return control.isPressed(PS3Controller.CIRCLE);
	}
	public boolean pressSelect(){
		return control.isPressed(PS3Controller.SELECT);
	}
	public boolean pressStart(){
		return control.isPressed(PS3Controller.START);
	}
	public float getLX(){
		return control.joystick(PS3Controller.L_X);
	}
	public float getLY(){
		return control.joystick(PS3Controller.L_Y);
	}
	public float getRX(){
		return control.joystick(PS3Controller.R_X);
	}
	public float getRY(){
		return control.joystick(PS3Controller.R_Y);
	}
	
	
	


}
