package game.entity.mob.state;

import game.entity.mob.Player;

abstract public class Estado {

	public void shoot(double x, double y, Player player) {

		int nump = player.level.getProjectiles().size();
		if (nump < player.bombas) {
			shootProjectile(x, y, player);
		}
		nump++;

	}

	abstract void shootProjectile(double x, double y, Player player);

}
