package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

/**
 * Clase que contiene el constructor y los m�todos para las Tile de tipo hierba
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 */
public class GrassTile extends Tile {

	/**
	 * Crea una GrassTile y le asigna su Sprite
	 * 
	 * @param sprite que contiene los gr�ficos de la Tile
	 */
	public GrassTile(Sprite sprite) {
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
