package game.entity.mob;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.input.Keyboard;

public class Player extends Mob {
	private Keyboard input;
	private Sprite sprite;

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
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;

		if (xa != 0 || ya != 0)
			move(xa, ya);
	}

	public void render(Screen screen) {
		/*
		 * int xx = x - 16; int yy = y - 16; screen.renderPlayer(xx, yy,
		 * Sprite.player0); screen.renderPlayer(xx + 16, yy, Sprite.player1);
		 * screen.renderPlayer(xx, yy + 16, Sprite.player2);
		 * screen.renderPlayer(xx + 16, yy + 16, Sprite.player3);
		 */
		int dir = this.dir;
		int flip = 0;
		switch (dir) {
		case 0:
			sprite = Sprite.player_fordward;
			break;
		case 1:
			sprite = Sprite.player_right;
			break;
		case 2:
			sprite = Sprite.player_backward;
			break;
		case 3:
			sprite = Sprite.player_right;
			flip = 1;
			break;
		default: sprite = Sprite.player_fordward;
			break;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
