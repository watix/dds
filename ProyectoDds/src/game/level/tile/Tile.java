package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

/**
 * Clase para la construcción de las Tiles del mapa
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class Tile {

	public int x, y;
	public Sprite sprite;

	// Colores para la renderización de los tiles a partir de los colores del mapa
	public static final int col_grass = 0xFF00FF00;
	public static final int col_water = 0xFF0000FF;
	public static final int col_rock = 0xFFF0F0F0;
	public static final int col_flower = 0xFFFFFF00;
	public static final int col_void = 0xFF000000;
	public static final int col_break = 0xFF000000;
	public static final int col_dummy = 0xFFFFFFFF;
	public static final int col_chaser = 0xFFFFFFF0;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile breakable = new RockTile(Sprite.breakable);
	public static Tile water = new RockTile(Sprite.water);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	/**
	 * Constructor que asigna el sprite dado a la Tile
	 * 
	 * @param sprite que contiene el diseño a renderizar del Tile
	 */
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @param x posición horizontal
	 * @param y posición vertical
	 * @param screen Objeto Screen en el que va a ser renderizada la Tile
	 */
	public void render(int x, int y, Screen screen) {
	}

	/**
	 * Método que indica si la Tile es sólida y provocará colisión o no
	 * 
	 * @return true si es sólida false si no es sólida
	 */
	public boolean solid() {
		return false;
	}

}
