package spaceinvaders.patterns;

import spaceinvaders.entities.*;

/**
 * Patrón Factory - Fábrica para crear diferentes tipos de enemigos
 */
public class EnemyFactory {
    
    public Enemy createEnemy(String type, int x, int y) {
        switch (type.toUpperCase()) {
            case "FAST":
                return new FastEnemy(x, y);
            case "NORMAL":
                return new NormalEnemy(x, y);
            case "HEAVY":
                return new HeavyEnemy(x, y);
            case "BOSS":
                return new BossEnemy(x, y);
            default:
                return new NormalEnemy(x, y);
        }
    }
    
    public Bullet createBullet(String type, int x, int y, boolean isPlayerBullet) {
        switch (type.toUpperCase()) {
            case "NORMAL":
                return new Bullet(x, y, isPlayerBullet);
            case "LASER":
                return new LaserBullet(x, y, isPlayerBullet);
            case "PLASMA":
                return new PlasmaBullet(x, y, isPlayerBullet);
            default:
                return new Bullet(x, y, isPlayerBullet);
        }
    }
}
