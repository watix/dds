package game.entity.mob;

import game.entity.mob.state.Estado;
import game.entity.mob.state.EstadoNormal;
import game.entity.mob.state.EstadoSuper;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;

/**
 * Clase que implementa la lógica para el Mob que será el jugador principal
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class Player extends Mob {
	private Keyboard input;
	private Sprite sprite;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite currentAnim = down;
	public int bombas = 4;
	public Estado estado = (Estado) new EstadoNormal();

	private double fireRate = 0;

	/**
	 * Constructor que crea un player en una posición determinada y con un teclado para su movimiento
	 * 
	 * @param x posición x del mapa donde va a posicionarse el Player
	 * @param y posición y del mapa donde va a posicionarse el Player
	 * @param input teclado del que atender los eventos de pulsación de teclas
	 */
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_fordward;
		fireRate = WizzardProjectile.FIRE_RATE;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.mob.Mob#update()
	 */
	public void update() {
		if (level.enemyEntities.size() == 1) {
			estado = new EstadoSuper();
		} else estado = new EstadoNormal();

		if (walking) currentAnim.update();
		else currentAnim.setFrame(0);
		if (fireRate > 0) fireRate--;
		double xa = 0, ya = 0;
		double speed = 1;

		if (input.up) {
			currentAnim = up;
			ya -= speed;
		} else if (input.down) {
			currentAnim = down;
			ya += speed;
		}
		if (input.left) {
			currentAnim = left;
			xa -= speed;
		} else if (input.right) {
			currentAnim = right;
			xa += speed;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
	}

	/**
	 * Método para eliminar los proyectiles generados por el Player
	 */
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) {
				level.getProjectiles().remove(i);
			}
		}
	}

	/**
	 * Método que actualiza la cción de disparar del Player
	 */
	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {

			estado.shoot(x, y, this);
			fireRate = WizzardProjectile.FIRE_RATE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entity.mob.Mob#render(game.graphics.Screen)
	 */
	public void render(Screen screen) {

		int flip = 0;
		sprite = currentAnim.getSprites();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);

	}

}
