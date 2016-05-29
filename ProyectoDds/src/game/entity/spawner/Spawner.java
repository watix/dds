package game.entity.spawner;

import game.entity.Entity;
import game.level.Level;

/**
 * Clase que implementa la lógica de los generadores
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class Spawner extends Entity {

	/**
	 * Constructor que crea un generador en una posición dada y que genera una cantidad de elementos que se
	 * implementa en sus hijos
	 * 
	 * @param x posición x del generador de particulas
	 * @param y posición y del generador de particulas
	 * @param amount cantidad de partículas a generar
	 * @param level Nivel donde se van a añadir el generador de particulas
	 */
	public Spawner(int x, int y, int amount, Level level) {
		this.x = x;
		this.y = y;
	}
}
