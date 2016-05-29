package game.entity.projectile;

import java.util.Random;

import game.entity.Entity;
import game.graphics.Sprite;

/**
 * Clase que implementa la l�gica de la creaci�n de poryectiles
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
	 * Constructor de proyectil que tiene movimiento en un posici�n y una direcci�n dada
	 * 
	 * @param x posici�n x inicial de generaci�n del proyectil
	 * @param y posici�n x inicial de generaci�n del proyectil
	 * @param dir direcci�n de movimiento del proyectil
	 */
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor de proyectil sin movimiento en un posici�n y una direcci�n dada
	 * 
	 * @param x posici�n x inicial de generaci�n del proyectil
	 * @param y posici�n x inicial de generaci�n del proyectil
	 */
	public Projectile(double x, double y) {
		xOrigin = x;
		yOrigin = y;
		angle = 0;
		this.x = x;
		this.y = y;
	}

	/**
	 * M�todo que implementa la l�gica de movimiento de un proyectil
	 */
	protected void move() {

	}

}
