package PacmanRip;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
	
	private Pane root;
	private Character player;
	private List<Character> enemies = new ArrayList<>();
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		private Parent createContent() {
//					
//			root = new Pane();
//			//window should be at least 1024x768 and no larger than 1440x900
//			root.setPrefSize(1024, 768);
//		}
		
		//Stage
//		primaryStage.setTitle("Candy Run! (Development Mode");
//		//Scene
//		primaryStage.setScene(new Scene(createContent()));
//		primaryStage.show();
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		/*if(event.getSource()==button) {
			System.out.println("Potato");
		}*/
		
	
	}
	
}
