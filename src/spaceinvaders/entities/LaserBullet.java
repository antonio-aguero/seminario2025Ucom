package spaceinvaders.entities;

import java.awt.*;

/**
 * Proyectil láser - más rápido y con efecto visual especial
 */
public class LaserBullet extends Bullet {
    
    public LaserBullet(int x, int y, boolean isPlayerBullet) {
        super(x, y, isPlayerBullet);
        this.speed = isPlayerBullet ? -12 : 6;
        this.damage = 2;
        this.width = 6;
        this.height = 15;
        this.color = isPlayerBullet ? Color.CYAN : Color.ORANGE;
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        // Efecto láser con gradiente
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        g.setColor(Color.WHITE);
        g.fillRect(x + 2, y + 2, width - 4, height - 4);
        
        // Efecto de energía
        g.setColor(color.brighter());
        g.drawRect(x - 1, y - 1, width + 2, height + 2);
    }
}
