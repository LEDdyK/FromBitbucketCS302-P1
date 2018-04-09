package PacmanRip;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
		Player pacman = new Player(1, 512, 384, 1, 0);
		
		//Get system time
		final long startNanoTime = System.nanoTime();
		
		//window dynamics
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - startNanoTime) / 100000000.0;
				
				//update actions
				pacman.setXPos(pacman.getXVel() * (int)t);
				pacman.setYPos(pacman.getYVel() * (int)t);
				graphics.clearRect(0, 0, 1024, 768);
				graphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());				
			}
		}.start();
		
		//show
		stage.show();
	}
}
