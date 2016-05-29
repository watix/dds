package game.entity;

import java.util.Random;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;

/**
 * Clase Abstracta para la creaci�n, modificaci�n y actualizaci�n de los elementos que se mueven en la
 * pantalla.
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 *
 */
public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	private boolean removed = false;
	public Level level;
	protected final Random random = new Random();

	/**
	 * Constructor vac�o que implementar�n los hijos
	 */
	public Entity() {
	}

	/**
	 * Constructor que inicializa el Entity
	 * 
	 * @param x posici�n horizontal en el mapa
	 * @param y posici�n vertical en el mapa
	 * @param sprite contiene los graficos que se renderizan del Entity
	 */
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	/**
	 * M�todo que actualiza el estado del Entity a implementar por sus hijos
	 */
	public void update() {

	}

	/**
	 * M�todo para renderizar el entity en el mapa
	 * 
	 * @param screen Ventana en la que se va a renderizar el Entity
	 */
	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite);

	}

	/**
	 * M�todo que marca como eliminado el entity para que la l�gica del juego la borre al acualizar el estado
	 * del juego
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * @return la posici�n x del entity
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return la posici�n y del entity
	 */
	public double getY() {
		return y;
	}

	/**
	 * M�todo que recibe la posici�n del elemento en el mapa generador y lo transforma a las coordenadas del
	 * mapa renderizado
	 * 
	 * @param i posici�n x del mapa generador
	 * @param j posici�n y del mapa generador
	 */
	public void setXY(int i, int j) {
		this.x = i * 16;
		this.y = j * 16;
	}

	/**
	 * Devuelve el Sprite del entity con sus gr�ficos
	 * 
	 * @return el Sprite del Entity
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * M�todo que inicializa el Entity
	 * 
	 * @param level asigna el nivel dado al que pertenece el Entity
	 */
	public void init(Level level) {
		this.level = level;
	}

	/**
	 * M�todo que indica si el objeto ha de ser eliminado o no
	 * 
	 * @return removed = true si se tiene que elminar el objeto y false si no
	 */
	public boolean isRemoved() {
		return removed;
	}
}
