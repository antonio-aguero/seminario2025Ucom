package spaceinvaders.entities;

import java.awt.*;

/**
 * Proyectil de plasma - más daño pero más lento
 */
public class PlasmaBullet extends Bullet {
    private int pulsePhase = 0;
    
    public PlasmaBullet(int x, int y, boolean isPlayerBullet) {
        super(x, y, isPlayerBullet);
        this.speed = isPlayerBullet ? -6 : 3;
        this.damage = 3;
        this.width = 8;
        this.height = 12;
        this.color = isPlayerBullet ? Color.GREEN : Color.MAGENTA;
    }
    
    @Override
    public void update() {
        super.update();
        pulsePhase = (pulsePhase + 1) % 20; // Ciclo de pulsación
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        // Efecto de pulsación
        int pulse = (int)(Math.sin(pulsePhase * 0.3) * 3);
        
        g.setColor(color);
        g.fillOval(x - pulse, y - pulse, width + 2*pulse, height + 2*pulse);
        
        g.setColor(color.brighter());
        g.fillOval(x + 1, y + 1, width - 2, height - 2);
        
        g.setColor(Color.WHITE);
        g.fillOval(x + 3, y + 3, width - 6, height - 6);
    }
}
