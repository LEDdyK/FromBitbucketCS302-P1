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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
		{0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0},
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
		
		//Display name of window
		stage.setTitle("Candy Run! (Development Version)");
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		//Canvas size: Minimum = 1024x768, Maximum = 1440x900
		Canvas canvas = new Canvas(1024, 768);
		root.getChildren().add(canvas);

		//Scene graphics
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		Image circle = new Image("circle.png");
		Player pacman = new Player(1, mapScale, mapScale, 0, 0);
		pacman.setXTile(1);
		pacman.setYTile(1);
		pacman.Direction = "RIGHT";
		
		//Event handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			
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
	            	root.getChildren().addAll(wall);
	            }
	            else if (map[i][j] == 0) {
	            	foodXPos = j*wallWidth;
	            	foodYPos = i*wallHeight;
	            	Rectangle food = new Rectangle(foodXPos+mapScale/4, foodYPos+mapScale/4, mapScale/2, mapScale/2);//Creates food
	            	food.setFill(Color.BLUE);
	            	root.getChildren().addAll(food);
	            }
	        }
		}
		
		
		//window dynamics
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (pacman.getXVel() == -1) {
					pacman.setXTile((int)Math.ceil((double)pacman.getXPos()/mapScale));
					if (pacman.Direction == "RIGHT") {
						pacman.setXVel(1);
					}
				}
				if (pacman.getXVel() == 1) {
					pacman.setXTile(pacman.getXPos()/mapScale);
					if (pacman.Direction == "LEFT") {
						pacman.setXVel(-1);
					}
				}
				if (pacman.getYVel() == -1) {
					pacman.setYTile((int)Math.ceil((double)pacman.getYPos()/mapScale));
					if (pacman.Direction == "DOWN") {
						pacman.setYVel(1);
					}
				}
				if (pacman.getYVel() == 1) {
					pacman.setYTile(pacman.getYPos()/mapScale);
					if (pacman.Direction == "UP") {
						pacman.setYVel(-1);
					}
				}
				
				if ((map[pacman.getYTile()][pacman.getXTile()] == 2) && (pacman.getXPos()%mapScale == 0) && (pacman.getYPos()%mapScale == 0)) {
					switch(pacman.Direction) {
						case "UP":
							if (map[pacman.getYTile() - 1][pacman.getXTile()] == 1) {
								pacman.setXVel(0);
								pacman.setYVel(0);
							}
							else {
								pacman.setXVel(0);
								pacman.setYVel(-1);
							}
							break;
						case "DOWN":
							if (map[pacman.getYTile() + 1][pacman.getXTile()] == 1) {
								pacman.setXVel(0);
								pacman.setYVel(0);
							}
							else {
								pacman.setXVel(0);
								pacman.setYVel(1);
							}							
							break;
						case "LEFT":
							if (map[pacman.getYTile()][pacman.getXTile() - 1] == 1) {
								pacman.setXVel(0);
								pacman.setYVel(0);
							}
							else {
								pacman.setXVel(-1);
								pacman.setYVel(0);								
							}
							break;
						case "RIGHT":
							if (map[pacman.getYTile()][pacman.getXTile() + 1] == 1) {
								pacman.setXVel(0);
								pacman.setYVel(0);
							}
							else {
								pacman.setXVel(1);
								pacman.setYVel(0);
							}
							break;
					}
				}
				
				pacman.setXPos(pacman.getXPos() + (pacman.getXVel() * 1));
				pacman.setYPos(pacman.getYPos() + (pacman.getYVel() * 1));
				
				graphics.clearRect(0, 0, 1024, 768);
				graphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());
			}
		}.start();
		
		//show
		stage.show();
	}
}
