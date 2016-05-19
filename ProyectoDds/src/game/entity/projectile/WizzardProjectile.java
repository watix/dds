package game.entity.projectile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class WizzardProjectile extends Projectile {

	public WizzardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 300;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.projectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		move();

	}

	protected void move() {
		x += nx;
		y += ny;
		calculateDistance();
		
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
