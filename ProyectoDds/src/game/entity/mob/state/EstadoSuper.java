package game.entity.mob.state;

import game.Game;
import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;
import game.input.Mouse;

/**
 * Clase para la implementación del estado Super del Player principal
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class EstadoSuper extends Estado {

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.mob.state.Estado#shootProjectile(double, double, game.entity.mob.Player)
	 */
	public void shootProjectile(double x, double y, Player player) {
		double dx = Mouse.getX() - Game.getWindowWidth() / 2;
		double dy = Mouse.getY() - Game.getWindowHeight() / 2;
		double dir = Math.atan2(dy, dx);
		Projectile p = new WizzardProjectile(x, y, dir, player);
		player.level.add(p);

	}
}
