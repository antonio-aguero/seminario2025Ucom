package spaceinvaders.entities;

import spaceinvaders.patterns.HorizontalMovementStrategy;
import java.awt.*;

/**
 * Enemigo rápido - usa estrategia de movimiento horizontal
 */
public class FastEnemy extends Enemy {
    
    public FastEnemy(int x, int y) {
        super(x, y, 30, 20, Color.RED, 30);
        setMovementStrategy(new HorizontalMovementStrategy(3));
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        // Dibujar como un rombo para diferenciarlo
        int[] xPoints = {x + width/2, x + width, x + width/2, x};
        int[] yPoints = {y, y + height/2, y + height, y + height/2};
        g.fillPolygon(xPoints, yPoints, 4);
        
        g.setColor(Color.WHITE);
        g.drawPolygon(xPoints, yPoints, 4);
    }
}
