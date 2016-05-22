package game.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int height, width;
	public int[] pixels;
	protected SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite water = new Sprite(16, 5, 0, SpriteSheet.tiles);
	
	public static Sprite projectile = new Sprite(16, 0, 1, SpriteSheet.tiles);

	public static Sprite player_fordward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_backward = new Sprite(32, 2, 5, SpriteSheet.tiles);
	// public static Sprite player_left = new Sprite(32, 3, 5,  SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles);

	public static Sprite player_mov_f1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_mov_f2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_mov_r1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_mov_r2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_mov_b1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_mov_b2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	public static Sprite particle_normal = new Sprite(3, 0xFFAA00AA);

	protected Sprite(SpriteSheet sheet, int width, int height){
		
		SIZE= (width==height) ? width :-1;
		this.width = width;
		this.height=height;
		this.sheet=sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {

		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.height = height;
		this.width = width;
		pixels = new int[height* width];
		setColor(color);
	}

	public Sprite(int size, int color) {

		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);

	}

	public Sprite(int[] Pixels, int width, int height) {
		SIZE= (width==height) ? width :-1;
		this.width=width;
		this.height=height;
		this.pixels=pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
