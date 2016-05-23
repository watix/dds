package game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.mob.Chaser;
import game.entity.mob.Dummy;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			super.tiles= this.tiles;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find level path!!!!");
		}
		add(new Dummy(9, 6)); // añade un dummy al nivel
		add(new Chaser(7, 5)); 
	}

	/*
	 * grass = 0x00FF00 flower = 0xFFFF00 rock = 0xF0F0F0 void = 0x000000
	 */
	protected void generateLevel() {
		
	}
}
