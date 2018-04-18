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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	static int mapScale = 22;
	
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
		Player pacman = new Player(1, mapScale+44, mapScale, 0, 0, 1);
		pacman.setXTile(1);
		pacman.setYTile(1);
		pacman.Direction = "RIGHT";
		Image circle = new Image("circle.png");
		Enemy blinky = new Enemy(7, mapScale, mapScale, 1, 0, 1);
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
	            	wallXPos = j*wallWidth + leftOffset;
	            	wallYPos = i*wallHeight + topOffset;
	            	Rectangle wall = new Rectangle(wallXPos, wallYPos, mapScale, mapScale);//Creates walls
	            	gameplay.getChildren().add(wall);
	            }
	            
	            else if (map[i][j] == 0 || map[i][j] == 2) {
	            	foodXPos = j*wallWidth + leftOffset;
	            	foodYPos = i*wallHeight + topOffset;
	            	Rectangle food = new Rectangle(foodXPos+mapScale/4, foodYPos+mapScale/4, mapScale/2, mapScale/2);//Creates food
	            	food.setFill(Color.BLUE);
	            	gameplay.getChildren().add(food);
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
						if ((map[pacman.getYTile()][pacman.getXTile()] == 2 || map[pacman.getYTile()][pacman.getXTile()] == 6) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
							pacman.updateDirection(map);
						}
//						gameplay.getChildren().remove(Environment.getScoreTxt());
//						Environment.makeScoreText(); //updates getScoreTxt
//						System.out.print(map[pacman.getYTile()][pacman.getXTile()]);
						//Eating food, incrementing score
						if (map[pacman.getYTile()][pacman.getXTile()] == 0) {
							Player.incrementScore();
							setMapValue(pacman.getYTile(),pacman.getXTile(),5);
							int dfoodXPos = pacman.getXTile()*wallWidth + leftOffset;
			            	int dfoodYPos = pacman.getYTile()*wallHeight + topOffset;
			            	Rectangle dfood = new Rectangle(dfoodXPos+mapScale/4, dfoodYPos+mapScale/4, mapScale/2, mapScale/2);
			            	dfood.setFill(Color.WHITE);
			            	gameplay.getChildren().add(dfood);
//			            	gameplay.getChildren().add(Environment.getScoreTxt());
						}
						//Eating food at intersections, incrementing score
						if (map[pacman.getYTile()][pacman.getXTile()] == 2) {
							Player.incrementScore();
							setMapValue(pacman.getYTile(),pacman.getXTile(),6);
							int dfoodXPos = pacman.getXTile()*wallWidth + leftOffset;
			            	int dfoodYPos = pacman.getYTile()*wallHeight + topOffset;
			            	Rectangle dfood = new Rectangle(dfoodXPos+mapScale/4, dfoodYPos+mapScale/4, mapScale/2, mapScale/2);
			            	dfood.setFill(Color.WHITE);
			            	gameplay.getChildren().add(dfood);
//			            	gameplay.getChildren().add(Environment.getScoreTxt());
						}
						//warping player
						else if ((pacman.getXTile() == 0) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
							pacman.setXPos((map[0].length - 1) * mapScale - 1);
						}						
						else if ((pacman.getXTile() == (map[0].length-1)) && (pacman.getXPos() % mapScale == 0) && (pacman.getYPos() % mapScale == 0)) {
							pacman.setXPos(1);
						}
						pacman.move();
						
						//update AI
						AiController.controlEnemy(blinky, pacman, map, mapScale, pacman.getXPos(), pacman.getYPos());
//						gameplay.getChildren().remove(Environment.getScoreTxt());

						//printing map to check proper labelling of points
//						for(int i=0; i<map.length; i++) {
//							System.out.println();
//					        for(int j=0; j<map[i].length; j++) {
//					        	System.out.print(map[i][j]);
//					        }
// 						}
						if ((pacman.getXPos() == blinky.getXPos()) && (pacman.getYPos() == blinky.getYPos())) {
							Environment.setState(6);
							if (Player.getLives() == 0) {
								//Game Over Screen
							}
							Timer.resetCountdown();
							Player.decrementLife();
							System.out.println("lost life");
							System.out.println(Player.getLives());
							pacman.resetVel();
							blinky.resetVel();
							pacman.resetPacPos();
							blinky.resetGPos();
							
							
							Environment.setState(5);
						}
					}
					
					//update visuals
					gameGraphics.clearRect(0, 0, 1024, 768);
					gameGraphics.drawImage(circle, pacman.getXPos() + leftOffset, pacman.getYPos() + topOffset);
					gameGraphics.drawImage(circleE, blinky.getXPos() + leftOffset, blinky.getYPos() + topOffset);
				}
			}
		}.start();
		
		//show
		stage.show();
	}
}
