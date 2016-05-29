package game.entity.mob.state;

import game.entity.mob.Player;

/**
 * Clase para la implementaci�n del estado del Player principal
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 */
abstract public class Estado {

	/**
	 * M�todo que implementa parte de la l�gica de disparo que se terminar� de implementar en sus clases hijas
	 * y si el numero de proyectiles ha alcanzado su m�ximo permitido
	 * 
	 * @param x posici�n horizontal donde se crea el proyectil
	 * @param y posici�n vertical donde se crea el proyectil
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
	 * Parte de la l�gica del disparo que genera los proyectiles que dispara el Player
	 * 
	 * @param x posici�n horizontal donde se crea el proyectil
	 * @param y posici�n vertical donde se crea el proyectil
	 * @param player Player el cual genera el disparo y tiene el estado
	 */
	abstract void shootProjectile(double x, double y, Player player);

}
