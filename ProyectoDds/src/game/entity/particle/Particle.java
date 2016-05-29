package game.entity.particle;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.tile.Tile;

/**
 * Clase que implementa la l�gica de las part�culas de las explosiones
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class Particle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;
	private int dir = 0;
	private List<Entity> enemyEntities = new ArrayList<Entity>();

	protected double xx, yy;
	protected double xa, ya;

	/**
	 * Constructor de Part�culas en un punto determinado
	 * 
	 * @param x posici�n x inicial de la generaci�n de la part�cula
	 * @param y posici�n y inicial de la generaci�n de la part�cula
	 * @param life duraci�n de la particula en pantalla
	 * @param d direcci�n de movimiento de la part�cula
	 */
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#update()
	 */
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		move((xx), (yy));
	}

	/**
	 * M�todo que implementa la l�gica de movimiento de las part�culas
	 * 
	 * @param x posici�n x del mapa donde va a posicionarse la part�cula
	 * @param y posici�n y del mapa donde va a posicionarse la part�cula
	 */
	private void move(double x, double y) {

		if (collition(x, y)) {
			this.remove();
		}
		if (dir == 0) this.xx += xa;
		if (dir == 1) this.yy += ya;
		if (dir == 2) this.yy += ya;
		if (dir == 3) this.yy += ya;
	}

	/**
	 * M�todo que calcula si en la posici�n a la que se va a mover la part�cula hay un Tile s�lido
	 * 
	 * @param x posici�n horizontal del Tile
	 * @param y posici�n vertical del Tile
	 * @return true si el Tile que que est� en la posici�n al a que se va a mover la part�cula es s�lido o no
	 */
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
					level.removeEntity(enemyEntities.get(e));
				}

			}
			if (level.getTile(ix, iy) == Tile.breakable) {
				level.removeTile(ix, iy);
				level.render = true;
			}

		}
		return solid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#render(game.graphics.Screen)
	 */
	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy, sprite);
	}
}
