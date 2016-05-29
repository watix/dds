package game.level;

/**
 * Clase que guarda las coordenadas de una tile
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class TileCoordinate {

	private int x, y;
	private final int TILE_SIZE = 16;

	/**
	 * Constructor que inicializa el objeto.
	 * 
	 * @param x posición horizontal
	 * @param y posición vertical
	 */
	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}

	/**
	 * @return la posición x de la Tile
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return la posición y de la Tile
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return la posición x e y de la Tile
	 */
	public int[] getXY() {
		int[] tileCoord = new int[2];
		tileCoord[0] = x;
		tileCoord[1] = y;
		return tileCoord;
	}

}
