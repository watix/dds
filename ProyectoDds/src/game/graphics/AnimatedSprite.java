package game.graphics;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 *         Clase para la creación, animación y actualización de los sprites animados
 */
public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int length = -1;
	private int time = 0;

	/**
	 * Constructor de los Sprite animados
	 * 
	 * @param sheet SpriteSheet que contiene los sprites a renderizar en la animación
	 * @param width ancho del sprite individual de la animación
	 * @param height alto del sprite individual de la animación
	 * @param length numero de sprites que contiene la animación
	 */
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("ErrorAnimatedSprite");
	}

	/**
	 * Método para actualizar el sprite de la animación
	 */
	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
		}
	}

	/**
	 * Método que devuelve el sprite actual del elemento que lo llama
	 * 
	 * @return el sprite del elemento que lo llama
	 */
	public Sprite getSprites() {
		return sprite;
	}

	/**
	 * Método que cambia el sprite del elemento que lo llama para conseguir una animación de dicho elemento.
	 * 
	 * @param index índice por el que empezar a coger el sprite que va a renderizarse en la animación
	 */
	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) return;
		sprite = sheet.getSprites()[index];
	}
}
