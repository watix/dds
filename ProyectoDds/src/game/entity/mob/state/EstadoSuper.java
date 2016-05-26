package game.entity.mob.state;

import game.Game;
import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;
import game.input.Mouse;

public class EstadoSuper implements Estado {

	public void shoot(double x, double y, Player player) {
		double dx = Mouse.getX()- Game.getWindowWidth() / 2;
		double dy = Mouse.getY() - Game.getWindowHeight() / 2;
		double dir = Math.atan2(dy, dx);
			int nump = player.level.getProjectiles().size();
			if (nump<player.bombas){
			Projectile p = new WizzardProjectile(x, y, dir, player);
			player.level.add(p);
			}
			nump++;
		}
	}
