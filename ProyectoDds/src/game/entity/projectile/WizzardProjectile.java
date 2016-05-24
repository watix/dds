package game.entity.projectile;

import game.entity.spawner.ParticleSpawner;
import game.graphics.Screen;
import game.graphics.Sprite;

public class WizzardProjectile extends Projectile {

	public static final double FIRE_RATE = 15;
	private int time = 0;

	public WizzardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = random.nextInt(100) + 50;
		speed = 2;
		damage = 20;
		sprite = Sprite.projectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > range) {
			level.add(new ParticleSpawner((int) x, (int) y, 100, 50, level));
			remove();
		}
		if (level.tileCollition((int)(x + nx), (int)(y + ny), 7, 5 , 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 100, 50, level));
			remove();
		}
		//move();

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
