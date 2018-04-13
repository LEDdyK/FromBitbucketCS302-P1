package PacmanRip;

public class Player extends Character {
	
	private int xTile;
	private int yTile;

	public Player(int id, int xPos, int yPos, int xVel, int yVel) {
		super(id, xPos, yPos, xVel, yVel);
	}
	
	//getters
	public int getXTile() {
		return xTile;
	}
	public int getYTile() {
		return yTile;
	}
	
	//setters
	public void setXTile(int X) {
		xTile = X;
	}
	public void setYTile(int Y) {
		yTile = Y;
	}
}
