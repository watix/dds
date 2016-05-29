package game.graphics;

import java.util.Random;

import game.entity.mob.Chaser;
import game.entity.mob.Mob;

/**
 * Clase que contiene los métodos para crear la Ventana del juego y renderizar los elementos
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public class Screen {
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 8;

	public int xOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * 8];

	private Random random = new Random();

	/**
	 * Constructor de una Ventana con el tamaño indicado en la cual se renderizará el juego
	 * 
	 * @param width anchura de la Screen
	 * @param height altura de la Screen
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xff00ff);
			tiles[0] = 0;
		}
	}

	/**
	 * Método que borra el array de pixeles para su posterior actualización
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	/**
	 * Método para renderizar los Sprite en la posición deseada
	 * 
	 * @param xp posición del eje horizontal donde empezar a renderizar
	 * @param yp posición del eje vertical donde empezar a renderizar
	 * @param sprite Sprite que contiene el tamaño y los pixeles a renderizar
	 */
	public void renderSprite(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.getHeigth(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int color = sprite.pixels[x + y * sprite.SIZE];
				if (color != 0xFFFF00FF) pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];

			}
		}
	}

	/**
	 * Método para renderizar las Tiles del Mapa
	 * 
	 * @param xp posición del eje horizontal donde empezar a renderizar
	 * @param yp posición del eje vertical donde empezar a renderizar
	 * @param sprite Que contiene el tamaño y los pixeles a renderizar
	 */
	public void renderTile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int color = sprite.pixels[x + y * sprite.SIZE];
				if (color != 0xFFFF00FF) pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}

	/**
	 * Método que renderiza a Chaser animados
	 * 
	 * @param xp posición del eje horizontal donde empezar a renderizar
	 * @param yp posición del eje vertical donde empezar a renderizar
	 * @param mob Objeto a renderizar que contiene el sprite
	 */
	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int color = mob.getSprite().pixels[xs + ys * 32];
				if ((mob instanceof Chaser) && color == 0xFFED1C24) color = 0xFF0AEA07;
				if (color != 0xFFFF00FF) pixels[xa + ya * width] = color;
			}
		}
	}

	/**
	 * Método que renderiza a los Player y a los Dummy animados
	 * 
	 * @param xp posición del eje horizontal donde empezar a renderizar
	 * @param yp posición del eje vertical donde empezar a renderizar
	 * @param sprite sprite a renderizar
	 * @param flip control para saber si ha de invertirse el Sprite horizontalmente
	 */
	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int color = sprite.pixels[xs + ys * 32];
				if (color != 0xFFFF00FF) pixels[xa + ya * width] = color;
			}
		}
	}

	/**
	 * Asigna los límites de la pantalla a renderizar
	 * 
	 * @param xOffset trozo horizontal a renderizar
	 * @param yOffset trozo vertical a renderizar
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

	}
}
