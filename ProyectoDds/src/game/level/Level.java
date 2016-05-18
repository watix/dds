package game.level;

import game.graphics.Screen;
import game.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.height = height;
		this.width = width;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	protected void generateLevel() {

	}

	private void loadLevel(String path) {

	}

	public void update() {

	}

	private void time() {

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;// (xScroll/16)
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;// (xScroll/16)
		int y1 = (yScroll + screen.height +16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0)
			return Tile.rock;
		if (tiles[x + y * width] == 1)
			return Tile.flower;
		if (tiles[x + y * width] == 2)
			return Tile.grass;
		return Tile.grass;
	}
}
