package PacmanRip;

public class Player extends Character {
	
	private int xTile;
	private int yTile;
	private boolean toggle;

	public Player(int id, int xPos, int yPos, int xVel, int yVel, int velMag) {
		super(id, xPos, yPos, xVel, yVel, velMag);
	}
	
	//getters
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
	public void setXTile(int X) {
		xTile = X;
	}
	public void setYTile(int Y) {
		yTile = Y;
	}
	public void setToggle(boolean T) {
		toggle = T;
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
			/*	else if (getXTile() == 0) {
				setXPos(88);
				}*/
				else {
					setXVel(-1);
					setYVel(0);								
				}
				break;
			case "RIGHT":
			/*	if (getXTile() == 5) {
					setXPos(88);
				}
				else*/ if (map[getYTile()][getXTile() + 1] == 1) {
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
