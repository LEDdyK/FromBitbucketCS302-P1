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
		Rectangle turnTile3 = new Rectangle(100, 500, 100, 100);
		turnTile3.setFill(Color.GREEN);
		root.getChildren().add(turnTile3);
		map[100][500] = 2;
		Rectangle turnTile4 = new Rectangle(100, 100, 100, 100);
		turnTile4.setFill(Color.GREEN);
		root.getChildren().add(turnTile4);
		map[100][100] = 2;
		
		
		//walls for visual debugging purposes
		Rectangle[] walls = new Rectangle[9];
		walls[1] = new Rectangle(400, 0, 100, 100);//top left
		walls[1].setFill(Color.BLACK);
		map[400][0] = 1;
		walls[3] = new Rectangle(600, 0, 100, 100);//top right
		walls[3].setFill(Color.BLACK);
		map[600][0] = 1;
		walls[5] = new Rectangle(600, 200, 100, 100);//bottom right
		walls[5].setFill(Color.BLACK);
		map[600][200] = 1;
		walls[7] = new Rectangle(400, 200, 100, 100);//bottom left
		walls[7].setFill(Color.BLACK);
		map[400][200] = 1;
		root.getChildren().addAll(walls[1], walls[3], walls[5], walls[7]);
		//toggle walls
		walls[0] = new Rectangle(500, 0, 100, 100);//top
		walls[0].setFill(Color.BLACK);
		map[500][0] = 1;
		root.getChildren().add(walls[0]);
//		walls[2] = new Rectangle(600, 100, 100, 100);//right
//		walls[2].setFill(Color.BLACK);
//		map[600][100] = 1;
//		root.getChildren().add(walls[4]);
//		walls[4] = new Rectangle(500, 200, 100, 100);//bottom
//		walls[4].setFill(Color.BLACK);
//		map[500][200] = 1;
//		root.getChildren().add(walls[6]);
//		walls[6] = new Rectangle(400, 100, 100, 100);//left
//		walls[6].setFill(Color.BLACK);
//		map[400][100] = 1;
//		root.getChildren().add(walls[8]);
		walls[8] = new Rectangle(500, 600, 100, 100);//top
		walls[8].setFill(Color.BLACK);
		map[500][600] = 1;
		root.getChildren().add(walls[8]);
		
		//target tile
		int targetX = 400;
		int targetY = 400;
		Rectangle targetTile = new Rectangle(targetX, targetY, 100, 100);
		targetTile.setFill(Color.YELLOW);
		root.getChildren().add(targetTile);
		
		Player pacman = new Player(1, 512, 384, 1, 0, 4);
		Enemy blinky = new Enemy(7, 0, 100, 1, 0, 10);
		blinky.setMode(1);
		
		//Event handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
