package game.entity.mob.state;

import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;

public class EstadoNormal extends Estado {

	public void shootProjectile(double x, double y, Player player) {

		Projectile p = new WizzardProjectile(x, y, player);
		player.level.add(p);

	}

}
