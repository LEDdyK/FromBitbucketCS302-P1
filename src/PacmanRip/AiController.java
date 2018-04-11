package PacmanRip;

public class AiController {
	
	public static void controlMom(Enemy mom, Player player) {
		//act upon mode:scatter
		//move to a designated target
		if (mom.getMode() == 1) {
			
		}
		//act upon mode:chase
		//move towards the player
		else if (mom.getMode() == 2) {
			
		}
		//act upon mode:run
		//run away from player
		else {
			
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
