package game.level;

import game.graphics.Screen;

public class Level {

	private int width, height;
	private int[] tiles;

	public Level(int width, int height) {
		this.height = height;
		this.width = width;
		tiles = new int[width * height];
		generateRandomLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	private void generateRandomLevel() {

	}

	private void loadLevel(String path) {

	}
	public void update(){
		
	}
	public void render(int xScroll, int yScroll, Screen screen){
		
	}
}
