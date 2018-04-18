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
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 9, 9, 9, 9, 9},
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 1, 1, 0, 1, 9, 9, 9, 9, 9},
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 9, 9, 9, 9, 9},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 9, 9, 9, 9, 9, 9, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{4, 0, 0, 0, 0, 0, 2, 0, 0, 2, 1, 9, 9, 9, 9, 9, 9, 1, 2, 0, 0, 2, 0, 0, 0, 0, 0, 4},
		{1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 9, 9, 9, 9, 9, 9, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 9, 9, 9, 9, 9},
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 9, 9, 9, 9, 9},
		{9, 9, 9, 9, 9, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 9, 9, 9, 9, 9},
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
	
	private void setMapValue(int YTile, int XTile, int value) {
		map[YTile][XTile] = value;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Screen dimensions
		Environment.setScreenWidth(1024);
		Environment.setScreenHeight(768);
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		//Game Section Offset
		int topOffset = Environment.getScreenHeight() - (map.length * mapScale);
		int leftOffset = (Environment.getScreenWidth() - (map[0].length * mapScale))/2;
		
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
		//pause and escape buttons
		Environment.makePRect();
		Environment.makeERect();
		Environment.makeScoreText();
		Environment.makeTURect();
		
		//default start screen: welcome screen
		Environment.setDefault();
		stage.setScene(screens[0]);
		
		//game objects
		//game character constructors (ID, xPos, yPos, xVel, yVel, velMag)
		Player pacman = new Player(1, mapScale, mapScale, 0, 0, 2);
		pacman.setXTile(1);
		pacman.setYTile(1);
		pacman.Direction = "RIGHT";
		Image circle = new Image("circle.png");
		Player multiOne = new Player(1, mapScale, mapScale*2, 0, 0, 2);
		multiOne.setXTile(1);
		multiOne.setYTile(1);
		multiOne.Direction = "DOWN";
		Image multiOneSprite = new Image("M1.png");
		Player multiTwo = new Player(1, mapScale, mapScale*3, 0, 0, 2);
		multiTwo.setXTile(1);
		multiTwo.setYTile(1);
		multiTwo.Direction = "DOWN";
		Image multiTwoSprite = new Image("M2.png");
		
		Enemy blinky = new Enemy(7, mapScale*2, mapScale, 1, 0, 2);
		Image circleE = new Image("circle.png");
		//set enemy AI mode
		blinky.setMode(1);
		Enemy pinky = new Enemy(7, mapScale*3, mapScale, 1, 0, 2);
		Image circleP = new Image("circle.png");
		//set enemy AI mode
		pinky.setMode(1);
		Enemy inky = new Enemy(7, mapScale*4, mapScale, 1, 0, 2);
		Image circleI = new Image("circle.png");
		//set enemy AI mode
		inky.setMode(1);
		Enemy clyde = new Enemy(7, mapScale*5, mapScale, 1, 0, 2);
		Image circleC = new Image("circle.png");
		//set enemy AI mode
		clyde.setMode(1);
		
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
						Environment.setPlayerCount(Environment.getOptionHover());
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
		
		Timer gameTime = new Timer();
		gameTime.resetCountdown();
		gameTime.setTimeLimit(120);
		gameTime.doGameTime();
		gameplay.getChildren().add(gameTime.getCountdownText());
		gameplay.getChildren().add(gameTime.getShowLimit());
		gameTime.setTrans(true);
		
		//actions upon key press on gameplay screen
		screens[5].setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "UP":
						if (Environment.getState() == 5) {
							pacman.Direction = "UP";
						}
						break;
					case "DOWN":
						if (Environment.getState() == 5) {
							pacman.Direction = "DOWN";
						}
						break;
					case "LEFT":
						if (Environment.getState() == 5) {
							pacman.Direction = "LEFT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "RIGHT":
						if (Environment.getState() == 5) {
							pacman.Direction = "RIGHT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "W":
						if (Environment.getState() == 5) {
							multiOne.Direction = "UP";
						}
						break;
					case "S":
						if (Environment.getState() == 5) {
							multiOne.Direction = "DOWN";
						}
						break;
					case "A":
						if (Environment.getState() == 5) {
							multiOne.Direction = "LEFT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "D":
						if (Environment.getState() == 5) {
							multiOne.Direction = "RIGHT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "I":
						if (Environment.getState() == 5) {
							multiTwo.Direction = "UP";
						}
						break;
					case "K":
						if (Environment.getState() == 5) {
							multiTwo.Direction = "DOWN";
						}
						break;
					case "J":
						if (Environment.getState() == 5) {
							multiTwo.Direction = "LEFT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "L":
						if (Environment.getState() == 5) {
							multiTwo.Direction = "RIGHT";
						}
						else if (Environment.getState() == 7) {
							Environment.escToggle();
						}
						break;
					case "P":
						if (Environment.getState() == 5) {
							Environment.setState(6);
							gameplay.getChildren().addAll(Environment.getpScreenRect(),Environment.getpScreenText());
						}
						else if (Environment.getState() == 6) {
							Environment.setState(5);		
							gameplay.getChildren().removeAll(Environment.getpScreenRect(),Environment.getpScreenText() );
						}
						break;
					case "ESCAPE":
						if (Environment.getState() == 5) {
							Environment.setState(7);
							gameplay.getChildren().addAll(Environment.geteScreenRect(0),Environment.geteScreenText(0));
							gameplay.getChildren().addAll(Environment.geteScreenRect(1),Environment.geteScreenText(1));
							gameplay.getChildren().addAll(Environment.geteScreenRect(2),Environment.geteScreenText(2));
						}
						else if (Environment.getState() == 7) {
							Environment.setEscToggle(false);
							Environment.setState(5);
							gameplay.getChildren().removeAll(Environment.geteScreenRect(0),Environment.geteScreenText(0));
							gameplay.getChildren().removeAll(Environment.geteScreenRect(1),Environment.geteScreenText(1));
							gameplay.getChildren().removeAll(Environment.geteScreenRect(2),Environment.geteScreenText(2));
						}
						break;
					case "ENTER":
						if (Environment.getState() == 7) {
							if (Environment.getEscToggle()) {
								stage.close();
							}
							else {
								Environment.setState(5);
								gameplay.getChildren().removeAll(Environment.geteScreenRect(0),Environment.geteScreenText(0));
								gameplay.getChildren().removeAll(Environment.geteScreenRect(1),Environment.geteScreenText(1));
								gameplay.getChildren().removeAll(Environment.geteScreenRect(2),Environment.geteScreenText(2));
							}
						}
						break;
					case "PAGE_DOWN":
						if (Environment.getState() == 5){
							System.out.println("hhhhh");
							gameTime.endCountdown();
							gameplay.getChildren().addAll(Environment.getTURect(),Environment.getTUText());
							/*Needs to be completed
							 * Needs Restart and main menu buttons*/
						}
				}
			}
		});
		Environment.initFoodArray(map.length, map[0].length);
		Environment.initWallArray(map.length, map[0].length);
		
		Environment.makeFood(map, mapScale, mapScale, mapScale, leftOffset, topOffset);
		Environment.makeWalls(map, mapScale, mapScale, mapScale, leftOffset, topOffset);
		
		//Loops to implement map
		for(int i=0; i<map.length; i++) {
	        for(int j=0; j<map[i].length; j++) {
	            if (map[i][j] == 1) {
	            	gameplay.getChildren().add(Environment.wallArray[i][j]);
	            }
	            else if (map[i][j] == 0 || map[i][j] == 2) {
	            	gameplay.getChildren().add(Environment.foodArray[i][j]);
	            }
	        }
		}
		
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (Environment.getState() == 5) {
					//frame counter
					gameTime.countFrames(60);
					//countdown: Ready? -> 3 -> 2 -> 1 -> GO!
					if (gameTime.getTrans() && (gameTime.getSecCount() < 5)) {
						//update countdown by removing countdown text, updating value, an re-adding text
						gameplay.getChildren().remove(gameTime.getCountdownText());
						gameTime.doCountdown();
						gameplay.getChildren().add(gameTime.getCountdownText());
					}
					//final line of countdown: "GO!"
					else if (gameTime.getTrans() && (gameTime.getSecCount() == 5)) {
						//remove the countdown text which should be displaying "GO!"
						gameplay.getChildren().remove(gameTime.getCountdownText());
					}
					//if countdown is finished (should have finished after 5 seconds, allow movement. Movement stops after 2mins.
					if ((gameTime.getSecCount() > 4) && (gameTime.getSecCount() < 125)) {
						//update timer
						if (gameTime.getTrans()) {
							//update the timer by removing timer text, updating value, and re-adding text
							gameplay.getChildren().remove(gameTime.getShowLimit());
							gameTime.doGameTime();
							gameplay.getChildren().add(gameTime.getShowLimit());
						}
											
						//player control logic
						pacman.updateTilePos(mapScale);
						//make turns at intersections
						if ((map[pacman.getYTile()][pacman.getXTile()] == 2 || map[pacman.getYTile()][pacman.getXTile()] == 6) && pacman.checkExact(mapScale)) {
							pacman.updateDirection(map);
						}
//						gameplay.getChildren().remove(Environment.getScoreTxt());
//						Environment.makeScoreText(); //updates getScoreTxt
//						System.out.print(map[pacman.getYTile()][pacman.getXTile()]);
						//Eating food, incrementing score
						if (map[pacman.getYTile()][pacman.getXTile()] == 0) {
							Player.incrementScore();
							setMapValue(pacman.getYTile(),pacman.getXTile(),5);
			            	gameplay.getChildren().remove(Environment.foodArray[pacman.getYTile()][pacman.getXTile()]);
//			            	gameplay.getChildren().add(Environment.getScoreTxt());
						}
						//Eating food at intersections, incrementing score
						if (map[pacman.getYTile()][pacman.getXTile()] == 2) {
							Player.incrementScore();
							setMapValue(pacman.getYTile(),pacman.getXTile(),6);
			            	gameplay.getChildren().remove(Environment.foodArray[pacman.getYTile()][pacman.getXTile()]);
//			            	gameplay.getChildren().add(Environment.getScoreTxt());
						}
						//warping player
						pacman.warp(map, mapScale);
						pacman.move();
						

						if (Environment.getPlayerCount() > 1) {
							//update player 2
							multiOne.doEverything(map, mapScale);
							//update player 3
							if (Environment.getPlayerCount() == 3) {
								multiTwo.doEverything(map, mapScale);
							}
						}
						
						//update AI
						AiController.controlEnemy(blinky, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
						blinky.warp(map, mapScale);
						AiController.controlEnemy(pinky, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
						pinky.warp(map, mapScale);
						if (Environment.getPlayerCount() < 3) {
							AiController.controlEnemy(inky, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
							inky.warp(map, mapScale);
							if (Environment.getPlayerCount() == 1) {
								AiController.controlEnemy(clyde, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
								clyde.warp(map, mapScale);
							}
						}
//						gameplay.getChildren().remove(Environment.getScoreTxt());

						//printing map to check proper labelling of points
//						for(int i=0; i<map.length; i++) {
//							System.out.println();
//					        for(int j=0; j<map[i].length; j++) {
//					        	System.out.print(map[i][j]);
//					        }
// 						}
					}
					
					//update visuals
					gameGraphics.clearRect(0, 0, 1024, 768);
					gameGraphics.drawImage(circle, pacman.getXPos() + leftOffset, pacman.getYPos() + topOffset);
					gameGraphics.drawImage(circleE, blinky.getXPos() + leftOffset, blinky.getYPos() + topOffset);
					gameGraphics.drawImage(circleP, pinky.getXPos() + leftOffset, pinky.getYPos() + topOffset);
					if (Environment.getPlayerCount() < 3) {
						gameGraphics.drawImage(circleI, inky.getXPos() + leftOffset, inky.getYPos() + topOffset);
						if (Environment.getPlayerCount() == 1) {
							gameGraphics.drawImage(circleC, clyde.getXPos() + leftOffset, clyde.getYPos() + topOffset);
						}
					}
					if (Environment.getPlayerCount() > 1) {
						gameGraphics.drawImage(multiOneSprite, multiOne.getXPos() + leftOffset, multiOne.getYPos() + topOffset);
						if (Environment.getPlayerCount() == 3) {
							gameGraphics.drawImage(multiTwoSprite, multiTwo.getXPos() + leftOffset, multiTwo.getYPos() + topOffset);
						}
					}
				}
			}
		}.start();
		
		//show
		stage.show();
	}
}
