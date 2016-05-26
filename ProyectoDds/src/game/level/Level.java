package game.level;

import game.entity.Entity;
import game.entity.factory.EntityFactory;
import game.entity.factory.EntityFactoryMethod;
import game.entity.mob.Dummy;
import game.entity.mob.Player;
import game.entity.particle.Particle;
import game.entity.projectile.Projectile;
import game.entity.spawner.ParticleSpawner;
import game.graphics.Screen;
import game.level.tile.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	public Screen screen;
	public boolean render = false;

	public List<Entity> enemyEntities = new ArrayList<Entity>();
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	
	private EntityFactoryMethod factory = new EntityFactory();

	public Level(int width, int height) {
		this.height = height;
		this.width = width;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				// getTile(x, y);
			}
		}
		tile_size = 16;
	}

//	protected void loadLevel(String path) {
//		
//	}

	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < enemyEntities.size(); i++) {
			enemyEntities.get(i).update();
			System.out.println(enemyEntities.size());
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

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < enemyEntities.size(); i++) {
			if (enemyEntities.get(i).isRemoved()) {enemyEntities.remove(i);
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

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	private void time() {

	}//refactorizar

	public boolean tileCollition(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		this.screen = screen;
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;// (xScroll/16) primer chunk
		int x1 = (xScroll + screen.width + 16) >> 4;// ultimo chunk
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

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerAt(int index) {

		return players.get(index);
	}

	public Player getPlayerClient() {
		return players.get(0);
	}

	// metodo universal para obtener todas las entity que est�n en un radio
	// dado.
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
		return result;

	}

	public int[] getEntitiesIndex(Entity e, int radius) {
		int[] result = new int[entities.size() + 1];
		int ex = (int) e.getX();
		int ey = (int) e.getY();

		for (int i = 0; i < entities.size(); i++) {
			result[0] = 1;
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result[i + 1] = i;
		}
		return result;

	}

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

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.spawn) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_water) return Tile.water;
		if (tiles[x + y * width] == Tile.col_dummy) {
			removeTile(x, y);
			add(factory.crearEntity(x, y, 0));
			return Tile.voidTile;
		}//aplicar factory
		
		// if (tiles[x+y*width]== Tile.col_dummy&& entities.size()<2) add(new
		// Dummy(x, y)); mirar solo spawnear uno!

		return Tile.grass;
	}
	
	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			//tiles = this.tiles;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find level path!!!!");
		}
//		add(new Dummy(9, 6)); // a�ade un dummy al nivel
//		add(new Chaser(4, 3));
//		add(new Chaser(8, 3));

	}
	
	public void removeParticles(){
		for(int i = 0; i < particles.size();i++){
			particles.get(i).remove();
		}
		for(int i = 0; i < projectiles.size();i++){
			projectiles.get(i).remove();
		}
	}

	public void removeTile(int ix, int iy) {

		tiles[ix + iy * width] = Tile.col_grass;

	}

	public void removeEnemy(Entity entity) {
		for (Entity e : enemyEntities){
			if (entity.equals(e)) e.remove();
			
		}
		
	}
}
