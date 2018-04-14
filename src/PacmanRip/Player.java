package PacmanRip;

public class Player extends Character {
	
	private int xTile;
	private int yTile;
	private boolean toggle;

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
}
