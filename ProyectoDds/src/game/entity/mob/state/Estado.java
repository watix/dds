package game.entity.mob.state;

import game.entity.mob.Player;

/**
 * Clase para la implementación del estado del Player principal
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 */
abstract public class Estado {

	/**
	 * Método que implementa parte de la lógica de disparo que se terminará de implementar en sus clases hijas
	 * y si el numero de proyectiles ha alcanzado su máximo permitido
	 * 
	 * @param x posición horizontal donde se crea el proyectil
	 * @param y posición vertical donde se crea el proyectil
	 * @param player Player el cual genera el disparo y tiene el estado
	 */
	public void shoot(double x, double y, Player player) {

		int nump = player.level.getProjectiles().size();
		if (nump < player.bombas) {
			shootProjectile(x, y, player);
		}
		nump++;

	}

	/**
	 * Parte de la lógica del disparo que genera los proyectiles que dispara el Player
	 * 
	 * @param x posición horizontal donde se crea el proyectil
	 * @param y posición vertical donde se crea el proyectil
	 * @param player Player el cual genera el disparo y tiene el estado
	 */
	abstract void shootProjectile(double x, double y, Player player);

}
