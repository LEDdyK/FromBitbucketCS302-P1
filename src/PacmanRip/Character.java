package PacmanRip;

public abstract class Character {
	//Character characteristics
	protected int id;
	protected int xPos, yPos;
	protected int xVel, yVel;
	
	//Constructor
	public Character(int id, int xPos, int yPos, int xVel, int yVel) {
		this.id = id;
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	//actions per tick
	//public abstract void tick();
	
	//getters
	public int getID() {
		return id;
	}
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public int getXVel() {
		return xVel;
	}
	public int getYVel() {
		return yVel;
	}
	
	//setters
	public void setID(int id) {
		this.id = id;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public void setXVel(int xVel) {
		this.xVel = xVel;
	}
	public void setYVel(int yVel) {
		this.yVel = yVel;
	}

}
