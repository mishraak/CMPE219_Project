import java.util.Vector;

public class ControlManager {

	static Vector<Control> allControls = getAllControls();
	static int numControls = numControls();
	
	/**
	 * this method populates the vector for all controls 
	 * @return 
	 */
	public static Vector<Control> getAllControls(){
		Vector<Control> returnV = new Vector();
		//first the basic controls (keyboard and mouse)
		returnV.add(new KeyboardControl());
		
		for(int i = 0; i < PS3Controller.pos.size(); i++){
			returnV.add(new PS3Control(i+1));
		}
		return returnV;
	}
	
	/**
	 * this method gets the total number of controls
	 * @return size of the vector containing all controls
	 */
	public static int numControls(){
		return allControls.size();
	}
	
	
}
