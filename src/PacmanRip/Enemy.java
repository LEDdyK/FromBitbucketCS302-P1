package PacmanRip;

public class Enemy extends Character{

	public Enemy(int id, int xPos, int yPos, int xVel, int yVel) {
		super(id, xPos, yPos, xVel, yVel);
	}
	
	//modes: 1 = scatter, 2 = chase, 3 = run
	private int mode;
	//directions: 1 = up, 2 = down, 3 = left, 4 = right
	private int nextDir;
	
	//getters
	public int getMode() {
		return mode;
	}
	public int getNextDir() {
		return nextDir;
	}
	
	//setters
	public void setMode(int mode) {
		this.mode = mode;
	}
	public void setNextDir(int nextDir) {
		this.nextDir = nextDir;
	}

}
