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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Screen dimensions
		Environment.screenWidth = 1024;
		Environment.screenHeight = 768;
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		//welcome screen setting up (0)
		Group welcome = new Group();
		Scene welScene = new Scene(welcome, Environment.screenWidth, Environment.screenHeight, Color.CADETBLUE);
		String[] optionNames = new String[] {"Play!", "Achievements", "Store", "Settings"};
		Environment.makeMenuOptions(optionNames, 100, 400, 30, Color.AZURE, Color.BLACK, 5);
		for (int i = 0; i < optionNames.length; ++i) {
			welcome.getChildren().add(Environment.options[i]);
			//add button placeholder text
			welcome.getChildren().add(Environment.text[i]);
		}
		
		//gameplay screen setting up (1)
		Group gameplay = new Group();
		Scene gameScene = new Scene(gameplay, Environment.screenWidth, Environment.screenHeight);
		Canvas gameCanvas = new Canvas(Environment.screenWidth, Environment.screenHeight);
		gameplay.getChildren().add(gameCanvas);
		GraphicsContext gameGraphics = gameCanvas.getGraphicsContext2D();
		
		//achievements screen setting up (2)
		Group achievements = new Group();
		Scene achScene = new Scene(achievements, Environment.screenWidth, Environment.screenHeight);
		Text achText = new Text(20, 20, "Achievements Page (Under development): press any key to return...");
		achievements.getChildren().add(achText);
		
		//store screen setting up (3)
		Group store = new Group();
		Scene storeScene = new Scene(store, Environment.screenWidth, Environment.screenHeight);
		Text storeText = new Text(20, 20, "Store Page (Under development): press any key to return...");
		store.getChildren().add(storeText);
		
		//settings screen setting up (4)
		Group settings = new Group();
		Scene settingsScene = new Scene(settings, Environment.screenWidth, Environment.screenHeight);
		Text settingsText = new Text(20, 20, "Settings Page (Under development): press any key to return...");
		settings.getChildren().add(settingsText);
		
		//default start screen: welcome screen
		stage.setScene(welScene);
		Environment.state = 0;
		Environment.welOptionHover = 1;
		Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
		
		//game objects
		int[][] map = new int[1024][768];
		int targetX = 400;
		int targetY = 400;
		//game character constructors (ID, xPos, yPos, xVel, yVel, velMag)
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Image circle = new Image("circle.png");
		Enemy blinky = new Enemy(7, 0, 100, 1, 0, 10);
		Image circleE = new Image("circle.png");
		//set enemy AI mode
		blinky.setMode(1);
		
		//actions upon key press on welcome screen
		welScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "ENTER":
						Environment.options[Environment.welOptionHover - 1].setFill(Color.AZURE);
						switch(Environment.welOptionHover) {
							case 1:
								System.out.println("Going to game screen...");
								stage.setScene(gameScene);
								Environment.state = 1;
								break;
							case 2:
								System.out.println("Going to achievements screen...");
								stage.setScene(achScene);
								Environment.state = 2;
								break;
							case 3:
								System.out.println("Going to store screen...");
								stage.setScene(storeScene);
								Environment.state = 3;
								break;
							case 4:
								System.out.println("Going to settings screen...");
								stage.setScene(settingsScene);
								Environment.state = 4;
								break;
						}
						break;
					case "UP":
						Environment.options[Environment.welOptionHover - 1].setFill(Color.AZURE);
						if (Environment.welOptionHover == 1) {
							Environment.welOptionHover = 4;
						}
						else {
							--Environment.welOptionHover;
						}
						Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
						break;
					case "DOWN":
						Environment.options[Environment.welOptionHover - 1].setFill(Color.AZURE);
						if (Environment.welOptionHover == 4) {
							Environment.welOptionHover = 1;
						}
						else {
							++Environment.welOptionHover;
						}
						Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
						break;
				}
			}
		});
		
		//actions upon key press on gameplay screen
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode().toString()) {
					case "UP":
						pacman.setXVel(0);
						pacman.setYVel(-1);
						break;
					case "DOWN":
						pacman.setXVel(0);
						pacman.setYVel(1);
						break;
					case "LEFT": 
						pacman.setXVel(-1);
						pacman.setYVel(0);
						break;
					case "RIGHT":
						pacman.setXVel(1);
						pacman.setYVel(0);
						break;
				}
			}
			
		});
		
		//actions upon key press on achievements screen
		achScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				System.out.println("Returning to welcome screen...");
				stage.setScene(welScene);
				Environment.state = 0;
				Environment.welOptionHover = 1;
				Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
			}
		});
		
		//actions upon key press on store screen
		storeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				System.out.println("Returning to welcome screen...");
				stage.setScene(welScene);
				Environment.state = 0;
				Environment.welOptionHover = 1;
				Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
			}
		});
		
		//actions upon key press on settings screen
		settingsScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				System.out.println("Returning to welcome screen...");
				stage.setScene(welScene);
				Environment.state = 0;
				Environment.welOptionHover = 1;
				Environment.options[Environment.welOptionHover - 1].setFill(Color.PINK);
			}
		});
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (Environment.state == 1) {
					pacman.move();
					AiController.controlEnemy(blinky, pacman, map, 100, targetX, targetY);
					
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
