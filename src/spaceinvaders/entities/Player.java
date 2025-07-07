package spaceinvaders.entities;

import java.awt.*;

/**
 * Clase que representa al jugador
 */
public class Player extends GameObject {
    private int speed;
    private int lives;
    private long lastMoveTime;
    private static final int MOVE_COOLDOWN = 50; // ms entre movimientos
    
    public Player(int x, int y) {
        super(x, y, 40, 30, Color.GREEN);
        this.speed = 5;
        this.lives = 3;
        this.lastMoveTime = 0;
    }
    
    @Override
    public void update() {
        // La actualización del movimiento se maneja en moveLeft/moveRight
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!active) return;
        
        g.setColor(color);
        // Dibujar nave del jugador como un triángulo
        int[] xPoints = {x, x + width/2, x + width};
        int[] yPoints = {y + height, y, y + height};
        g.fillPolygon(xPoints, yPoints, 3);
        
        // Dibujar vidas restantes
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Vidas: " + lives, 10, 25);
    }
    
    public void moveLeft() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime > MOVE_COOLDOWN) {
            if (x > 0) {
                x -= speed;
            }
            lastMoveTime = currentTime;
        }
    }
    
    public void moveRight() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime > MOVE_COOLDOWN) {
            if (x < 800 - width) {
                x += speed;
            }
            lastMoveTime = currentTime;
        }
    }
    
    public void takeDamage() {
        lives--;
        if (lives <= 0) {
            active = false;
        }
    }
    
    public int getLives() {
        return lives;
    }
    
    public void resetLives() {
        lives = 3;
        active = true;
    }
}
