package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Clase para capturar los eventos del ratón.
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 */
public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;

	/**
	 * Método para obtener la posición del ratón
	 * 
	 * @return la posición horizontal del puntero del ratón
	 */
	public static int getX() {
		return mouseX;
	}

	/**
	 * Método para obtener la posición del ratón
	 * 
	 * @return la posición vertical del puntero del ratón
	 */
	public static int getY() {
		return mouseY;
	}

	/**
	 * Método que devuelve el botón que se está pulsando en el instante llamado.
	 * 
	 * @return el botón pulsado del ratón
	 */
	public static int getB() {
		return mouseB;
	}

	/**
	 * Método que actualiza la posición del puntero del ratón cuando se clica y arrastra con el botón pulsado
	 */
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	/**
	 * Método que actualiza la posición del puntero del ratón cuando se mueve
	 */
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Método que actualiza qué botón se ha pulsado
	 */
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	/**
	 * Método que actualiza qué botón se ha liberado
	 */
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

}
