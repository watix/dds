package game.entity.mob;

import game.entity.Entity;
import game.entity.mob.state.Estado;
import game.entity.mob.state.EstadoNormal;
import game.graphics.Screen;

public abstract class Mob extends Entity {

	//protected boolean moving = false;
	protected boolean walking = false;
	public Estado estado = (Estado) new EstadoNormal();

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = Direction.RIGHT;// right
		if (xa < 0) dir = Direction.LEFT;// left
		if (ya > 0) dir = Direction.DOWN;// down
		if (ya < 0) dir = Direction.UP;// up

		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collition(abs(xa), ya)) {
					this.x += abs(xa);
				}
				xa -= abs(xa);
			} else {
				if (!collition(abs(xa), ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collition(xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!collition(xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}
	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen);

//	protected void shoot(double x, double y, double dir, int bombas) {
//		int nump = level.getProjectiles().size();
//		if (nump<bombas){
//		Projectile p = new WizzardProjectile(x, y, dir);
//		level.add(p);
//		}
//		nump++;
//	}

	
	
	
	private boolean collition(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 16) / 16+0.7;
			double yt = ((y + ya) - c / 2 * 16) / 16+0.7;
			int ix = (int) Math.ceil(xt)-1;
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);

			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
}
