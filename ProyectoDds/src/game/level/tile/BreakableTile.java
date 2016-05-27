package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class BreakableTile extends Tile {
	public BreakableTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {

		screen.renderTile(x << 4, y << 4, sprite);
	}
	public boolean solid() {
		return true;
	}


}
