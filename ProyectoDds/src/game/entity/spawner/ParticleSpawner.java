package game.entity.spawner;

import game.entity.particle.Particle;
import game.level.Level;

/**
 * Clase que implementa la l�gica de la construcci�n de generadores de part�culas
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class ParticleSpawner extends Spawner {

	/**
	 * M�todo que crea un generador de part�culas en la posici�n y con las carecter�sticas indicadas
	 * 
	 * @param x posici�n x del generador de particulas
	 * @param y posici�n y del generador de particulas
	 * @param life duraci�n de la vida de la part�cula
	 * @param amount cantidad de part�culas a generar
	 * @param level Nivel donde se van a a�adir el generador de particulas
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
