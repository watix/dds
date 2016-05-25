package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class FlowerTile extends Tile {
	public FlowerTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
//System.out.println("me han dado");
		screen.renderTile(x << 4, y << 4, sprite);
	}
	


}
