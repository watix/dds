package game.entity.projectile;

import game.entity.particle.Particle;
import game.graphics.Screen;
import game.graphics.Sprite;

public class WizzardProjectile extends Projectile {

	public static final double FIRE_RATE = 15;

	public WizzardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(100) + 50;
		speed = 2;
		damage = 20;
		sprite = Sprite.projectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if (level.tileCollition(x, y, nx, ny, 7)) {
			Particle p = new Particle((int)x, (int)y, 500);
			level.add(p);
			remove();
		}
		move();

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
