package game.entity.mob;

import game.entity.Entity;
import game.graphics.Screen;

/**
 * Clase Abstracta para la creación, modificación y actualización de los elementos de tipo Mob que se mueven
 * en la pantalla.
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 */
public abstract class Mob extends Entity {

	protected boolean walking = false;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	/**
	 * Método que implementa el movimiento del Mob
	 * 
	 * @param xa posición x a la que se va a mover
	 * @param ya posición y a la que se va a mover
	 */
	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;

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

	/**
	 * Método que devuelve la unidad positva o negativa que va a moverse el Mob
	 * 
	 * @param value valor del cual depende el retorno
	 * @returne si el valor es negativo se devuelve -1 y si es positivo se devuelve +1
	 */
	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#update()
	 */
	public abstract void update();

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.Entity#render(game.graphics.Screen)
	 */
	public abstract void render(Screen screen);

	/**
	 * Método que calcula si en la posición a la que se va a mover el Mob hay un Tile sólido o uno no sólido
	 * 
	 * @param xa posición horizontal del Tile
	 * @param ya posición vertical del Tile
	 * @return true si el Tile que que está en la posición al a que se va a mover el Mob es sólido o no
	 */
	private boolean collition(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 15) / 16 + 0.5;
			double yt = ((y + ya) - c / 2 * 15) / 16 + 0.7;
			int ix = (int) Math.ceil(xt) - 1;
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);

			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
}
