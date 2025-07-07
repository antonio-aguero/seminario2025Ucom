package spaceinvaders.patterns;

/**
 * Estrategia de movimiento directo hacia abajo para enemigos rápidos
 */
public class StraightDownMovementStrategy implements MovementStrategy {
    private int newX, newY;
    private int speed;
    
    public StraightDownMovementStrategy(int speed) {
        this.speed = speed;
    }
    
    @Override
    public void move(int currentX, int currentY, long deltaTime) {
        newX = currentX;
        newY = currentY + speed;
    }
    
    @Override
    public int getNewX() {
        return newX;
    }
    
    @Override
    public int getNewY() {
        return newY;
    }
}
