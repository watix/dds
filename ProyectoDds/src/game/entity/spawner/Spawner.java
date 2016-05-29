package game.entity.spawner;

import game.entity.Entity;
import game.level.Level;

/**
 * Clase que implementa la l�gica de los generadores
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class Spawner extends Entity {

	/**
	 * Constructor que crea un generador en una posici�n dada y que genera una cantidad de elementos que se
	 * implementa en sus hijos
	 * 
	 * @param x posici�n x del generador de particulas
	 * @param y posici�n y del generador de particulas
	 * @param amount cantidad de part�culas a generar
	 * @param level Nivel donde se van a a�adir el generador de particulas
	 */
	public Spawner(int x, int y, int amount, Level level) {
		this.x = x;
		this.y = y;
	}
}
