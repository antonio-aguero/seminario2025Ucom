package spaceinvaders.entities;

import spaceinvaders.patterns.HorizontalMovementStrategy;
import java.awt.*;

/**
 * Enemigo normal - usa estrategia de movimiento horizontal estándar
 */
public class NormalEnemy extends Enemy {
    
    public NormalEnemy(int x, int y) {
        super(x, y, 35, 25, Color.BLUE, 20);
        setMovementStrategy(new HorizontalMovementStrategy(2));
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Agregar detalles visuales
        g.setColor(Color.CYAN);
        g.fillRect(x + 5, y + 5, width - 10, height - 10);
        
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }
}
