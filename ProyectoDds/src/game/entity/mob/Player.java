package game.entity.mob;

import game.Game;
import game.entity.projectile.Projectile;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.input.Keyboard;
import game.input.Mouse;

public class Player extends Mob {
	private Keyboard input;
	private Sprite sprite;
	private int animated = 0;
	private boolean walking;

	public Player(Keyboard input) {

		this.input = input;
		sprite = Sprite.player_fordward;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;

	}

	public void update() {
		int xa = 0, ya = 0;
		if (animated < 60)
			animated++;
		else
			animated = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
		System.out.println(projectiles.size());
	}

	private void clear() {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isRemoved()){projectiles.remove(i);}
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
		}
	}

	public void render(Screen screen) {

		int dir = this.dir;
		int flip = 0;
		switch (dir) {
		case 0:
			sprite = Sprite.player_fordward;
			if (walking) {
				if (animated < 30) {
					sprite = Sprite.player_mov_f1;
				} else
					sprite = Sprite.player_mov_f2;
			}
			break;
		case 1:
		case 3:
			sprite = Sprite.player_right;
			if (dir == 3) flip = 1;
			if (walking) {
				if (animated < 30) {
					sprite = Sprite.player_mov_r1;
				} else
					sprite = Sprite.player_mov_r2;
			}
			break;
		case 2:
			sprite = Sprite.player_backward;
			if (walking) {
				if (animated < 30) {
					sprite = Sprite.player_mov_b1;
				} else
					sprite = Sprite.player_mov_b2;
			}
			break;

		default:
			sprite = Sprite.player_fordward;
			break;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
