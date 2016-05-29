package game.graphics;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 *         Clase para la creaci�n, animaci�n y actualizaci�n de los sprites animados
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
	 * @param sheet SpriteSheet que contiene los sprites a renderizar en la animaci�n
	 * @param width ancho del sprite individual de la animaci�n
	 * @param height alto del sprite individual de la animaci�n
	 * @param length numero de sprites que contiene la animaci�n
	 */
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("ErrorAnimatedSprite");
	}

	/**
	 * M�todo para actualizar el sprite de la animaci�n
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
	 * M�todo que devuelve el sprite actual del elemento que lo llama
	 * 
	 * @return el sprite del elemento que lo llama
	 */
	public Sprite getSprites() {
		return sprite;
	}

	/**
	 * M�todo que cambia el sprite del elemento que lo llama para conseguir una animaci�n de dicho elemento.
	 * 
	 * @param index �ndice por el que empezar a coger el sprite que va a renderizarse en la animaci�n
	 */
	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) return;
		sprite = sheet.getSprites()[index];
	}
}
