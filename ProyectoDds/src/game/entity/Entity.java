package game.entity;

import java.util.Random;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;

/**
 * Clase Abstracta para la creación, modificación y actualización de los elementos que se mueven en la
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
	 * Constructor vacío que implementarán los hijos
	 */
	public Entity() {
	}

	/**
	 * Constructor que inicializa el Entity
	 * 
	 * @param x posición horizontal en el mapa
	 * @param y posición vertical en el mapa
	 * @param sprite contiene los graficos que se renderizan del Entity
	 */
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	/**
	 * Método que actualiza el estado del Entity a implementar por sus hijos
	 */
	public void update() {

	}

	/**
	 * Método para renderizar el entity en el mapa
	 * 
	 * @param screen Ventana en la que se va a renderizar el Entity
	 */
	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite);

	}

	/**
	 * Método que marca como eliminado el entity para que la lógica del juego la borre al acualizar el estado
	 * del juego
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * @return la posición x del entity
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return la posición y del entity
	 */
	public double getY() {
		return y;
	}

	/**
	 * Método que recibe la posiciín del elemento en el mapa generador y lo transforma a las coordenadas del
	 * mapa renderizado
	 * 
	 * @param i posición x del mapa generador
	 * @param j posición y del mapa generador
	 */
	public void setXY(int i, int j) {
		this.x = i * 16;
		this.y = j * 16;
	}

	/**
	 * Devuelve el Sprite del entity con sus gráficos
	 * 
	 * @return el Sprite del Entity
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Método que inicializa el Entity
	 * 
	 * @param level asigna el nivel dado al que pertenece el Entity
	 */
	public void init(Level level) {
		this.level = level;
	}

	/**
	 * Método que indica si el objeto ha de ser eliminado o no
	 * 
	 * @return removed = true si se tiene que elminar el objeto y false si no
	 */
	public boolean isRemoved() {
		return removed;
	}
}
