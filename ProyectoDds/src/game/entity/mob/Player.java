package game.entity.mob;

import game.Game;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;

public class Player extends Mob {
	private Keyboard input;
	private Sprite sprite;
	private int animated = 0;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite currentAnim = down;

	private double fireRate = 0;

	public Player(Keyboard input) {

		this.input = input;
		sprite = Sprite.player_fordward;
		currentAnim = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_fordward;
		fireRate = WizzardProjectile.FIRE_RATE;

	}

	public void update() {

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

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) {
				level.getProjectiles().remove(i);
			}
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizzardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {

		// int dir = this.dir;
		int flip = 0;
		/*
		 * switch (dir) { no borrar, ejemplo de uso por si acaso case 0: sprite
		 * = Sprite.player_fordward; if (walking) { if (animated < 30) { sprite
		 * = Sprite.player_mov_f1; } else sprite = Sprite.player_mov_f2; }
		 * break; case 1: case 3: sprite = Sprite.player_right; if (dir == 3)
		 * flip = 1; if (walking) { if (animated < 30) { sprite =
		 * Sprite.player_mov_r1; } else sprite = Sprite.player_mov_r2; } break;
		 * case 2: sprite = Sprite.player_backward; if (walking) { if (animated
		 * < 30) { sprite = Sprite.player_mov_b1; } else sprite =
		 * Sprite.player_mov_b2; } break;
		 * 
		 * default: sprite = Sprite.player_fordward; break; }
		 */
		sprite = currentAnim.getSprites();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);

	}

}
