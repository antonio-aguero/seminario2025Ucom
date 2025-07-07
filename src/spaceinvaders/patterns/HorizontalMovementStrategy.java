package spaceinvaders.patterns;

/**
 * Estrategia de movimiento horizontal clásica de Space Invaders
 */
public class HorizontalMovementStrategy implements MovementStrategy {
    private int newX, newY;
    private static int direction = 1; // 1 para derecha, -1 para izquierda
    private static boolean shouldMoveDown = false;
    private int speed;
    
    public HorizontalMovementStrategy(int speed) {
        this.speed = speed;
    }
    
    @Override
    public void move(int currentX, int currentY, long deltaTime) {
        if (shouldMoveDown) {
            newX = currentX;
            newY = currentY + 20; // Bajar 20 píxeles
            shouldMoveDown = false;
        } else {
            newX = currentX + (direction * speed);
            newY = currentY;
            
            // Verificar si necesita cambiar dirección
            if (newX <= 0 || newX >= 750) {
                direction *= -1;
                shouldMoveDown = true;
                newX = currentX; // No mover horizontalmente este frame
            }
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
    
    public static void resetDirection() {
        direction = 1;
        shouldMoveDown = false;
    }
}
