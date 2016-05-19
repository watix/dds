package game.entity.mob;

import game.entity.Entity;
import game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	

	public void move(int xa, int ya) {
		if(xa!=0 && ya!=0){
			move(xa,0);
			move(0,ya);
		}
		if(xa>0) dir = 1;//right
		if(xa<0) dir = 3;//left
		if(ya>0) dir = 2;//down
		if(ya<0) dir = 0;//up

		
		
		if(!collition(xa, ya)){
		x += xa;
		y += ya;
		}
	}

	public void update() {

	}

	private boolean collition(int xa, int ya) {
		boolean solid = false;
		if(level.getTile((x+xa)/16, (y+ya+16)/16).solid()) solid = true;
		return solid;
	}

	public void render() {

	}

}

 
