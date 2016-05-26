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

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = 168; // width / 16 * 9;
	private static int scale = 3;
	public static String title = "AWesoME LEVEL ";
	private static Game game;
	private static int stage = 1;

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

	private Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new Level("/textures/levels/map1.png");
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		thread = new Thread(this, "Display");
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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
				frame.setTitle(title +stage+ "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

	int x = 0, y = 0;

	public void update() {
		key.update();
		level.update();

		if (level.enemyEntities.size() == 0) {
			stage++;
			level.loadLevel("/textures/levels/map3.png");
			level.removeParticles();
			// level = new SpawnLevel("/textures/levels/map2.png");
			playerSpawn = new TileCoordinate(8, 8);
			player.estado = new EstadoNormal();

			player.setXY(8, 8);

			// = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
			// level.add(player);
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		// singleton
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		// limpia la pantalla el array pixels con la info a renderizar
		screen.clear();
		// hace que el player esté siempre en el centro de la pantalla con los
		// offset
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;
		//
		level.render((int) xScroll, (int) yScroll, screen);
		// screen.renderSheet(40, 40, SpriteSheet.player_down, false); muestra
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
	}

	public static Game getGame() {
		if (game != null) return game;
		else {
			return new Game();
		}
	}

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

	}
}
