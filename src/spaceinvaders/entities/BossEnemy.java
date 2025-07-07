package spaceinvaders.entities;

import spaceinvaders.patterns.ZigzagMovementStrategy;
import java.awt.*;

/**
 * Enemigo jefe - usa estrategia de movimiento zigzag
 */
public class BossEnemy extends Enemy {
    private int health;
    
    public BossEnemy(int x, int y) {
        super(x, y, 60, 40, Color.MAGENTA, 100);
        setMovementStrategy(new ZigzagMovementStrategy(2));
        this.health = 5; // Muy resistente
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Detalles del jefe
        g.setColor(Color.PINK);
        g.fillOval(x + 10, y + 5, width - 20, height - 10);
        
        g.setColor(Color.RED);
        g.fillRect(x + 5, y + height/3, width - 10, height/3);
        
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        
        // Mostrar salud del jefe
        g.setColor(Color.WHITE);
        g.drawString("HP: " + health, x, y - 5);
    }
    
    public void takeDamage() {
        health--;
        if (health <= 0) {
            active = false;
        }
    }
    
    public int getHealth() {
        return health;
    }
}
