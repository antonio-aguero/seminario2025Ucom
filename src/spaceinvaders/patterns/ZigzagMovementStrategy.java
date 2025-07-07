package spaceinvaders.patterns;

/**
 * Estrategia de movimiento en zigzag para enemigos especiales
 */
public class ZigzagMovementStrategy implements MovementStrategy {
    private int newX, newY;
    private int speed;
    private long timeAccumulator = 0;
    private boolean movingRight = true;
    
    public ZigzagMovementStrategy(int speed) {
        this.speed = speed;
    }
    
    @Override
    public void move(int currentX, int currentY, long deltaTime) {
        timeAccumulator += deltaTime;
        
        // Cambiar dirección cada 1000ms (1 segundo)
        if (timeAccumulator > 1000) {
            movingRight = !movingRight;
            timeAccumulator = 0;
        }
        
        // Movimiento horizontal
        int horizontalMovement = movingRight ? speed : -speed;
        newX = currentX + horizontalMovement;
        
        // Movimiento vertical constante hacia abajo
        newY = currentY + 1;
        
        // Mantener dentro de los límites horizontales
        if (newX < 0) {
            newX = 0;
            movingRight = true;
        } else if (newX > 750) {
            newX = 750;
            movingRight = false;
        }
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
