package PacmanRip;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	//map Array
	int[][] map = new int[][]{
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 2, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 2, 1},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{4, 0, 0, 0, 0, 0, 2, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 2, 0, 0, 0, 0, 0, 4},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{1, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
		{1, 2, 0, 2, 1, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 1, 1, 2, 0, 2, 1},
		{1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
		{1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
		{1, 2, 0, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 0, 2, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
		{1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	int mapScale = 22;
	
	@Override
	public void start(Stage stage) throws Exception {
		//Screen dimensions
		Environment.setScreenWidth(1024);
		Environment.setScreenHeight(768);
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		//make screens
		Scene[] screens = new Scene[7];
		//welcome screen setting up (0)	
		screens[0] = new Scene(Environment.makeWelcome(), Environment.getScreenWidth(), Environment.getScreenHeight(), Color.CADETBLUE);
		//game mode select screen setting up (1)
		screens[1] = new Scene(Environment.makeMode(), Environment.getScreenWidth(), Environment.getScreenHeight());		
		//achievements screen setting up (2)
		screens[2] = new Scene(Environment.makeAchievements(), Environment.getScreenWidth(), Environment.getScreenHeight());
		//store screen setting up (3)
		screens[3] = new Scene(Environment.makeStore(), Environment.getScreenWidth(), Environment.getScreenHeight());
		//settings screen setting up (4)
		screens[4] = new Scene(Environment.makeSettings(), Environment.getScreenWidth(), Environment.getScreenHeight());		
		//gameplay screen setting up (5)
		Group gameplay = new Group();
		Canvas gameCanvas = new Canvas(Environment.getScreenWidth(), Environment.getScreenHeight());
		gameplay.getChildren().add(gameCanvas);
		GraphicsContext gameGraphics = gameCanvas.getGraphicsContext2D();
		screens[5] = new Scene(gameplay, Environment.getScreenWidth(), Environment.getScreenHeight());
		Environment.makePRect();
		Environment.makeERect();
		
		
		//default start screen: welcome screen
		Environment.setDefault();
		stage.setScene(screens[0]);
		
		//game objects
		//game character constructors (ID, xPos, yPos, xVel, yVel, velMag)
		Player pacman = new Player(1, mapScale, mapScale, 0, 0, 1);
		pacman.setXTile(1);
		pacman.setYTile(1);
		pacman.Direction = "RIGHT";
		Image circle = new Image("circle.png");
		Enemy blinky = new Enemy(7, mapScale * 2, mapScale, 1, 0, 1);
		Image circleE = new Image("circle.png");
		//set enemy AI mode
		blinky.setMode(1);
		
		//actions upon key press on welcome screen
		screens[0].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "ENTER":
						stage.setScene(screens[Environment.switchScreen()]);
						break;
					case "UP":
						Environment.highlightUp(Environment.getWelOptions());
						break;
					case "DOWN":
						Environment.highlightDown(Environment.getWelOptions());
						break;
					case "ESCAPE":
						stage.close();
						break;
				}
			}
		});
		//actions upon key press on game mode select screen
		screens[1].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "ENTER":
						stage.setScene(screens[Environment.switchGame()]);
						break;
					case "UP":
						Environment.highlightUp(Environment.getModeOptions());
						break;
					case "DOWN":
						Environment.highlightDown(Environment.getModeOptions());
						break;
					case "ESCAPE":
						stage.close();
						break;
				}
			}
		});
		//actions upon key press on achievements screen
		screens[2].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				Environment.setDefault();
				stage.setScene(screens[0]);
			}
		});
		//actions upon key press on store screen
		screens[3].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				Environment.setDefault();
				stage.setScene(screens[0]);
			}
		});
		//actions upon key press on settings screen
		screens[4].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				Environment.setDefault();
				stage.setScene(screens[0]);
			}
		});
		
		
		//actions upon key press on gameplay screen
		screens[5].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "UP":
						pacman.Direction = "UP";
						break;
					case "DOWN":
						pacman.Direction = "DOWN";
						break;
					case "LEFT": 
						pacman.Direction = "LEFT";
						break;
					case "RIGHT":
						pacman.Direction = "RIGHT";
						break;
					case "ESCAPE":
						if (Environment.getState() == 5) {
							Environment.setState(7);
							gameplay.getChildren().addAll(Environment.geteScreenRect(),Environment.geteScreenText());
						/*	switch(e.getCode().toString()) {
							case "RIGHT":
								
								Break;
							case "LEFT":
								
								
								Break;
							case "ENTER':
								
								Break;
						}*/
						}
						else if(Environment.getState() == 7) {Environment.setState(5);							
						gameplay.getChildren().removeAll(Environment.geteScreenRect(),Environment.geteScreenText() );
						}
						break;
					case "P":
						if (Environment.getState() == 5) {
							Environment.setState(6);
							gameplay.getChildren().addAll(Environment.getpScreenRect(),Environment.getpScreenText());
						}
						else if (Environment.getState() == 6){Environment.setState(5);		
						gameplay.getChildren().removeAll(Environment.getpScreenRect(),Environment.getpScreenText() );
						}
						break;
				}
			}
		});
			
		//wall dimensions
		int wallWidth = mapScale;
		int wallHeight = mapScale;
		//wall position
		int wallXPos = 0;
		int wallYPos = 0;
		//food position
		int foodXPos = 0;
		int foodYPos = 0;
		//Loops to implement map
		for(int i=0; i<map.length; i++) {
	        for(int j=0; j<map[i].length; j++) {
	            if (map[i][j] == 1) {
	            	wallXPos = j*wallWidth;
	            	wallYPos = i*wallHeight;
	            	Rectangle wall = new Rectangle(wallXPos, wallYPos, mapScale, mapScale);//Creates walls
	            	gameplay.getChildren().add(wall);
	            }
	            else if ((map[i][j] == 0) || (map[i][j] == 2) || (map[i][j] == 4)) {
	            	foodXPos = j*wallWidth;
	            	foodYPos = i*wallHeight;
	            	Rectangle food = new Rectangle(foodXPos+mapScale/4, foodYPos+mapScale/4, mapScale/2, mapScale/2);//Creates food
	            	food.setFill(Color.BLUE);
	            	gameplay.getChildren().add(food);
	            }
	        }
		}
		
		Environment.timer = 0;
		Environment.frameCount = 0;
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {				
				if (Environment.getState() == 5) {
					//frame counter and displays time in seconds in console
					if (Environment.frameCount == 60) {
						Environment.frameCount = 0;
						++Environment.timer;
//						System.out.println(Environment.timer);
					}
					
					++Environment.frameCount;
					
					//player control logic
					pacman.updateTilePos(mapScale);
					//make turns at intersections
					if ((map[pacman.getYTile()][pacman.getXTile()] == 2) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
						pacman.updateDirection(map);
					}
					else if ((pacman.getXTile() == 0) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
						pacman.setXPos(571);
					}
					else if ((pacman.getXTile() == 26) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
						pacman.setXPos(1);
					}
					pacman.move();
					//update AI
					AiController.controlEnemy(blinky, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
					
					//update visuals
					gameGraphics.clearRect(0, 0, 1024, 768);
					gameGraphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());
					gameGraphics.drawImage(circleE, blinky.getXPos(), blinky.getYPos());
				}
			}
		}.start();
		
		//show
		stage.show();
	}
}
