package game.entity.mob;

import game.entity.Entity;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;
import game.graphics.Screen;
import game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected enum Direction{
		UP,DOWN,LEFT,RIGHT
	}
	
	protected Direction dir;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0)
			dir = Direction.RIGHT;// right
		if (xa < 0)
			dir = Direction.LEFT;// left
		if (ya > 0)
			dir = Direction.DOWN;// down
		if (ya < 0)
			dir = Direction.UP;// up

		if (!collition(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizzardProjectile(x, y, dir);
		level.add(p);

	}

	private boolean collition(int xa, int ya) {
		boolean solid = false;
		if (level.getTile((x + xa) / 16, (y + ya + 16) / 16).solid())
			solid = true;
		return solid;
	}
}
