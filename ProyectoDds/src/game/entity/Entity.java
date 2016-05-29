package game.entity;

import java.util.Random;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	private boolean removed = false;
	public Level level;
	protected final Random random = new Random();

	public Entity() {
	}

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void update() {

	}

	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite);

	}

	public void remove() {
		// remove from level
		removed = true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setXY(int i, int j) {
		this.x = i * 16;
		this.y = j * 16;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void init(Level level) {
		this.level = level;
	}

	public boolean isRemoved() {
		return removed;
	}
}
