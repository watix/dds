package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Clase para capturar los eventos del rat�n.
 * 
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 */
public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;

	/**
	 * M�todo para obtener la posici�n del rat�n
	 * 
	 * @return la posici�n horizontal del puntero del rat�n
	 */
	public static int getX() {
		return mouseX;
	}

	/**
	 * M�todo para obtener la posici�n del rat�n
	 * 
	 * @return la posici�n vertical del puntero del rat�n
	 */
	public static int getY() {
		return mouseY;
	}

	/**
	 * M�todo que devuelve el bot�n que se est� pulsando en el instante llamado.
	 * 
	 * @return el bot�n pulsado del rat�n
	 */
	public static int getB() {
		return mouseB;
	}

	/**
	 * M�todo que actualiza la posici�n del puntero del rat�n cuando se clica y arrastra con el bot�n pulsado
	 */
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	/**
	 * M�todo que actualiza la posici�n del puntero del rat�n cuando se mueve
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
	 * M�todo que actualiza qu� bot�n se ha pulsado
	 */
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	/**
	 * M�todo que actualiza qu� bot�n se ha liberado
	 */
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

}
