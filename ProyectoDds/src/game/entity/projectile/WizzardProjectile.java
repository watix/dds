package game.entity.projectile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class WizzardProjectile extends Projectile {

	public WizzardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 300;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.flower;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		move();

	}

	protected void move() {
		x += nx;
		y += ny;
		range--;
		if (range == 0) this.remove();
	}
	
	public void render(Screen screen){
		
		screen.renderTile(x, y, sprite);
	}

}
