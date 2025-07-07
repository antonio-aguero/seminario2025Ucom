package spaceinvaders.entities;

import java.awt.*;

/**
 * Clase que representa los proyectiles
 */
public class Bullet extends GameObject {
    protected int speed;
    protected boolean isPlayerBullet;
    protected int damage;
    
    public Bullet(int x, int y, boolean isPlayerBullet) {
        super(x, y, 4, 10, isPlayerBullet ? Color.YELLOW : Color.RED);
        this.isPlayerBullet = isPlayerBullet;
        this.speed = isPlayerBullet ? -8 : 4; // Negativo para ir hacia arriba
        this.damage = 1;
    }
    
    @Override
    public void update() {
        y += speed;
        
        // Desactivar si sale de la pantalla
        if (y < 0 || y > 600) {
            active = false;
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Efecto de brillo para proyectiles del jugador
        if (isPlayerBullet) {
            g.setColor(Color.WHITE);
            g.fillRect(x + 1, y + 1, width - 2, height - 2);
        }
    }
    
    public boolean isPlayerBullet() {
        return isPlayerBullet;
    }
    
    public int getDamage() {
        return damage;
    }
}
