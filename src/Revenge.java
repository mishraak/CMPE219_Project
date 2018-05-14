import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBoxMenuItem; //check box menu
import javax.swing.JFrame; //window
import javax.swing.JMenu; //menu
import javax.swing.JMenuBar; //menu bar
import javax.swing.JMenuItem; //menu item
import javax.swing.JRadioButtonMenuItem; //radio menu button
import javax.swing.JOptionPane;

public class Revenge extends JFrame implements Runnable {
	
	BufferedImage backBuffer;
	//the following booleans are for which screen to show
	boolean pause = true;
	boolean start = true;
	boolean credit = false;
	boolean playerSelect = false;
	
	//screens
	StartScreen startScreen;
	CreditScreen creditScreen;
	Background background;
	GameBackground gameB;
	Pause pauseScreen;
	GameOverScreen gameover;
	
	//this is used for full screen
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final int fullW = (int) screenSize.getWidth();
	final int fullH = (int) screenSize.getHeight();

	//dimensions of the game
	//int width = fullW, height = fullH;
	int width = 1440;
	int height = 900;
		

	//keyboard and other controls
	KeyboardControl k = new KeyboardControl();
	//PS3Control k = new PS3Control();
	
	//time and delays
	int delay; //delay for button presses
	
	//player info used for csv game save
	String player; //name of the current player
	int row; //row of where the player exist
	int currDistance;
	int distance; 
	int distanceDelay = 0;
	int lives = 3; //lives of character
	int immunity = 300; //delay for immunity
	boolean updateCSV = false; //boolean to know if changes where made in the scores
	boolean crash = false; //crash boolean 

	
	//sprites and character objects
	Motorcycle motorcycle;
	GreenGuyReborn greenDude = new GreenGuyReborn(width,665,288,445);
	Car car = new Car(width,665,288,445);;
	GreenGuyReborn[] greenDudes;
	Car[] cars;

	HUD hud;
	
	
	public static void main(String[] args) {
		Revenge game = new Revenge(); 
		game.init();
        game.run(); 

        System.exit(0); 
	}

	public void draw() {
		
		Graphics g = getGraphics();
		//buffer 
		Graphics buffer = backBuffer.getGraphics(); 
		buffer.setColor(Color.WHITE); 
		buffer.fillRect(0, 0, width, height);
		buffer.setColor(Color.black);
		//start screen draw and credit screen draw
		background.draw(buffer, 0, 0);

		if(start){
			//background.draw(buffer, 0, 0);
			if(credit){ //this is the select screen (setting/credit/info screen)
				creditScreen.draw(buffer); //display credits
					if(playerSelect){ //this should be handled somewhere else
						player = JOptionPane.showInputDialog("Current Player: " + player);
						if(player == null || player.equals("")){ //avoid empty box
							player = "Player1";
						}
						playerSelect = false;
						k.Start = false; //there is a bug here where it gets stuck in true without this (will fix later for now this is quick fix)
					}
			}
			else{
				startScreen.draw(buffer); 
			}
		}
		//start of game screens draw 
		else if(lives > 0){
	
			gameB.draw(buffer,pause); //game backgrounds
			
			//text on screen
			Font fnt=new Font("Arial",10,50);//setting Font size and style
			buffer.setFont(fnt);
			buffer.drawString("Lives: " + lives, 20, 770);
			//buffer.drawString("Distance: " + distance, 20, 790);
			
			//handle crash drawing (flicker for now)
			if(immunity < 200){
				if(immunity % 3 == 0){
					motorcycle.draw(buffer); //motorcycle 
				}
				buffer.drawString("Immunity: " + (200-immunity), 20, 810);
				buffer.setColor(Color.red);
				MP3.playSound();			
			}
			//no crash
			else {
				buffer.setColor(Color.BLACK);
				motorcycle.draw(buffer); //motorcycle 
			}

	
			hud.draw(buffer, motorcycle.speed); //the display
			
			//
			for(int i = 0; i < greenDudes.length; i++){
				if(greenDudes[i] != null){
					greenDudes[i].drawInfinite(buffer, 10000,pause);
				}
			}
			if(distance > 200){
			for(int i = 0; i < cars.length; i++){
				if(cars[i] != null){
					cars[i].drawInfinite(buffer, 30000,pause);
				}
			}
			}
			
			//pause screen draw
			if(pause){
				pauseScreen.draw(buffer, 0, 0);
				if(k.pressSelect() && delay > 30){
					player = JOptionPane.showInputDialog("Current Player: " + player);
					row = SaveGame.getPlayerRow(player);
					updateCSV = true;
					delay = 0;
					k.Select = false; //another bug not sure if this statys stuck but just in case
					this.restart();
					
				}
			}
			
		}
		//this is where everything should get restarted
		else if(lives < 1){
			gameover.draw(buffer, 0, 0);
		}
		
		
		g.drawImage(backBuffer, 0, 0, this);
		
		

	}
	
	/**
	 * this method handles all the update stuff
	 */
	public void update() {
		
		delay++;
		
		//this is to switch player in credit screen
		if(credit && k.pressStart() && delay > 30){
			playerSelect = true;
			delay = 0;
		}
		//start the game and pause the game
		else if(k.pressStart() && delay > 30){
			start = false;
			pause = !pause; 
			delay = 0;
		}
		//switch to credit screen 
		if(start && k.pressSelect() && delay > 30){
			credit = !credit;
			delay = 0;
		}
		//if you are in the game this is where the controls go
		if(start == false){
			if(!pause && lives > 0){
			immunity++;
			distanceDelay++;
			//not a good way to calculate distance but for now its ok
			if(distanceDelay == 50){
				distance+=motorcycle.speed;
				distanceDelay = 0;
			}
			
			
			//automatic movements
			Camera.moveBy(10, 0); //will change later this is the speed of the motorcycle
			motorcycle.moveRt();
			//green dudes and all DumbCharacters move in their classes automatically
			
			//key presses movements
			motorcycle.pressA1(k.pressAct1()); //change the speed should be clean later (shouldn't be here)
			if(k.pressAct1()){
				if(motorcycle.x >= Camera.x + 700){
					Camera.moveBy(20, 0);
				}
			}
			else if(motorcycle.x <= Camera.x){ //move the motorcycle automatically
				motorcycle.setSpeed(10);
			}
			else{
				//this block shouldn't be here or should it?
				if(motorcycle.speed>3){
					motorcycle.speed--;
				}
				if(motorcycle.turnSpeed>3){
					motorcycle.turnSpeed--;
				}
				motorcycle.setSpeed(motorcycle.speed);
			}			
			if(k.pressUp()){
				if(motorcycle.y >= 288){
					motorcycle.moveUp(motorcycle.turnSpeed);
				}

			}
			if(k.pressDown()) {
				if(685 > motorcycle.y){
					motorcycle.moveDn(motorcycle.turnSpeed);
				}
				else{
					motorcycle.y = 685;
				}
			}
			
			//finally check collision, for now using a loop but should be fix to be more efficient later
			for(int i = 0; i < greenDudes.length; i++){
				//crash happens
				if(greenDudes[i] != null){
					if(immunity > 200 && motorcycle.hasCollided(greenDudes[i])){
						lives--;
						immunity = 0; //activate the immunity
						greenDudes[i] = null;
						motorcycle.speed =10;
						
					}
				}
			}
			if(distance > 200){
			for(int i = 0; i < cars.length; i++){
				//crash happens
				if(cars[i] != null){
					if(immunity > 200 && motorcycle.hasCollided(cars[i])){
						lives--;
						immunity = 0; //activate the immunity
						cars[i] = null;
						motorcycle.speed =10;
						
					}
				}
			}
			}
			if(lives == 0 ) updateCSV = true;
			}
			
			
			//end screen actions this is where things shoud be update in the csv also reset everything
			if(lives < 1){
				if(updateCSV){
					currDistance = distance+currDistance;
					SaveGame.setScoreSorted(currDistance, row);
					pauseScreen.updateData();
					updateCSV = false;
				}
				if(k.pressStart()){
					//update savegame
					//reset
					this.restart();
				}
			}
			
		}
	
	
	}


	
	/**
	 * this method initializes everything
	 */
	public void init() {
		//window stuff
		setTitle("CMPE 219 HCI"); //title
		setSize(width, height); 
	    setFocusable(true);
	    requestFocusInWindow();
	    setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
	    setVisible(true); 
	    
	    //key listener for keyboard
	    addKeyListener(k);
	    
	    //buffering
	    backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    
	    //initialize screens
	    startScreen = new StartScreen(width,height);
	    creditScreen = new CreditScreen(width,height);
	    background = new Background(width,height);
	    gameB = new GameBackground(width,height);
	    pauseScreen = new Pause(width,height);
	    gameover = new GameOverScreen(width, height);
	    

	    //camera
	    Camera.setLocation(200, 0);
	    
	    //time is a clock for keeping track of delays etc
	    distance = 0;
	    delay = 0;
	    player = "josue"; //starts with default player 'josue'
	
	   motorcycle = new Motorcycle();
	   greenDudes = (GreenGuyReborn[]) greenDude.getDCArray(5);
	   cars = (Car[]) car.getDCArray(10);

	   hud = new HUD(width, height);
	   
	   //csv 'savegame'
	    row = SaveGame.getPlayerRow(player); //if doesn't exist make a new one
		currDistance = Integer.parseInt(SaveGame.getPlayerInfo(player)[1]);

	   
	   
	}
	
	public void restart(){
		start = true;
		lives = 3;
		immunity = 200;
		motorcycle.reset();
		Camera.reset();
		greenDudes = (GreenGuyReborn[]) greenDude.getDCArray(5);
		cars = (Car[]) car.getDCArray(15);
	    gameB = new GameBackground(width,height);
	    distance = 0;
	}

	/**
	 * this is sort of like the main() but in a loop
	 */
	@Override
	public void run() {
		
		while (true) {
			update();
			draw();
			
			try {
				Thread.sleep(16);
			} catch (Exception e) {
			}

		}

	}


}
