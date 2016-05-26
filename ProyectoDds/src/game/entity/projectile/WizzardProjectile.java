package game.entity.projectile;

import game.entity.mob.Player;
import game.entity.mob.state.EstadoSuper;
import game.entity.spawner.ParticleSpawner;
import game.graphics.Screen;
import game.graphics.Sprite;

public class WizzardProjectile extends Projectile {

	public static final double FIRE_RATE = 20;
	private int time = 0;
	private Player player;

	public WizzardProjectile(double x, double y, double dir, Player player) {
		super(x, y, dir);
		this.player= player;
		range = random.nextInt(100) + 50;
		speed = 2;
		//damage = 20;
		sprite = Sprite.projectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	public WizzardProjectile(double x, double y, Player player) {
		super(x, y);
		this.player= player;
		range = random.nextInt(100) + 50;
		sprite = Sprite.projectile;

	}

	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > range) {
			level.add(new ParticleSpawner((int) x, (int) y, 200, 10, level));
			remove();
		}
		if (player.estado instanceof EstadoSuper){ 
		if (level.tileCollition((int)(x + nx), (int)(y + ny), 7, 5 , 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 200, 5, level));
			remove();
		}
		move(); //mueve la bomba
		}
	}

	protected void move() {
		x += nx;
		y += ny;

		if (range < calculateDistance()) remove();
	}

	private double calculateDistance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {

		screen.renderTile((int) x - 11, (int) y - 2, sprite);
	}

}
