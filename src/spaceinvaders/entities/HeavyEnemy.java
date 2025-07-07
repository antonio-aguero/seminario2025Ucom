package spaceinvaders.entities;

import spaceinvaders.patterns.HorizontalMovementStrategy;
import java.awt.*;

/**
 * Enemigo pesado - más lento pero más resistente
 */
public class HeavyEnemy extends Enemy {
    private int health;
    
    public HeavyEnemy(int x, int y) {
        super(x, y, 40, 30, Color.ORANGE, 10);
        setMovementStrategy(new HorizontalMovementStrategy(1));
        this.health = 2; // Requiere 2 disparos para ser destruido
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        // Color cambia según la salud
        Color currentColor = health > 1 ? color : Color.DARK_GRAY;
        g.setColor(currentColor);
        g.fillRect(x, y, width, height);
        
        // Dibujar armadura
        g.setColor(Color.YELLOW);
        g.drawRect(x + 2, y + 2, width - 4, height - 4);
        g.drawRect(x + 4, y + 4, width - 8, height - 8);
        
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
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
