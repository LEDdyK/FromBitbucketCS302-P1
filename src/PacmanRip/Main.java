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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Screen dimensions
		Environment.setScreenWidth(1024);
		Environment.setScreenHeight(768);
		
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		//make screens
		Scene[] screens = new Scene[6];
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
		
		//default start screen: welcome screen
		Environment.setDefault();
		stage.setScene(screens[0]);
		
		//game objects
		int[][] map = new int[1024][768];
		int targetX = 400;
		int targetY = 400;
		//game character constructors (ID, xPos, yPos, xVel, yVel, velMag)
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Image circle = new Image("circle.png");
		Enemy blinky = new Enemy(7, 0, 100, 1, 0, 0);
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
					case "W":
						pacman.setXVel(0);
						pacman.setYVel(-1);
						break;
					case "S":
						pacman.setXVel(0);
						pacman.setYVel(1);
						break;
					case "A": 
						pacman.setXVel(-1);
						pacman.setYVel(0);
						break;
					case "D":
						pacman.setXVel(1);
						pacman.setYVel(0);
						break;
					case "I":
						pacman.setXVel(0);
						pacman.setYVel(-1);
						break;
					case "K":
						pacman.setXVel(0);
						pacman.setYVel(1);
						break;
					case "J": 
						pacman.setXVel(-1);
						pacman.setYVel(0);
						break;
					case "L":
						pacman.setXVel(1);
						pacman.setYVel(0);
						break;
					case "ESCAPE":
						Environment.setDefault();
						stage.setScene(screens[0]);
						break;
				}
			}
			
		});
		
		Environment.timer = 0;
		Environment.frameCount = 0;
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (Environment.getState() == 5) {
					if (Environment.frameCount == 60) {
						Environment.frameCount = 0;
						++Environment.timer;
						System.out.println(Environment.timer);
					}
					++Environment.frameCount;
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
