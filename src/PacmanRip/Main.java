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
				  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				  { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				  { 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1},
				  { 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
				  { 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
				  { 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
				  { 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
				  { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1},
				  { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
				};
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
		Player pacman = new Player(1, 50, 50, 0, 1);
		
		//Event handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			
			@Override
			public void handle(KeyEvent e) {
				//System.out.println(e.getCode().toString());
				//System.out.println(pacman.getXVel());
				//System.out.println(pacman.getYVel());
				
				String direction = e.getCode().toString();
				
				switch(e.getCode().toString()) {
					case "UP":
				//	if (map[((pacman.getYPos())/40)-1][(pacman.getXPos())/40] == 1){
					//	pacman.setXVel(0);
						//pacman.setYVel(0);}
					{
						pacman.setXVel(0);
						pacman.setYVel(-1);}
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
		
		/*//Get system time
		final long startNanoTime = System.nanoTime();
		//long newNanoTime = currentNanoTime;*/
			
		//wall dimensions
		int wallWidth = 50;
		int wallHeight = 50;
		
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
	            	Rectangle wall = new Rectangle(wallXPos, wallYPos, 50, 50);//Creates walls
	            	root.getChildren().addAll(wall);
	            }
	            else if (map[i][j] == 0) {
	            	foodXPos = j*wallWidth;
	            	foodYPos = i*wallHeight;
	            	Rectangle food = new Rectangle(foodXPos+12.5, foodYPos+12.5, 25, 25);//Creates food
	            	food.setFill(Color.BLUE);
	            	root.getChildren().addAll(food);
	            }
	        }
	    
		
		
	}
		
		
		//window dynamics
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				
				if (pacman.getTick() < 10) {
					pacman.setTick(pacman.getTick() + 1);
				}
				else {
					pacman.setTick(0);
					//update actions
//					if (map[((pacman.getYPos())/40)-1][(pacman.getXPos())/40] == 1 && direction == "UP"){
//						pacman.setYVel(0);}
//					if (map[((pacman.getYPos())/40)+1][(pacman.getXPos())/40] == 1 && direction == "DOWN"){
//						pacman.setYVel(0);}
//					if (map[(pacman.getYPos())/40][((pacman.getXPos())/40)+1] == 1 && direction == "RIGHT"){
//						pacman.setXVel(0);}
//					if (map[(pacman.getYPos())/40][((pacman.getXPos())/40)-1] == 1 && direction == "LEFT"){
//						pacman.setXVel(0);}
					
					pacman.setXPos(pacman.getXPos() + (pacman.getXVel() * 4));
					pacman.setYPos(pacman.getYPos() + (pacman.getYVel() * 4));
					
					graphics.clearRect(0, 0, 1024, 768);
					graphics.drawImage(circle, pacman.getXPos(), pacman.getYPos());
					

					
				}
			}
		}.start();
		
		//show
		stage.show();
	}
}
