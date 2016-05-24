package game.entity.projectile;

import java.util.Random;

import game.entity.Entity;
import game.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double instance;
	protected double speed, range, damage;

	protected final Random random = new Random();

	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	protected void move() {

	}

}
