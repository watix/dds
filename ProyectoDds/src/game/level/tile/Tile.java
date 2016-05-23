package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static final int col_grass = 0xFF00FF00;
	public static final int col_water = 0xFF0000FF;
	public static final int col_rock = 0xFFF0F0F0; 
	public static final int col_flower = 0xFFFFFF00; 
	public static final int col_void = 0xFF000000; 
	public static final int spawn = 0xFF000000;
	public static final int col_dummy = 0xFFFFFFFF;

	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile water = new RockTile(Sprite.water);

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
	}

	public boolean solid() {
		return false;
	}
}
