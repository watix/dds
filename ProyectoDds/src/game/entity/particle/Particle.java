package game.entity.particle;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.tile.Tile;

public class Particle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;
	private int dir = 0;
	private List<Entity> enemyEntities = new ArrayList<Entity>();

	protected double xx, yy;// zz;
	protected double xa, ya;// za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;

		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		//this.zz = random.nextFloat() + 2.0;
	}

	public Particle(int x, int y, int life, int d) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.dir = d;

		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		// this.zz = random.nextFloat() + 2.0;
	}

	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		//this.za -= 0.1;
		/*
		 * if (zz < 0) { zz = 0; za *= -0.5; xa *= 0.4; ya *= 0.4; }
		 */
		move((xx), (yy));
	}

	private void move(double x, double y) {

		if (collition(x, y)) {
			this.remove();
		}
		if (dir == 0) this.xx += xa;
		if (dir == 1) this.yy += ya;
		if (dir == 2) this.yy += ya;
		if (dir == 3) this.yy += ya;
	}

	public boolean collition(double x, double y) {

		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);

			if (level.getTile(ix, iy).solid()) solid = true;

			enemyEntities = level.getEntities(this, 22);
			int esize = enemyEntities.size();

			if (esize > 0) {
				for (int e = 0; e < esize; e++) {
					System.out.println(enemyEntities.get(e));
					level.removeEnemy(enemyEntities.get(e));
				}
				// if(level.getEntitiesIndex(this, 10).length>=1){
				// level.enemyEntities.remove(level.getEntitiesIndex(this,
				// 0)[1]);
				// System.out.println(level.getEntitiesIndex(this, 0)[1]);
				// }

				// System.out.println(level.getEntities(this, 0).size());
			}
			if (level.getTile(ix, iy) == Tile.flower) {
				level.removeTile(ix, iy);
				level.render = true;
			}

		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy , sprite, true);
	}
}
