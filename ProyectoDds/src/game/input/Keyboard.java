package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 *         Clase para capturar los eventos del teclado.
 */
public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];

	/**
	 * Método que actualiza si ciertas teclas han sido pulsadas y lanzan su evento correspondiente
	 */
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

	}

	public boolean up, down, left, right;

	/*
	 * Método que actualiza si una tecla está pulsada
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/*
	 * Método que actualiza si una tecla está liberada
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	public void keyTyped(KeyEvent e) {

	}

}
