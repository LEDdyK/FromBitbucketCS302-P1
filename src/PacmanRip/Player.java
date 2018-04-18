package PacmanRip;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player extends Character {
	
	private int xTile;
	private int yTile;
	private boolean toggle;
	private static int score = 0;
	private static int lives = 3;

	public Player(int id, int xPos, int yPos, int xVel, int yVel, int velMag) {
		super(id, xPos, yPos, xVel, yVel, velMag);
	}
	
	//getters
	public static int getScore() {
		return score;
	}
	public static int getLives() {
		return lives;
	}
	public int getXTile() {
		return xTile;
	}
	public int getYTile() {
		return yTile;
	}
	public boolean getToggle() {
		return toggle;
	}
	
	//setters
	public void setScore(int Score) {
		score = Score;
	}
	public void setLives(int Lives) {
		lives = Lives;
	}
	public void setXTile(int X) {
		xTile = X;
	}
		public void setYTile(int Y) {
		yTile = Y;
	}
	public void setToggle(boolean T) {
		toggle = T;
	}
	
	public static void incrementScore() {
		score = score+1;
	}
	public static void decrementLife() {
		lives = lives-1;
	}
	
	//call this function to update x and y tile positions and allow reverse
	//update x tile
	public void updateTilePos(int mapScale) {
		if (getXVel() == -1) {
			setXTile((int)Math.ceil((double)getXPos()/mapScale));
			//allow reverse
			if (Direction == "RIGHT") {
				setXVel(1);
			}
		}
		if (getXVel() == 1) {
			setXTile(getXPos()/mapScale);
			//allow reverse
			if (Direction == "LEFT") {
				setXVel(-1);
			}
		}
		//update y tile
		if (getYVel() == -1) {
			setYTile((int)Math.ceil((double)getYPos()/mapScale));
			//allow reverse
			if (Direction == "DOWN") {
				setYVel(1);
			}
		}
		if (getYVel() == 1) {
			setYTile(getYPos()/mapScale);
			//allow reverse
			if (Direction == "UP") {
				setYVel(-1);
			}
		}
	}
	
	//call this function to update velocity values
	public void updateDirection(int[][] map) {
		switch(Direction) {
			case "UP":
				if (map[getYTile() - 1][getXTile()] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(0);
					setYVel(-1);
				}
				break;
			case "DOWN":
				if (map[getYTile() + 1][getXTile()] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(0);
					setYVel(1);
				}							
				break;
			case "LEFT":
				if (map[getYTile()][getXTile() - 1] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(-1);
					setYVel(0);								
				}
				break;
			case "RIGHT":
				if (map[getYTile()][getXTile() + 1] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(1);
					setYVel(0);
				}
				break;
		}
	}
}
