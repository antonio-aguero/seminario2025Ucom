package spaceinvaders.game;

import spaceinvaders.patterns.GameManager;
import spaceinvaders.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Ventana principal del juego Space Invaders
 */
public class GameWindow extends JFrame implements ActionListener, KeyListener {
    private GamePanel gamePanel;
    private Timer gameTimer;
    private GameManager gameManager;
    private Set<Integer> pressedKeys;
    
    public GameWindow() {
        this.pressedKeys = new HashSet<>();
        initializeWindow();
        initializeGame();
        startGameLoop();
    }
    
    private void initializeWindow() {
        setTitle("Space Invaders - Patrones de Diseño");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        gamePanel = new GamePanel();
        add(gamePanel);
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    private void initializeGame() {
        gameManager = GameManager.getInstance();
        gameManager.setGameWindow(this);
    }
    
    private void startGameLoop() {
        gameTimer = new Timer(16, this); // ~60 FPS
        gameTimer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        handleInput();
        gameManager.update();
        gamePanel.repaint();
    }
    
    private void handleInput() {
        if (pressedKeys.contains(KeyEvent.VK_LEFT) || pressedKeys.contains(KeyEvent.VK_A)) {
            gameManager.getPlayer().moveLeft();
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT) || pressedKeys.contains(KeyEvent.VK_D)) {
            gameManager.getPlayer().moveRight();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        
        // Disparo
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            gameManager.playerShoot();
        }
        
        // Reiniciar juego
        if (e.getKeyCode() == KeyEvent.VK_R && !gameManager.isGameRunning()) {
            gameManager.resetGame();
        }
        
        // Salir
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // No se usa
    }
    
    /**
     * Panel donde se dibuja el juego
     */
    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Fondo del espacio
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            // Dibujar estrellas
            drawStars(g2d);
            
            if (gameManager.isGameRunning()) {
                // Dibujar entidades del juego
                gameManager.getPlayer().render(g2d);
                
                for (Enemy enemy : gameManager.getEnemies()) {
                    enemy.render(g2d);
                }
                
                for (Bullet bullet : gameManager.getPlayerBullets()) {
                    bullet.render(g2d);
                }
                
                for (Bullet bullet : gameManager.getEnemyBullets()) {
                    bullet.render(g2d);
                }
                
                // Dibujar UI
                drawUI(g2d);
            } else {
                // Pantalla de Game Over
                drawGameOver(g2d);
            }
        }
        
        private void drawStars(Graphics2D g) {
            g.setColor(Color.WHITE);
            for (int i = 0; i < 50; i++) {
                int x = (int)(Math.random() * getWidth());
                int y = (int)(Math.random() * getHeight());
                g.fillOval(x, y, 1, 1);
            }
        }
        
        private void drawUI(Graphics2D g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            
            // Puntuación
            g.drawString("Puntuación: " + gameManager.getScore(), 10, 50);
            
            // Nivel
            g.drawString("Nivel: " + gameManager.getLevel(), 10, 75);
            
            // Controles
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString("Controles: izq/der o A/D mover, ESPACIO disparar, ESC salir", 10, getHeight() - 20);
        }
        
        private void drawGameOver(Graphics2D g) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            FontMetrics fm = g.getFontMetrics();
            String gameOverText = "GAME OVER";
            int x = (getWidth() - fm.stringWidth(gameOverText)) / 2;
            int y = getHeight() / 2 - 50;
            g.drawString(gameOverText, x, y);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            fm = g.getFontMetrics();
            String scoreText = "Puntuación Final: " + gameManager.getScore();
            x = (getWidth() - fm.stringWidth(scoreText)) / 2;
            y += 60;
            g.drawString(scoreText, x, y);
            
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            fm = g.getFontMetrics();
            String restartText = "Presiona R para reiniciar";
            x = (getWidth() - fm.stringWidth(restartText)) / 2;
            y += 40;
            g.drawString(restartText, x, y);
        }
    }
}
