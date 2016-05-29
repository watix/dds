package game.entity.projectile;

import java.util.Random;

import game.entity.Entity;
import game.graphics.Sprite;

/**
 * Clase que implementa la lógica de la creación de poryectiles
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double instance;
	protected double speed, range, damage;

	protected final Random random = new Random();

	/**
	 * Constructor de proyectil que tiene movimiento en un posición y una dirección dada
	 * 
	 * @param x posición x inicial de generación del proyectil
	 * @param y posición x inicial de generación del proyectil
	 * @param dir dirección de movimiento del proyectil
	 */
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor de proyectil sin movimiento en un posición y una dirección dada
	 * 
	 * @param x posición x inicial de generación del proyectil
	 * @param y posición x inicial de generación del proyectil
	 */
	public Projectile(double x, double y) {
		xOrigin = x;
		yOrigin = y;
		angle = 0;
		this.x = x;
		this.y = y;
	}

	/**
	 * Método que implementa la lógica de movimiento de un proyectil
	 */
	protected void move() {

	}

}
