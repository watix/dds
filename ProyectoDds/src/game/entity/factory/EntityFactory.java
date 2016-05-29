package game.entity.factory;

import game.entity.Entity;
import game.entity.mob.Chaser;
import game.entity.mob.Dummy;
import game.level.tile.Tile;

public class EntityFactory implements EntityFactoryInterface {

	public Entity crearEntity(int x, int y, int type) {
		switch (type) {
		case Tile.col_chaser:
			return new Chaser(x, y);
		case Tile.col_dummy:
			return new Dummy(x, y);
		default:
			return new Dummy(x,y);

		}
	}

}
