package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

/**
 * Clase que contiene el constructor y los métodos para las Tile de tipo Flor
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo *
 */
public class FlowerTile extends Tile {
	/**
	 * Crea una FlowerTile y le asigna su Sprite
	 * 
	 * @param sprite que contiene los gráficos de la Tile
	 */
	public FlowerTile(Sprite sprite) {
		super(sprite);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.level.tile.Tile#render(int, int, game.graphics.Screen)
	 */
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}

}
