package game.graphics;

/**
 * Clase que contiene los constructores de Sprites
 * @author  Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class Sprite {

	public final int SIZE;
	private int x, y;
	private int height, width;
	public int[] pixels;
	protected SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite water = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite breakable = new Sprite(16, 3, 0, SpriteSheet.tiles);

	public static Sprite projectile = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite bomb = new Sprite(16, 1, 1, SpriteSheet.tiles);

	public static Sprite dummy = new Sprite(16, 0, 0, SpriteSheet.dummy_down);

	public static Sprite player_fordward = new Sprite(32, 0, 5, SpriteSheet.tiles);

	public static Sprite particle_normal = new Sprite(3, 0xFFAA00AA);

	/**
	 * Constructor que crea el Sprite a renderizar del tamaño dado para la creación de Sprites animados
	 * @param sheet
	 * @param width
	 * @param height
	 */
	protected Sprite(SpriteSheet sheet, int width, int height) {

		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	/**
	 * Constructor que crea el Sprite a renderizar del tamaño y en la posición del SpriteSheet dado
	 * @param size tamaño del Sprite en pixeles
	 * @param x posición en el eje horizontal en la que se encuentra el Sprite en el SpriteSheet
	 * @param y posición en el eje vertical en la que se encuentra el Sprite en el SpriteSheet
	 * @param sheet SriteSheet que contiene los sprites a renderizar
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {

		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}


	/**
	 * Constructor que crea un Sprite de color uniforme de un tamaño dado.
	 * @param size tamaño del Sprite a crear.
	 * @param color Color del Sprite a crear.
	 */
	public Sprite(int size, int color) {

		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);

	}

	/**
	 * Constructor que crea un Sprite a partir de un array de pixeles con la anchura y altura dada
	 * @param pixels array de pixeles ya coloreados
	 * @param width anchura del sprite
	 * @param height altura del sprite
	 */
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	/**
	 * Método que rellena un Sprite de un color dado
	 * @param color color con el que rellenar el Sprite
	 */
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}

	}

	/**
	 * @return La anchura del Sprite
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return La altura del Sprite
	 */
	public int getHeigth() {
		return height;
	}

	/**
	 * Carga en el array de pixeles del sprite los gráficos del SpriteSheet para su posterios renderización
	 */
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
