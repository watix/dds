package game.entity.mob.state;

import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.WizzardProjectile;

public class EstadoNormal implements Estado{

	public void shoot(double x, double y, Player player) {
			int nump = player.level.getProjectiles().size();
			if (nump<player.bombas){
			Projectile p = new WizzardProjectile(x, y, player);
			player.level.add(p);
			}
			nump++;
		}

}
