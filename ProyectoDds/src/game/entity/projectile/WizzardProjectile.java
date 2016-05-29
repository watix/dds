package game.entity.projectile;

import game.entity.mob.Player;
import game.entity.mob.state.EstadoSuper;
import game.entity.spawner.ParticleSpawner;
import game.graphics.Screen;
import game.graphics.Sprite;

/**
 * Clase que implementa la lógica de la creación de poryectiles
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class WizzardProjectile extends Projectile {

	public static final double FIRE_RATE = 20;
	private int time = 0;
	private Player player;

	/**
	 * * Constructor de un proyectil que se mueve y genera una explosion d particulas
	 * 
	 * @param x posición x inicial de generación del proyectil
	 * @param y posición x inicial de generación del proyectil
	 * @param dir dirección de movimiento del proyectil
	 * @param player Player que genera el proyectil
	 */
	public WizzardProjectile(double x, double y, double dir, Player player) {
		super(x, y, dir);
		this.player = player;
		range = random.nextInt(100) + 50;
		speed = 2;
		// damage = 20;
		sprite = Sprite.projectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	/**
	 * Constructor de un proyectil que genera una explosion d particulas
	 * 
	 * @param x posición x inicial de generación del proyectil
	 * @param y posición x inicial de generación del proyectil
	 * @param player Player que genera el proyectil
	 */
	public WizzardProjectile(double x, double y, Player player) {
		super(x, y);
		this.player = player;
		range = 100;
		sprite = Sprite.bomb;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#update()
	 */
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > range) {
			level.add(new ParticleSpawner((int) x, (int) y, 50, 10, level));
			remove();
		}
		if (player.estado instanceof EstadoSuper) {
			if (level.tileCollition((int) (x + nx), (int) (y + ny), 16, 5, 4)) {
				level.add(new ParticleSpawner((int) x, (int) y, 50, 5, level));
				remove();
			}
			move();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.projectile.Projectile#move()
	 */
	protected void move() {
		x += nx;
		y += ny;

		if (range < calculateDistance()) remove();
	}

	/**
	 * Método que calcula la distancia recorrida por un proyectil con respecto a su posición inicial.
	 * 
	 * @return la distancia recorrida por el proyectil
	 */
	private double calculateDistance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#render(game.graphics.Screen)
	 */
	public void render(Screen screen) {

		screen.renderTile((int) x - 11, (int) y, sprite);
	}

}
