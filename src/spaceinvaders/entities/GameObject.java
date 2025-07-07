package spaceinvaders.entities;

import java.awt.*;

/**
 * Clase base para todas las entidades del juego
 */
public abstract class GameObject {
    protected int x, y;
    protected int width, height;
    protected Color color;
    protected boolean active;
    
    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.active = true;
    }
    
    public abstract void update();
    public abstract void render(Graphics2D g);
    
    public boolean collidesWith(GameObject other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }
    
    // Getters y Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }
    public boolean isActive() { return active; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setActive(boolean active) { this.active = active; }
}
