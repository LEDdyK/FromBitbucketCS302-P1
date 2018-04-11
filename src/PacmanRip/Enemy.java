package PacmanRip;

public class Enemy extends Character{

	public Enemy(int id, int xPos, int yPos, int xVel, int yVel, int velMag) {
		super(id, xPos, yPos, xVel, yVel, velMag);
	}
	
	//modes: 1 = scatter, 2 = chase, 3 = run
	private int mode;
	//directions: 1 = up, 2 = down, 3 = left, 4 = right
	private int nextDir;
	//[up][down][left][right]
	private boolean[] availDir = new boolean[4];
	
	//getters
	public int getMode() {
		return mode;
	}
	public int getNextDir() {
		return nextDir;
	}
	public boolean[] getAvailDir() {
		return availDir;
	}
	
	//setters
	public void setMode(int mode) {
		this.mode = mode;
	}
	//call this method before green tile
	public void setNextDir(boolean[] availDir, int[] intersectPos, int[] target, int tileDim) {
		int[] nextTileDist = new int[4];
		
		//find distances to target from next tile after intersection
		if (availDir[0]) {
			nextTileDist[0] = ((intersectPos[0] - tileDim) - target[0])^2 + (intersectPos[1] - target[1])^2;
		} else nextTileDist[0] = 2*(1024^2);
		if (availDir[1]) {
			nextTileDist[1] = ((intersectPos[0] + tileDim) - target[0])^2 + (intersectPos[1] - target[1])^2;			
		} else nextTileDist[1] = 2*(1024^2);
		if (availDir[2]) {
			nextTileDist[2] = (intersectPos[0] - target[0])^2 + ((intersectPos[1] - tileDim) - target[1])^2;			
		} else nextTileDist[2] = 2*(1024^2);
		if (availDir[3]) {
			nextTileDist[3] = (intersectPos[0] - target[0])^2 + ((intersectPos[1] + tileDim) - target[1])^2;
		} else nextTileDist[3] = 2*(1024^2);
		
		//find the shortest distance
		int shortest = 0;
		for (int i = 1; i < nextTileDist.length; ++i) {
			//in case of same distance
			if (nextTileDist[i] == nextTileDist[shortest]) {
				if (Math.random() < 0.5) {
					shortest = i;
				}
			}
			//reassign shortest
			else if (nextTileDist[i] < nextTileDist[shortest]) {
				shortest = i;
			}
		}
		
		//set next direction
		this.nextDir = shortest + 1;
	}
	public void setAvailDir(boolean up, boolean down, boolean left, boolean right) {
		this.availDir[0] = up;
		this.availDir[1] = down;
		this.availDir[2] = left;
		this.availDir[3] = right;
	}
	
	//call this method at green tile
	public void changeDir(int nextDir) {
		switch(nextDir) {
			case 1: 
				this.xVel = 0;
				this.yVel = -1;
				break;
			case 2:
				this.xVel = 0;
				this.yVel = 1;
				break;
			case 3:
				this.xVel = -1;
				this.yVel = 0;
				break;
			case 4:
				this.xVel = 1;
				this.yVel = 0;
				break;
		}
	}

}
