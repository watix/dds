package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

/**
 * Clase que contiene el constructor y los métodos para las Tile vacías aquellas que están fuera del mapa
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class VoidTile extends Tile {

	/**
	 * Crea una VoidTile y le asigna su Sprite
	 * 
	 * @param sprite que contiene los gráficos de la Tile
	 */
	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	/* (non-Javadoc)
	 * @see game.level.tile.Tile#render(int, int, game.graphics.Screen)
	 */
	public void render(int x, int y, Screen screen) {

		screen.renderTile(x << 4, y << 4, sprite);
	}

	/* (non-Javadoc)
	 * @see game.level.tile.Tile#solid()
	 */
	public boolean solid() {
		return true;
	}
}
