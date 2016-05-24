package game.entity.mob;

import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public class Dummy extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite currentAnim = down;
	
	private int time = 0;
	private int xa = 0;
	private int ya = 0;

	private enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction dir;

	public Dummy(int x, int y) {
		this.x = x << 4; // x*16
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	public void update() {
		time++;
		if (time % (random.nextInt(50)+30) == 0) {// cada segundo entra...y cambia de direccion
			xa = random.nextInt(3) -1;
			ya = random.nextInt(3) -1;
			if(random.nextInt(4) == 0){
				xa=0;
				ya=0;
			}
		}

		if (walking)
			currentAnim.update();
		else
			currentAnim.setFrame(0);
		if (ya < 0) {
			currentAnim = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			currentAnim = down;
			dir = Direction.DOWN;
		} else if (xa < 0) {
			currentAnim = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			currentAnim = right;
			dir = Direction.RIGHT;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		sprite = currentAnim.getSprites();
		screen.renderMob((int)x, (int)y, sprite, 0);

	}

}
