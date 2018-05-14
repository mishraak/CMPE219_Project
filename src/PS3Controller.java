import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import java.util.Vector;


public class PS3Controller {
	//the buttons int represents the position on the Controller.Component array
	final static int SELECT   	  = 4;
	final static int L3           = 5;
	final static int R3           = 6;
	final static int START        = 7;
	final static int UP           = 8;
	final static int LEFT         = 11;
	final static int DOWN         = 10;
	final static int RIGHT        = 9;
	final static int L2           = 12;
	final static int R2           = 13;
	final static int L1           = 14;
	final static int R1           = 15;
	final static int TRIANGLE     = 16;
	final static int CIRCLE       = 17;
	final static int X            = 18;
	final static int SQUARE       = 19;
	final static int PS_BUTTON    = 20;
	
	//the next are the joystick left and right 
	//each have their own x and y
	final static int L_X = 0;
	final static int L_Y = 1;
	final static int R_X = 2;
	final static int R_Y = 3;

	//represents the controller for the player
	private Controller ps3R;	
	//represents the components for the controller 
	private Component[] allComp;	
	//all positions for all (PS3)remotes
	static Vector<Integer> pos = pos();

	
	/**
	 * This is the constructor 
	 * takes in the player number
	 */
	public PS3Controller(int player){
		ps3R = getControl(player);
		//put a check null just in case no controller exist and prevent errors
		if(!(ps3R == null)){
			allComp = ps3R.getComponents();
		}
	}
	
	
	/**
	 * this method gives the position of all the (ps3) controller 
	 * that exist in the ControllerEnvironment
	 * @return an int vector with the position of all (ps3) controllers
	 */
	private static Vector<Integer> pos(){
		Vector<Integer> rV = new Vector();
		//get all controllers first
		Controller[] allControl = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(int i = 0; i < allControl.length; i++){
			if(allControl[i].getName().equals("PLAYSTATION(R)3 Controller")){
				rV.add(i);
			}
		}
		return rV;
		//return rList;
	}
	
	/**
	 * a private class to get the controller depending on the player
	 * @return the first occurrence of the ps3 remote
	 */
	private static Controller getControl(int player){
				//just for checking if within range
				int numControlls = pos.size();
				//first get all your remote
				Controller[] controlA = ControllerEnvironment.getDefaultEnvironment().getControllers();
				//check if the player chosen is within the range /or numControlls is no zero
				//should be handled differently
				if(player > numControlls || player < 0 || numControlls == 0){
					return null;
				}
				return controlA[pos.elementAt(player - 1)];
	}
	
	/**
	 * this method return true or false if the button is pressed
	 * @param key is the int position of the button 
	 * usually used w/ PS3Remote.(button) ex. isPressed(PS3Remote.SELECT);
	 * @return true or false if the key is pressed or false if its analog
	 */
	//cannot be analog, will return false
	public  boolean isPressed(int key){
		//always have to check poll (its similar to checking event in keylistener or addKeyListener())
		ps3R.poll();
		//checks if if its a button (not analog) and checks if button is pressed
		return (!allComp[key].isAnalog()) && (allComp[key].getPollData()==1.0f);
	}
	
	/**
	 * this method finds the value for x or y on the left or right joystick
	 * @param axis the part of the joystick (x or y on left or right)
	 * @return the value of that part
	 */
	public  float joystick(int axis){
		//always poll data
		ps3R.poll();
		float data = allComp[axis].getPollData();
		//check if its analog
		//the rest got from the example given from 
		//checks if everything is a deadzone
		///should be handled later (exception)
		if(!allComp[axis].isAnalog() || allComp[axis].getDeadZone() >= Math.abs(data)){
			return (float)-2;
		}

		return data;
	}
	
}
