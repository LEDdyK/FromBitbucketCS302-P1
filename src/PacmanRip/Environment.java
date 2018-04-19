package PacmanRip;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
	//pause rectangle
	private static Rectangle pScreenRect;
	//pause text
	private static Text pScreenText;
	//escape rectangles
	private static Rectangle[] eScreenRect;
	//escape text
	private static Text[] eScreenText;
	//escape prompt toggle
	private static boolean escToggle;
	//Score
	private static Text scoreTxt;
	//Score
	private static Text livesTxt;
	//Death Text
	private static Text DeathText;
	//Times up Rectangle
	private static Rectangle tURect;
	//Times up Text
	private static Text tUText;
	
	public static Rectangle[][] wallArray;
	public static Rectangle[][] foodArray;
	
	//temporary placement of player count
	private static int playerCount;
	
	//getters
	public static Rectangle getpScreenRect() {
		return pScreenRect;
	}
	public static Text getpScreenText() {
		return pScreenText;
	}
	public static Rectangle getTURect() {
		return tURect;
	}
	public static Text getTUText() {
		return tUText;
	}	
	public static Rectangle geteScreenRect(int i) {
		return eScreenRect[i];
	}
	public static Text geteScreenText(int i) {
		return eScreenText[i];
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
	public static boolean getEscToggle() {
		return escToggle;
	}
	public static Text getScoreTxt() {
		return scoreTxt;
	}
	public static Text getLivesTxt() {
		return livesTxt;
	}
	public static Text getDeathTxt() {
		return DeathText;
	}
	public static int getPlayerCount() {
		return playerCount;
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
	public static void setEscToggle(boolean T) {
		escToggle = T;
		eScreenRect[1].setFill(Color.AZURE);
		eScreenRect[2].setFill(Color.GREEN);
	}
	public static void setPlayerCount(int count) {
		playerCount = count;
	}
	
	//Score Display
	public static void makeScoreText() {
		scoreTxt = new Text(Environment.getScreenWidth()/4, 50, "Score = " + Integer.toString(Player.getScore()));
	}
	//lives Display
	public static void makeLivesText() {
		livesTxt = new Text(Environment.getScreenWidth()-350, 50, "Remaining Lives = " + Integer.toString(Player.getLives()));
	}
	
	//Death Screen
	public static void makeDeathText() {
		DeathText = new Text("You got Caught!");
		DeathText.setFont(Font.font("Verdana", 60));
		DeathText.setFill(Color.RED);
		DeathText.setStroke(Color.GREEN);
		DeathText.setStrokeWidth(3);
		DeathText.setX(Environment.getScreenWidth()/2);
		DeathText.setY(Environment.getScreenHeight()/2);
		
	}
	
	//Pause Screen
	public static void makePRect() {
		pScreenRect = new Rectangle((Environment.getScreenWidth()/2)-200, (Environment.getScreenHeight()/2)-50, 400, 100);
		pScreenRect.setFill(Color.rgb(0,191,255, 0.4));
		pScreenText = new Text((Environment.getScreenWidth()/2)-50, (Environment.getScreenHeight()/2)-25, "PAUSED");
	}
	
	//Times up!
	public static void makeTURect() {
		tURect = new Rectangle((Environment.getScreenWidth()/2)-200, (Environment.getScreenHeight()/2)-50, 400, 100);
		tURect.setFill(Color.rgb(0,191,255, 0.4));
		tUText = new Text("Times up!");
		tUText.setFont(Font.font("Verdana", 100));
		tUText.setFill(Color.RED);
		tUText.setStroke(Color.GREEN);
		tUText.setStrokeWidth(3);
		tUText.setX(Environment.getScreenWidth()/2);
		tUText.setY(Environment.getScreenHeight()/2);
	}
	
	
	//Exit prompt
	public static void makeERect() {
		escToggle = false;
		eScreenRect = new Rectangle[3];
		eScreenText = new Text[3];
		eScreenRect[0] = new Rectangle(screenWidth/2 - 200, screenHeight/2 - 100, 400, 200);
		eScreenRect[0].setFill(Color.RED);
		eScreenText[0] = new Text(screenWidth/2 - 100, screenHeight/2 - 50, "Do you want to Exit?");
		eScreenRect[1] = new Rectangle(screenWidth/2 - 190, screenHeight/2 + 10, 185, 80);
		eScreenRect[1].setFill(Color.AZURE);
		eScreenText[1] = new Text(screenWidth/2 - 165, screenHeight/2 + 50, "Yes");
		eScreenRect[2] = new Rectangle(screenWidth/2 + 5, screenHeight/2 + 10, 185, 80);
		eScreenRect[2].setFill(Color.GREEN);
		eScreenText[2] = new Text(screenWidth/2 + 30, screenHeight/2 + 50, "No");
	}
	public static void escToggle() {
		escToggle = !escToggle;
		if (escToggle) {
			eScreenRect[1].setFill(Color.GREEN);
			eScreenRect[2].setFill(Color.AZURE);
		}
		else {
			eScreenRect[1].setFill(Color.AZURE);
			eScreenRect[2].setFill(Color.GREEN);
		}
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
	
	//make default hovers on option screens
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
	
	public static void initWallArray(int i, int j) {
		wallArray = new Rectangle[i][j];
	}
	public static void initFoodArray(int i, int j) {
		foodArray = new Rectangle[i][j];
	}
	
	public static void makeWalls(int[][] map, int mapScale, int wallWidth, int wallHeight, int leftOffset, int topOffset) {
		int wallXPos = 0;
		int wallYPos = 0;
		for(int i=0; i<map.length; i++) {
	        for(int j=0; j<map[i].length; j++) {
	            if (map[i][j] == 1) {
	            	wallXPos = j*wallWidth + leftOffset;
	            	wallYPos = i*wallHeight + topOffset;
	            	Rectangle wall = new Rectangle(wallXPos, wallYPos, mapScale, mapScale);//Creates walls
	            	wallArray[i][j] = wall;
	            }
	        }
		}
	}
	public static void makeFood(int[][] map, int mapScale, int wallWidth, int wallHeight, int leftOffset, int topOffset) {
		int foodXPos = 0;
		int foodYPos = 0;
		for(int i=0; i<map.length; i++) {
	        for(int j=0; j<map[i].length; j++) {
	        	if (map[i][j] == 0 || map[i][j] == 2) {
	            	foodXPos = j*wallWidth + leftOffset;
	            	foodYPos = i*wallHeight + topOffset;
	            	Rectangle food = new Rectangle(foodXPos+mapScale/4, foodYPos+mapScale/4, mapScale/2, mapScale/2);//Creates food
	            	food.setFill(Color.BLUE);
	            	foodArray[i][j] = food;
	        	}
	        }
		}
	}
	
}
