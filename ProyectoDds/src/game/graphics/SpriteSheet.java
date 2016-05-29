package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 *         Clase para la construcción de SpriteSheets que contienen los sprites a renderizar
 *
 */
public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;

	public final int WIDTH;
	public final int HEIGHT;

	public Sprite[] sprites;

	// Tile Sprites
	public static SpriteSheet tiles = new SpriteSheet("/textures/sprites/sprites.png", 256);

	// player sprites
	public static SpriteSheet player = new SpriteSheet("/textures/sprites/Player.png", 128, 96);
	public static SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 3, 32);// animacion hacia abajo
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 3, 32);// animacion hacia arriba
	public static SpriteSheet player_left = new SpriteSheet(player, 3, 0, 1, 3, 32);// animacion hacia izda
	public static SpriteSheet player_right = new SpriteSheet(player, 1, 0, 1, 3, 32);// animacion hacia dcha

	// dummy sprites
	public static SpriteSheet dummy = new SpriteSheet("/textures/sprites/Dummy.png", 128, 96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 2, 0, 1, 3, 32);// animacion hacia abajo
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 0, 1, 3, 32);// animacion hacia arriba
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 3, 0, 1, 3, 32);// animacion hacia izda
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 1, 0, 1, 3, 32);// animacion hacia dcha

	/**
	 * Constructor para los Sprites animados
	 * 
	 * @param sheet SpriteSheet que contiene los Sprites de la animación
	 * @param x posición horizontal donde empieza el sprite
	 * @param y posición vertical donde empieza el sprite
	 * @param width anchura del sprite
	 * @param height altura del sprite
	 * @param spriteSize tamaño de la unidad que se va a animar
	 */
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize)
								+ (y0 + ya * spriteSize) * WIDTH];

					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	/**
	 * Constructor de SpriteSheet a partir de una ruta dada y un tamaño definido por ancho y alto para los
	 * sprites animados
	 * 
	 * @param path ruta del archivo de imagen que contiene el mapa de sprites del juego
	 * @param width ancho de la SpriteSheet a construir
	 * @param height alto de la SpriteSheet a construir
	 */
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.SIZE = -1;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	/**
	 * Constructor de SpriteSheet a partir de una ruta dada y un tamaño
	 * 
	 * @param path ruta del archivo de imagen que contiene el mapa de sprites del juego
	 * @param size tamaño de la SpriteSheet a construir
	 */
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		this.WIDTH = size;
		this.HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	/**
	 * @return el array de sprites a renderizar
	 */
	public Sprite[] getSprites() {
		return sprites;
	}

	/**
	 * Método para cargar en el array de pixeles una imagen dada
	 */
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
