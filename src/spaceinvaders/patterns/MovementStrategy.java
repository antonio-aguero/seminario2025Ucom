package spaceinvaders.patterns;

/**
 * Patrón Strategy - Interfaz para diferentes estrategias de movimiento
 */
public interface MovementStrategy {
    void move(int currentX, int currentY, long deltaTime);
    int getNewX();
    int getNewY();
}
