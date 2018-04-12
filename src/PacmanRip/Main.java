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

	@Override
	public void start(Stage stage) throws Exception {
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		//welcome screen
		Group welcome = new Group();
		Scene welScene = new Scene(welcome, 1024, 768);
		
		//gameplay screen
		Group gameplay = new Group();
		Scene gameScene = new Scene(gameplay, 1024, 768);
		
		//Canvas size: Minimum = 1024x738, Maximum = 1440x900
		Canvas canvas = new Canvas(1024, 768);
		welcome.getChildren().add(canvas);
		gameplay.getChildren().add(canvas);
		
		int[][] map = new int[1024][768];
		//Scene graphics
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		Image circle = new Image("circle.png");
		Image circleE = new Image("circle.png");
		
		int targetX = 400;
		int targetY = 400;
		
		//game character constructors (ID, xPos, yPos, xVel, yVel, velMag)
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Enemy blinky = new Enemy(7, 0, 100, 1, 0, 10);
		//set enemy AI mode
		blinky.setMode(1);
		
		//Event handler
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
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				pacman.move();
				AiController.controlEnemy(blinky, pacman, map, 100, targetX, targetY);
				
				graphics.clearRect(0, 0, 1024, 768);
				graphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());
				graphics.drawImage(circleE, blinky.getXPos(), blinky.getYPos());
			}
		}.start();
		
		//show
		stage.show();
	}
}
