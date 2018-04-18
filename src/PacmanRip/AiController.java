package PacmanRip;

public class AiController {
	public static void controlEnemy(Enemy enemy, Player player, int[][] map, int tileDim, int targetX, int targetY) {
		int[] intersectPos = new int[2];
		int[] target = new int[] {targetX, targetY};
		//act when tileSize away from turn tile coming from horizontal direction
		if (((map[enemy.getYPos()/tileDim][(enemy.getXPos() + enemy.getXVel() * tileDim)/tileDim] == 2) || (map[enemy.getYPos()/tileDim][(enemy.getXPos() + enemy.getXVel() * tileDim)/tileDim] == 6)) && (enemy.getXVel() != 0) && (enemy.getXPos() % tileDim == 0) && (enemy.getYPos() % tileDim == 0)) {
			intersectPos[0] = enemy.getXPos() + enemy.getXVel() * tileDim;
			intersectPos[1] = enemy.getYPos();
			//find available directions
			enemy.setAvailDir(map, tileDim);
			//set the next direction
			enemy.setNextDir(enemy.getAvailDir(), intersectPos, target, tileDim);
		}
		//act when (tileSize) away from turn tile coming from vertical direction
		else if (((map[(enemy.getYPos() + enemy.getYVel() * tileDim)/tileDim][enemy.getXPos()/tileDim] == 2) || (map[(enemy.getYPos() + enemy.getYVel() * tileDim)/tileDim][enemy.getXPos()/tileDim] == 6)) && (enemy.getYVel() != 0) && (enemy.getXPos() % tileDim == 0) && (enemy.getYPos() % tileDim == 0)) {
			intersectPos[0] = enemy.getXPos();
			intersectPos[1] = enemy.getYPos() + enemy.getYVel() * tileDim;
			//find available directions
			enemy.setAvailDir(map, tileDim);
			//set next direction
			enemy.setNextDir(enemy.getAvailDir(), intersectPos, target, tileDim);
		}
		//act when on a turn tile
		else if (((map[enemy.getYPos()/tileDim][enemy.getXPos()/tileDim] == 2) || (map[enemy.getYPos()/tileDim][enemy.getXPos()/tileDim] == 6)) && (enemy.getXPos() % tileDim == 0) && (enemy.getYPos() % tileDim == 0)) {
			enemy.changeDir(enemy.getNextDir());
		}
		enemy.move();
	}
	public static boolean collisionCheck(Enemy enemy, Player player) {
		if (player.getXPos() <= (enemy.getXPos()+enemy.getVelMag()) && player.getXPos() >= (enemy.getXPos()-enemy.getVelMag()) && player.getYPos() <= (enemy.getYPos()+enemy.getVelMag()) && player.getYPos() >= (enemy.getYPos()-enemy.getVelMag())) {
			return true;
		}
		return false;
	}
}
