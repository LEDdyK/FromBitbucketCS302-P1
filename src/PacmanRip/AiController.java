package PacmanRip;

public class AiController {
	
	public static void controlMom(Enemy mom, Player player, int[][] map) {
		//act when within 50px of intersection
		boolean upAvail, downAvail, leftAvail, rightAvail;
		int[] intersectPos = new int[2];
		int[] target = new int[2];
		
		if ((map[mom.getXPos() + mom.getXVel() * 50][mom.getYPos()] == 2) && (mom.getXVel() != 0)) {
			intersectPos[0] = mom.getXPos() + mom.getXVel() * 50;
			intersectPos[1] = mom.getYPos();
			//find available directions at intersection
			//up
			if (map[mom.getXPos() + mom.getXVel() * 50][mom.getYPos() - 100] == 0) {
				upAvail = true;
			} else upAvail = false;
			//down
			if (map[mom.getXPos() + mom.getXVel() * 50][mom.getYPos() + 100] == 0) {
				downAvail = true;
			} else downAvail = false;
			//left
			if ((map[mom.getXPos() + mom.getXVel() * 50 - 100][mom.getYPos()] == 0) && (mom.getXVel() != 1)) {
				leftAvail = true;
			} else leftAvail = false;
			//right
			if ((map[mom.getXPos() + mom.getXVel() * 50 + 100][mom.getYPos()] == 0) && (mom.getXVel() != -1)) {
				rightAvail = true;
			} else rightAvail = false;
			//set available directions
			mom.setAvailDir(upAvail, downAvail, leftAvail, rightAvail);
			boolean[] checkDir = new boolean[] {upAvail, downAvail, leftAvail, rightAvail};
			System.out.println("current position: " + mom.getXPos() + " " + mom.getYPos());
			System.out.println("up:" + checkDir[0] + " down:" + checkDir[1] + " left:" + checkDir[2] + " right:" + checkDir[3]);
			
			//act upon mode:scatter
			//move to a designated target
			if (mom.getMode() == 1) {
				target[0] = 500;
				target[1] = 700;
				mom.setNextDir(mom.getAvailDir(), intersectPos, target, 100);
			}
			//act upon mode:chase
			//move towards the player
			else if (mom.getMode() == 2) {
				
			}
			//act upon mode:run
			//run away from player
			else {
				
			}
			mom.setXPos(mom.getXPos() + (mom.getXVel() * mom.getVelMag()));
			mom.setYPos(mom.getYPos() + (mom.getYVel() * mom.getVelMag()));
		}
		else if ((map[mom.getXPos()][mom.getYPos() + mom.getYVel() * 50] == 2) && (mom.getYVel() != 0)) {
			
		}
		else if (map[mom.getXPos()][mom.getYPos()] == 2) {
			mom.changeDir(mom.getNextDir());
			mom.setXPos(mom.getXPos() + (mom.getXVel() * mom.getVelMag()));
			mom.setYPos(mom.getYPos() + (mom.getYVel() * mom.getVelMag()));
		}
		else {
			mom.setXPos(mom.getXPos() + (mom.getXVel() * mom.getVelMag()));
			mom.setYPos(mom.getYPos() + (mom.getYVel() * mom.getVelMag()));
		}
//		int xDif = player.getXPos() - mom.getXPos();
//		int yDif = player.getYPos() - mom.getYPos();
//		
//		//chase: type 1 - for 
//		if (Math.abs(xDif) >= Math.abs(yDif)) {
//			mom.setXVel(xDif/Math.abs(xDif));
//			mom.setYVel(0);
//		}
//		else {
//			mom.setXVel(0);
//			mom.setYVel(yDif/Math.abs(yDif));
//		}
//		mom.setXPos(mom.getXPos() + (mom.getXVel() * mom.getVelMag()));
//		mom.setYPos(mom.getYPos() + (mom.getYVel() * mom.getVelMag()));
	}
	
	public static void controlTeacher(Enemy teacher, Player player) {
		
	}
	
	public static void controlBully(Enemy bully, Player player) {
		
	}
	
	public static void controlCrush(Enemy crush, Player player) {
		
	}
}
