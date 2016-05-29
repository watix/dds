package game.entity.mob;

import java.util.List;

import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

/**
 * Clase que implementa la lógica para el Mob que será el enemigo que se mueve persiguiendo al Player si se
 * acerca lo suficiente
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 */
public class Chaser extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite currentAnim = down;

	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;

	/**
	 * Constructor de Dummy que será el enemigo que persigua al jugador si entra denro de su radio de acción
	 * 
	 * @param x posición x del mapa donde va a posicionarse el Chaser
	 * @param y posición y del mapa donde va a posicionarse el Chaser
	 */
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = currentAnim.getSprites();
	}

	/**
	 * Método que implementa el movimiento específico del Chaser
	 */
	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 10);
		if (players.size() > 0) {
			Player player = players.get(0);
			if (x < player.getX()) xa += speed;
			if (x > player.getX()) xa -= speed;
			if (y < player.getY()) ya += speed;
			if (y > player.getY()) ya -= speed;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	/* (non-Javadoc)
	 * @see game.entity.mob.Mob#update()
	 */
	public void update() {
		move();
		if (walking) currentAnim.update();
		else currentAnim.setFrame(0);
		if (ya < 0) {
			currentAnim = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			currentAnim = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			currentAnim = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			currentAnim = right;
			dir = Direction.RIGHT;
		} else {
			currentAnim = down;
			dir = Direction.DOWN;
		}

	}

	/* (non-Javadoc)
	 * @see game.entity.mob.Mob#render(game.graphics.Screen)
	 */
	public void render(Screen screen) {
		sprite = currentAnim.getSprites();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}

}
