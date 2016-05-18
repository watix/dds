package game.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
//	public static Sprite player0 = new Sprite(16, 0 , 10, SpriteSheet.tiles);
//	public static Sprite player1 = new Sprite(16, 1 , 10, SpriteSheet.tiles);
//	public static Sprite player2 = new Sprite(16, 0 , 11, SpriteSheet.tiles);
//	public static Sprite player3 = new Sprite(16, 1 , 11, SpriteSheet.tiles);
	
	public static Sprite player_fordward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_backward = new Sprite(32, 2, 5, SpriteSheet.tiles);
	//public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	public static Sprite player_mov_f1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_mov_f2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_mov_r1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_mov_r2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_mov_b1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_mov_b2 = new Sprite(32, 2, 7, SpriteSheet.tiles);




	public Sprite(int size, int x, int y, SpriteSheet sheet) {

		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	public Sprite(int size, int color){
		
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
		
	}

	private void setColor(int color) {
		for(int i =0 ; i < SIZE*SIZE ; i++){
			pixels[i] = color;
		}
		
	}
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
