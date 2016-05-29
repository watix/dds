package game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.entity.Entity;
import game.entity.factory.EntityFactory;
import game.entity.factory.EntityFactoryInterface;
import game.entity.mob.Player;
import game.entity.particle.Particle;
import game.entity.projectile.Projectile;
import game.entity.spawner.ParticleSpawner;
import game.graphics.Screen;
import game.level.tile.Tile;

/**
 * Clase Level la encargada de inicializar y de almacenar los elementos del nivel.
 * 
 * @author Joan Batiste Canet Collado Jordi Vicedo
 * 
 */
public class Level {

	protected int width, height;
	public int[] tiles;
	protected int tile_size;
	public Screen screen;
	public boolean render = false;

	/**
	 * Listas de almacenamiento de los elementos del nivel
	 */
	public List<Entity> enemyEntities = new ArrayList<Entity>();
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	public List<Player> players = new ArrayList<Player>();

	/**
	 * Fábrica de enemigos
	 */
	private EntityFactoryInterface factory = new EntityFactory();

	/**
	 * @param path ruta del archivo de imagen que contiene el mapa de pixeles del nivel
	 */
	public Level(String path) {
		loadLevel(path);
		tile_size = 16;
	}

	/**
	 * Método que actualiza cada componente del nivel que ha de renderizarse
	 */
	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < enemyEntities.size(); i++) {
			enemyEntities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		remove();
	}

	/**
	 * Método que elimina los elementos que se renderizan si su estado es isRemoved == true
	 */
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < enemyEntities.size(); i++) {
			if (enemyEntities.get(i).isRemoved()) {
				enemyEntities.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}

	}

	/**
	 * @return Lista de los proyectiles que tiene el nivel
	 */
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Método que devuelve si una Tile es sólida o no
	 * @param x posición de la Tile
	 * @param y posición de la Tile
	 * @param size tamaño de la Tile
	 * @param xOffset limites de renderizado
	 * @param yOffset limites de renderizado
	 * @return si es solido true y si no lo es false
	 */
	public boolean tileCollition(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	/**
	 * Método para renderizar los elementos del nivel
	 * 
	 * @param xScroll límite horizontal
	 * @param yScroll límite vertical
	 * @param screen ventana a renderizar
	 */
	public void render(int xScroll, int yScroll, Screen screen) {
		this.screen = screen;
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;// (xScroll/16) primer chunk
		int x1 = (xScroll + screen.width + 16) >> 4;// último chunk
		int y0 = yScroll >> 4;// (yScroll/16)
		int y1 = (yScroll + screen.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);

			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < enemyEntities.size(); i++) {
			enemyEntities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}

	/**
	 * Método para añadir un Entity al nivel
	 * 
	 * @param e Entity que se desea añadir al nivel
	 */
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof ParticleSpawner) {
			entities.add(e);
		} else {
			enemyEntities.add(e);
		}
	}

	/**
	 * Método universal para obtener todas las entity que están en un radio dado.
	 * 
	 * @param e Entity que llama al método
	 * @param radius Radio efectivo
	 * @return lista con las Entity que se encuentran dentro del radio dado
	 */
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();

		for (int i = 0; i < enemyEntities.size(); i++) {
			Entity entity = enemyEntities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}
		for (int i = 0; i < players.size(); i++) {
			Entity entityP = players.get(i);
			int x = (int) entityP.getX();
			int y = (int) entityP.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entityP);
		}

		return result;

	}

	/**
	 * Método que implementa la lógica para obtener los jugadores que hay en un radio dado
	 * 
	 * @param e Entity que llama al método
	 * @param radius Radio efectivo
	 * @return lista con los Player que se encuentran dentro del radio dado
	 */
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}

	/**
	 * Método que devuelve el tipo de tile que ha de renderizarse en una posición
	 * 
	 * @param x posición en el mapa generador
	 * @param y posición en el mapa generador
	 * @return el tipo de Tile que ha de renderizarse
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_break) return Tile.breakable;
		if (tiles[x + y * width] == Tile.col_chaser) {
			removeTile(x, y);
			add(factory.crearEntity(x, y, 1));
			return Tile.grass;
		}
		if (tiles[x + y * width] == Tile.col_dummy) {
			removeTile(x, y);
			add(factory.crearEntity(x, y, 0));
			return Tile.grass;
		}
		return Tile.grass;
	}

	/**
	 * Método que carga el mapa generador
	 * 
	 * @param path que contiene la ruta del archivo de imagen que contiene el mapa del nivel
	 */
	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find level path!!!!");
		}
	}

	/**
	 * Método que elimina las partículas y los proyectiles que haya en el level
	 */
	public void removeParticlesProyectiles() {
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).remove();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).remove();
		}
	}

	/**
	 * método que elimina una Tile y le asigna una de hierba
	 * @param ix posición de la tile
	 * @param iy posición de la tile
	 */
	public void removeTile(int ix, int iy) {

		tiles[ix + iy * width] = Tile.col_grass;

	}

	/**
	 * Método que elimina una Entity 
	 * @param entity entity a eliminar
	 */
	public void removeEntity(Entity entity) {
		for (Entity e : enemyEntities) {
			if (entity.equals(e)) e.remove();
			if (entity.equals(players.get(0))) players.get(0).remove();

		}

	}
}
