package PacmanRip;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Environment {
	private static int screenWidth;
	private static int screenHeight;
	//0 = Welcome, 1 = Gameplay, 2 = Achievements, 3 = Store, 4 = Settings, 6 = Pause, 7 = exit
	private static int state;
	private static int optionHover;
	//button placeholder text
	private static Text[] text;
	//for welcome screen options placement
	private static Rectangle[] welOptions;
	//button placeholder text
	private static Text[] welText;
	//for game mode select screen options placement
	private static Rectangle[] modeOptions;
	//button placeholder text
	private static Text[] modeText;
	//pauseScreen rectangle
	private static Rectangle pScreenRect;
	//pause screen text
	private static Text pScreenText;
	//pauseScreen rectangle
	private static Rectangle eScreenRect;
	//pause screen text
	private static Text eScreenText;
	
	//temporary placement of player count
	public static int playerCount;
	public static int timer;
	public static int frameCount;
	
	//getters
	public static Rectangle getpScreenRect() {
		return pScreenRect;
	}
	public static Text getpScreenText() {
		return pScreenText;
	}
	public static Rectangle geteScreenRect() {
		return eScreenRect;
	}
	public static Text geteScreenText() {
		return eScreenText;
	}
	public static int getScreenWidth() {
		return screenWidth;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
	public static int getState() {
		return state;
	}
	public static int getOptionHover() {
		return optionHover;
	}
	public static Rectangle[] getWelOptions() {
		return welOptions;
	}
	public static Rectangle[] getModeOptions() {
		return modeOptions;
	}
	
	//setters
	public static void setScreenWidth(int pixels) {
		screenWidth = pixels;
	}
	public static void setScreenHeight(int pixels) {
		screenHeight = pixels;
	}
	public static void setState(int S) {
		state = S;
	}
	
	//Pause Screen / needs to be fine tuned
	public static void makePRect() {
		pScreenRect = new Rectangle((Environment.getScreenWidth()/2)-200, (Environment.getScreenHeight()/2)-50, 400, 100);
		pScreenRect.setFill(Color.rgb(0,191,255, 0.4));
		pScreenText = new Text((Environment.getScreenWidth()/2)-50, (Environment.getScreenHeight()/2)-25, "PAUSED");
	}
	
	//Exit prompt / needs to be fine tuned
	public static void makeERect() {
		eScreenRect = new Rectangle((Environment.getScreenWidth()/2)-200, (Environment.getScreenHeight()/2)-100, 400, 200);
		eScreenRect.setFill(Color.rgb(0,191,255, 0.4));
		eScreenText = new Text((Environment.getScreenWidth()/2), (Environment.getScreenHeight()/2)-50, "Do you want to Exit?");
		
	}
	
	
	//make menu options
	public static Rectangle[] makeOptions(String[] names, int width, int height, int space, Color fill, Color stroke, int border) {
		Rectangle[] options = new Rectangle[names.length];
		Text[] optionNames = new Text[names.length];
		int optionsTotalHeight = names.length * height + (names.length - 1) * space;
		
		for (int i = 0; i < names.length; ++i) {
			//rectangle constructor: (xPos, yPos, width, height)
			options[i] = new Rectangle ((screenWidth/2 - width/2), ((screenHeight/2 - optionsTotalHeight/2) + i * (height + space)), width, height);
			options[i].setFill(fill);
			options[i].setStroke(stroke);
			options[i].setStrokeWidth(border);
			optionNames[i] = new Text((screenWidth/2 - width/2 + 25), ((screenHeight/2 - optionsTotalHeight/2 + height/2) + i * (height + space)), names[i]);
		}
		//for temporary use
		text = optionNames;
		//return array of Rectangles
		return options;
	}
	
	//welcome screen setting up (0)
	public static Group makeWelcome() {
		Group welcome = new Group();
		String[] optionNames = new String[] {"Play!", "Achievements", "Store", "Settings"};
		welOptions = makeOptions(optionNames, 400, 100, 30, Color.AZURE, Color.BLACK, 5);
		welText = text;
		for (int i = 0; i < optionNames.length; ++i) {
			welcome.getChildren().add(welOptions[i]);
			//add button placeholder text
			welcome.getChildren().add(welText[i]);
		}
		return welcome;
	}
	
	//achievements screen setting up (2)
	
	
	public static Group makeAchievements() {
		Group achievements = new Group();
		Text achievementsText = new Text(20, 20, "Achievements Page (Under development): press any key to return...");
		achievements.getChildren().add(achievementsText);
		return achievements;
	}
	
	//store screen setting up (3)
	public static Group makeStore() {
		Group store = new Group();
		Text storeText = new Text(20, 20, "Store Page (Under development): press any key to return...");
		store.getChildren().add(storeText);
		return store;
	}
	
	//settings screen setting up (4)
	public static Group makeSettings() {
		Group settings = new Group();
		Text settingsText = new Text(20, 20, "Settings Page (Under development): press any key to return...");
		settings.getChildren().add(settingsText);
		return settings;
	}
	
	//game mode screen setting up (1a)
	public static Group makeMode() {
		Group mode = new Group();
		String[] optionNames = new String[] {"1 Player", "2 Players", "3 Players", "Return to welcome screen"};
		modeOptions = makeOptions(optionNames, 200, 50, 10, Color.AZURE, Color.BLACK, 5);
		modeText = text;
		for (int i = 0; i < optionNames.length; ++i) {
			mode.getChildren().add(modeOptions[i]);
			//add button placeholder text
			mode.getChildren().add(modeText[i]);
		}
		return mode;
	}
	
	public static void setDefault() {
		state = 0;
		optionHover = 1;
		welOptions[0].setFill(Color.PINK);
	}
	
	public static int switchScreen() {
		//reset highlight then switch to appropriate screen
		welOptions[optionHover - 1].setFill(Color.AZURE);
		state = optionHover;
		if (optionHover == 1) {
			modeOptions[0].setFill(Color.PINK);
		}
		return state;
	}
	
	public static int switchGame() {
		//reset highlight then switch to appropriate screen
		modeOptions[optionHover - 1].setFill(Color.AZURE);
		if (optionHover == 4) {
			setDefault();
		}
		else {
			playerCount = optionHover;
			state = 5;
		}
		return state;
	}
	
	public static void highlightUp(Rectangle[] options) {
		//reset highlight
		options[optionHover - 1].setFill(Color.AZURE);
		//find next highlight
		if (optionHover == 1) {
			optionHover = options.length;
		}
		else {
			--optionHover;
		}
		//set new highlight
		options[optionHover - 1].setFill(Color.PINK);
	}
	
	public static void highlightDown(Rectangle[] options) {
		//reset highlight
		options[optionHover - 1].setFill(Color.AZURE);
		//find next highlight
		if (optionHover == options.length) {
			optionHover = 1;
		}
		else {
			++optionHover;
		}
		//set new highlight
		options[optionHover - 1].setFill(Color.PINK);
	}
}
