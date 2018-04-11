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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		//Canvas size: Minimum = 1024x738, Maximum = 1440x900
		Canvas canvas = new Canvas(1024, 768);
		root.getChildren().add(canvas);

		//Scene graphics
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		Image circle = new Image("circle.png");
		Image circleE = new Image("circle.png");
		
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Enemy blinky = new Enemy(7, 0, 0, 1, 0, 1);
		blinky.setMode(2);
		
		//Event handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
//				System.out.println(e.getCode().toString());
//				System.out.println(pacman.getXVel());
//				System.out.println(pacman.getYVel());
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
		
		//Get system time
		final long startNanoTime = System.nanoTime();
		//long newNanoTime = currentNanoTime;
		
		//window dynamics
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				
				if (pacman.getTick() < 1) {
					pacman.setTick(pacman.getTick() + 1);
				}
				else {
					pacman.setTick(0);
					//update actions
					pacman.setXPos(pacman.getXPos() + (pacman.getXVel() * pacman.getVelMag()));
					pacman.setYPos(pacman.getYPos() + (pacman.getYVel() * pacman.getVelMag()));
					AiController.controlMom(blinky, pacman);
					
					graphics.clearRect(0, 0, 1024, 768);
					graphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());
					graphics.drawImage(circleE, blinky.getXPos(), blinky.getYPos());
				}
			}
		}.start();
		
		//show
		stage.show();
	}
}
