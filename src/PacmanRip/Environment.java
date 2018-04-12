package PacmanRip;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Environment {
	public static int screenWidth;
	public static int screenHeight;
	//0 = Welcome, 1 = Gameplay, 2 = Achievements, 3 = Store, 4 = Settings
	public static int state;
	public static int welOptionHover;
	//for welcome screen options placement
	public static int optionsTotalHeight;
	public static Rectangle[] options;
	//button placeholder text
	public static Text[] text;
	
	public static void makeMenuOptions(String[] names, int height, int width, int space, Color fill, Color stroke, int border) {
		Rectangle[] optionsA = new Rectangle[names.length];
		Text[] optionNames = new Text[names.length];
		optionsTotalHeight = names.length * height + (names.length - 1) * space;
		
		for (int i = 0; i < names.length; ++i) {
			//rectangle constructor: (xPos, yPos, width, height)
			optionsA[i] = new Rectangle ((screenWidth/2 - width/2), ((screenHeight/2 - optionsTotalHeight/2) + i * (height + space)), width, height);
			optionsA[i].setFill(fill);
			optionsA[i].setStroke(stroke);
			optionsA[i].setStrokeWidth(border);
			optionNames[i] = new Text((screenWidth/2 - width/2 + 25), ((screenHeight/2 - optionsTotalHeight/2 + height/2) + i * (height + space)), names[i]);
		}
		text = optionNames;
		options = optionsA;
	}
}
