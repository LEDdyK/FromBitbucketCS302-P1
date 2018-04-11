package PacmanRip;

public class AiController {
	public static void controlEnemy(Enemy enemy, Player player, int[][] map, int tileDim, int targetX, int targetY) {
		int[] intersectPos = new int[2];
		int[] target = new int[] {targetX, targetY};
		//act when 50px away from turn tile coming from horizontal direction
		if ((map[enemy.getXPos() + enemy.getXVel() * tileDim/2][enemy.getYPos()] == 2) && (enemy.getXVel() != 0)) {
			intersectPos[0] = enemy.getXPos() + enemy.getXVel() * tileDim/2;
			intersectPos[1] = enemy.getYPos();
			//find available directions
			enemy.setAvailDir(map, tileDim);
			//set the next direction
			enemy.setNextDir(enemy.getAvailDir(), intersectPos, target, 100);
		}
		//act when 50px away from turn tile coming from vertical direction
		else if ((map[enemy.getXPos()][enemy.getYPos() + enemy.getYVel() * tileDim/2] == 2) && (enemy.getYVel() != 0)) {
			intersectPos[0] = enemy.getXPos();
			intersectPos[1] = enemy.getYPos() + enemy.getYVel() * tileDim/2;
			//find available directions
			enemy.setAvailDir(map, tileDim);
			//set next direction
			enemy.setNextDir(enemy.getAvailDir(), intersectPos, target, 100);
		}
		//act when on a turn tile
		else if (map[enemy.getXPos()][enemy.getYPos()] == 2) {
			enemy.changeDir(enemy.getNextDir());
		}
		enemy.move();
	}
}
