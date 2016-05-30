package game.entity.spawner;

import game.entity.particle.Particle;
import game.level.Level;

/**
 * Clase que implementa la lógica de la construcción de generadores de partículas
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class ParticleSpawner extends Spawner {

	/**
	 * Método que crea un generador de partículas en la posición y con las carecterísticas indicadas
	 * 
	 * @param x posición x del generador de particulas
	 * @param y posición y del generador de particulas
	 * @param life duración de la vida de la partícula
	 * @param amount cantidad de partículas a generar
	 * @param level Nivel donde se van a añadir el generador de particulas
	 */
	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, amount, level);
		int dir= 0;
		for (int i = 0; i < amount; i++) {
			if (dir > 3)dir =0;			
			level.add(new Particle(x, y + 7, life, dir));
			dir++;
		}

	}

}
