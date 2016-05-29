package game.entity.mob.state;

import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;

/**
 * Clase para la implementación del estado Normal del Player principal
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class EstadoNormal extends Estado {

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.mob.state.Estado#shootProjectile(double, double, game.entity.mob.Player)
	 */
	public void shootProjectile(double x, double y, Player player) {

		Projectile p = new WizzardProjectile(x, y, player);
		player.level.add(p);

	}

}
