package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import game.entity.mob.Player;
import game.entity.mob.state.EstadoNormal;
import game.graphics.Screen;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.TileCoordinate;

/**
 * @author Joan Batiste Canet Collado y Jordi Vicedo
 * 
 *         Clase principal en la que se inicializan los elementos necesarios para el correcto funcionamiento
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static Game game;
	private static int width = 300;
	private static int height = 168; // width / 16 * 9;
	private static int scale = 3;
	public static String title = "AWesoME LEVEL ";
	private static int stage = 1;
	private String path = "/textures/levels/map" + stage + ".png";

	private Thread thread;
	private boolean running = false;
	private JFrame frame;

	private Keyboard key;
	private Player player;

	private Level level;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private TileCoordinate playerSpawn = new TileCoordinate(8, 8);

	/**
	 * Método constructor de la clase principal Game. Se inicializan los diferentes elementos. Se carga el
	 * mapa de juego con level y se crea el jugador principal
	 */
	private Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new Level(path);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	} // Fin del constructor Game

	/**
	 * @return El ancho de la pantalla de juego
	 */
	public static int getWindowWidth() {
		return width * scale;
	}// Fin del método getWindowWidth

	/**
	 * @return El alto de la pantalla de juego
	 */
	public static int getWindowHeight() {
		return height * scale;
	}// Fin del método getWindowHeight

	/**
	 * Inicializa el hilo de ejecución para el motor del juego
	 */
	public synchronized void start() {
		thread = new Thread(this, "Display");
		thread.start();
		running = true;
	}

	/**
	 * Para la ejecución de la aplicación
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}// Fin del método stop

	/*
	 * Lógica de ejecución de la actualización y renderización de los elementos de la aplicación
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + stage + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}// Fin del método run

	/**
	 * Método que actualiza los elementos del juego y las entradas como el teclado
	 */
	public void update() {
		key.update();
		level.update();

		if (level.enemyEntities.size() == 0) {
			stage++;
			path = "/textures/levels/map" + stage + ".png";
			level.loadLevel(path);
			level.removeParticles();
			playerSpawn = new TileCoordinate(8, 8);
			player.estado = new EstadoNormal();

			player.setXY(8, 8);

		}
		// if (level.players.size() == 0) {
		// stop();
		// }
	}//// Fin del método update

	/**
	 * Método que renderiza los elementos gráficos del juego
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		// limpia la pantalla el array pixels con la info a renderizar
		screen.clear();
		// los Scroll indican el inicio la zona a renderizar en la pantalla de juego
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;

		level.render((int) xScroll, (int) yScroll, screen);
		// el sprite de la animacion

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		g.dispose();
		bs.show();
	}// Fin del método render

	/**
	 * Método SINGLETON para mantener una instancia única de la clase Game
	 * 
	 * @return la instancia única de Game.
	 */
	public static Game getGame() {
		if (game != null) return game;
		else {
			return new Game();
		}
	}

	/**
	 * Método principal que configura la pantalla principal *
	 * 
	 * @param args parámetros estándar
	 */
	public static void main(String[] args) {

		game = Game.getGame();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	} // Fin del método main
} // Fin de la clase Game
