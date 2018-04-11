package PacmanRip;

public class AiController {
	
	public static void controlMom(Enemy mom, Player player) {
		int xDif = player.getXPos() - mom.getXPos();
		int yDif = player.getYPos() - mom.getYPos();
		
		//chase: type 1
		if (Math.abs(xDif) >= Math.abs(yDif)) {
			mom.setXVel(xDif/Math.abs(xDif));
			mom.setYVel(0);
		}
		else {
			mom.setXVel(0);
			mom.setYVel(yDif/Math.abs(yDif));
		}
		mom.setXPos(mom.getXPos() + (mom.getXVel() * 1));
		mom.setYPos(mom.getYPos() + (mom.getYVel() * 1));
	}
	
	public static void controlTeacher(Enemy teacher, Player player) {
		int xDif = player.getXPos() - teacher.getXPos();
		int yDif = player.getYPos() - teacher.getYPos();
		
		//chase: type 1
		if (Math.abs(xDif) >= Math.abs(yDif)) {
			teacher.setXVel(xDif/Math.abs(xDif));
			teacher.setYVel(0);
		}
		else {
			teacher.setXVel(0);
			teacher.setYVel(yDif/Math.abs(yDif));
		}
		teacher.setXPos(teacher.getXPos() + (teacher.getXVel() * 1));
		teacher.setYPos(teacher.getYPos() + (teacher.getYVel() * 1));
	}

}
