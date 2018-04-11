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
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		//Canvas size: Minimum = 1024x738, Maximum = 1440x900
		Canvas canvas = new Canvas(1024, 768);
		root.getChildren().add(canvas);
		
		int[][] map = new int[1024][768];
		//Scene graphics
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		Image circle = new Image("circle.png");
		Image circleE = new Image("circle.png");
		Rectangle turnTile = new Rectangle(500, 100, 100, 100);
		turnTile.setFill(Color.GREEN);
		root.getChildren().add(turnTile);
		map[500][100] = 2;
		Rectangle turnTile2 = new Rectangle(500, 500, 100, 100);
		turnTile2.setFill(Color.GREEN);
		root.getChildren().add(turnTile2);
		map[500][500] = 2;
		
		//walls for visual debugging purposes
		Rectangle[] walls = new Rectangle[9];
		walls[1] = new Rectangle(400, 0, 100, 100);//top left
		walls[1].setFill(Color.RED);
		map[400][0] = 1;
		walls[3] = new Rectangle(600, 0, 100, 100);//top right
		walls[3].setFill(Color.RED);
		map[600][0] = 1;
		walls[5] = new Rectangle(600, 200, 100, 100);//bottom right
		walls[5].setFill(Color.RED);
		map[600][200] = 1;
		walls[7] = new Rectangle(400, 200, 100, 100);//bottom left
		walls[7].setFill(Color.RED);
		map[400][200] = 1;
		root.getChildren().addAll(walls[1], walls[3], walls[5], walls[7]);
		//toggle walls
		walls[0] = new Rectangle(500, 0, 100, 100);//top
		walls[0].setFill(Color.RED);
		map[500][0] = 1;
		root.getChildren().add(walls[0]);
//		walls[2] = new Rectangle(600, 100, 100, 100);//right
//		walls[2].setFill(Color.RED);
//		map[600][100] = 1;
//		root.getChildren().add(walls[4]);
//		walls[4] = new Rectangle(500, 200, 100, 100);//bottom
//		walls[4].setFill(Color.RED);
//		map[500][200] = 1;
//		root.getChildren().add(walls[6]);
//		walls[6] = new Rectangle(400, 100, 100, 100);//left
//		walls[6].setFill(Color.RED);
//		map[400][100] = 1;
//		root.getChildren().add(walls[8]);
		walls[8] = new Rectangle(500, 600, 100, 100);//top
		walls[8].setFill(Color.RED);
		map[500][600] = 1;
		root.getChildren().add(walls[8]);
		
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Enemy blinky = new Enemy(7, 0, 100, 1, 0, 5);
		blinky.setMode(1);
		
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
		//final long startNanoTime = System.nanoTime();
		//long newNanoTime = currentNanoTime;
		
		//window dynamics: fps = 60
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				//fps limiter
				if (pacman.getTick() < 1) {
					pacman.setTick(pacman.getTick() + 1);
				}
				else {
					pacman.setTick(0);
					//update actions
					pacman.setXPos(pacman.getXPos() + (pacman.getXVel() * pacman.getVelMag()));
					pacman.setYPos(pacman.getYPos() + (pacman.getYVel() * pacman.getVelMag()));
					AiController.controlMom(blinky, pacman, map);
					
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
