package PacmanRip;

public class Player extends Character {
	private boolean toggle;
	private static int score = 0;

	public Player(int id, int xPos, int yPos, int xVel, int yVel, int velMag) {
		super(id, xPos, yPos, xVel, yVel, velMag);
	}
	
	//getters
	public static int getScore() {
		return score;
	}
	public boolean getToggle() {
		return toggle;
	}
	
	//setters
	public void setScore(int Score) {
		score = Score;
	}
	public void setToggle(boolean T) {
		toggle = T;
	}
	
	public static void incrementScore() {
		score = score+1;
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
				if (map[yTile - 1][xTile] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(0);
					setYVel(-1);
				}
				break;
			case "DOWN":
				if (map[yTile + 1][xTile] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(0);
					setYVel(1);
				}							
				break;
			case "LEFT":
				if (map[yTile][xTile - 1] == 1) {
					setXVel(0);
					setYVel(0);
				}
				else {
					setXVel(-1);
					setYVel(0);								
				}
				break;
			case "RIGHT":
				if (map[yTile][xTile + 1] == 1) {
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
