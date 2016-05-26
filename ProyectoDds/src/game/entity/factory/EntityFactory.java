package game.entity.factory;

import game.entity.Entity;
import game.entity.mob.Chaser;
import game.entity.mob.Dummy;

public class EntityFactory implements EntityFactoryMethod {

	public Entity crearEntity(int x, int y, int type) {
		switch (type) {
		case 0:
			return new Chaser(x, y);
		case 1:
			return new Dummy(x, y);
		default:
			return new Dummy(x,y);

		}
	}

}
