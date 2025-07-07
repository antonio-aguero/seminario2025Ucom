package spaceinvaders.entities;

import spaceinvaders.patterns.MovementStrategy;
import java.awt.*;

/**
 * Clase base para todos los enemigos
 */
public abstract class Enemy extends GameObject {
    protected MovementStrategy movementStrategy;
    protected int points;
    protected long lastUpdateTime;
    
    public Enemy(int x, int y, int width, int height, Color color, int points) {
        super(x, y, width, height, color);
        this.points = points;
        this.lastUpdateTime = System.currentTimeMillis();
    }
    
    @Override
    public void update() {
        if (!active) return;
        
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastUpdateTime;
        
        if (movementStrategy != null) {
            movementStrategy.move(x, y, deltaTime);
            x = movementStrategy.getNewX();
            y = movementStrategy.getNewY();
        }
        
        lastUpdateTime = currentTime;
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Dibujar borde
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }
    
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
    
    public int getPoints() {
        return points;
    }
}
