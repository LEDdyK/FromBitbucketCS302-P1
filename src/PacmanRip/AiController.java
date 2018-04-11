package PacmanRip;

public class AiController {
	
	public static void controlMom(Enemy mom, Player player, int[][] map) {
		int[] intersectPos = new int[2];
		int[] target = new int[2];
		//act when 50px in horizontal direction
		if ((map[mom.getXPos() + mom.getXVel() * 50][mom.getYPos()] == 2) && (mom.getXVel() != 0)) {
			intersectPos[0] = mom.getXPos() + mom.getXVel() * 50;
			intersectPos[1] = mom.getYPos();
			mom.setAvailDir(map);
			//act upon mode:scatter - move to a designated target
			if (mom.getMode() == 1) {
				target[0] = 500;
				target[1] = 250;
			}
			//act upon mode:chase - move towards the player
			else if (mom.getMode() == 2) {
				target[0] = player.getXPos();
				target[1] = player.getYPos();
			}
			mom.setNextDir(mom.getAvailDir(), intersectPos, target, 100);
		}
		//act when 50px in vertical direction
		else if ((map[mom.getXPos()][mom.getYPos() + mom.getYVel() * 50] == 2) && (mom.getYVel() != 0)) {
			intersectPos[0] = mom.getXPos();
			intersectPos[1] = mom.getYPos() + mom.getYVel() * 50;
			mom.setAvailDir(map);
			//act upon mode:scatter - move to a designated target
			if (mom.getMode() == 1) {
				target[0] = 500;
				target[1] = 250;
			}
			//act upon mode:chase - move towards the player
			else if (mom.getMode() == 2) {
				target[0] = player.getXPos();
				target[1] = player.getYPos();
			}
			mom.setNextDir(mom.getAvailDir(), intersectPos, target, 100);
		}
		else if (map[mom.getXPos()][mom.getYPos()] == 2) {
			mom.changeDir(mom.getNextDir());
		}
		mom.setXPos(mom.getXPos() + (mom.getXVel() * mom.getVelMag()));
		mom.setYPos(mom.getYPos() + (mom.getYVel() * mom.getVelMag()));
	}
	
	public static void controlTeacher(Enemy teacher, Player player) {
		
	}
	
	public static void controlBully(Enemy bully, Player player) {
		
	}
	
	public static void controlCrush(Enemy crush, Player player) {
		
	}
}
