package game.graphics;

public class AnimatedSprite extends Sprite {

	private int frames = 0;
	private Sprite sprite;
	private int rate = 5;
	private int length = -1;
	private int time = 0;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite=sheet.getSprite()[0];
		if (length > sheet.getSprite().length)
			System.out.println("Error");
	}

	public void update() {
		time++;
		if(time% rate ==0){
		if (frames >= length-1)
			frames = 0;
		else
			frames++;
		sprite = sheet.getSprite()[frames];
		}
		System.out.println(sprite + ", Frame "+ frames);;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		this.rate = frames;
	}
}
