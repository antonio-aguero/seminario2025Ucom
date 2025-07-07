package spaceinvaders.patterns;

import spaceinvaders.entities.*;
import spaceinvaders.game.GameWindow;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Patrón Singleton - GameManager
 * Maneja el estado global del juego y asegura que solo exista una instancia
 */
public class GameManager {
    private static GameManager instance;
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> playerBullets;
    private List<Bullet> enemyBullets;
    private int score;
    private int level;
    private boolean gameRunning;
    private GameWindow gameWindow;
    
    // Constructor privado para evitar instanciación externa
    private GameManager() {
        this.enemies = new ArrayList<>();
        this.playerBullets = new ArrayList<>();
        this.enemyBullets = new ArrayList<>();
        this.score = 0;
        this.level = 1;
        this.gameRunning = true;
        initializeGame();
    }
    
    // Método para obtener la única instancia (Thread-safe)
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    private void initializeGame() {
        // Crear el jugador en el centro inferior
        this.player = new Player(375, 550);
        
        // Crear enemigos usando el Factory
        EnemyFactory enemyFactory = new EnemyFactory();
        createEnemyFormation(enemyFactory);
    }
    
    private void createEnemyFormation(EnemyFactory factory) {
        enemies.clear();
        // Crear formación de enemigos (5 filas, 10 columnas)
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                int x = 100 + col * 60;
                int y = 50 + row * 50;
                
                // Diferentes tipos de enemigos según la fila
                String enemyType;
                if (row == 0) {
                    enemyType = "FAST";
                } else if (row <= 2) {
                    enemyType = "NORMAL";
                } else {
                    enemyType = "HEAVY";
                }
                
                Enemy enemy = factory.createEnemy(enemyType, x, y);
                enemies.add(enemy);
            }
        }
    }
    
    public void update() {
        if (!gameRunning) return;
        
        // Actualizar jugador
        player.update();
        
        // Actualizar enemigos
        for (Enemy enemy : enemies) {
            enemy.update();
            
            // Los enemigos pueden disparar ocasionalmente
            if (Math.random() < 0.001) { // 0.1% de probabilidad por frame
                Bullet bullet = new Bullet(enemy.getX() + enemy.getWidth()/2, 
                                          enemy.getY() + enemy.getHeight(), false);
                enemyBullets.add(bullet);
            }
        }
        
        // Actualizar proyectiles del jugador
        updateBullets(playerBullets);
        
        // Actualizar proyectiles de enemigos
        updateBullets(enemyBullets);
        
        // Verificar colisiones
        checkCollisions();
        
        // Verificar condiciones de victoria/derrota
        checkGameState();
    }
    
    private void updateBullets(List<Bullet> bullets) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update();
            
            // Remover proyectiles que salen de la pantalla
            if (bullet.getY() < 0 || bullet.getY() > 600) {
                iterator.remove();
            }
        }
    }
    
    private void checkCollisions() {
        // Colisiones proyectiles del jugador con enemigos
        Iterator<Bullet> bulletIterator = playerBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (bullet.collidesWith(enemy)) {
                    bulletIterator.remove();
                    enemyIterator.remove();
                    score += enemy.getPoints();
                    break;
                }
            }
        }
        
        // Colisiones proyectiles de enemigos con jugador
        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet bullet = enemyBulletIterator.next();
            if (bullet.collidesWith(player)) {
                enemyBulletIterator.remove();
                player.takeDamage();
                if (player.getLives() <= 0) {
                    gameRunning = false;
                }
                break;
            }
        }
    }
    
    private void checkGameState() {
        // Si no quedan enemigos, siguiente nivel
        if (enemies.isEmpty()) {
            level++;
            EnemyFactory factory = new EnemyFactory();
            createEnemyFormation(factory);
        }
        
        // Si los enemigos llegan muy abajo, game over
        for (Enemy enemy : enemies) {
            if (enemy.getY() > 500) {
                gameRunning = false;
                break;
            }
        }
    }
    
    public void playerShoot() {
        if (playerBullets.size() < 3) { // Máximo 3 proyectiles simultáneos
            Bullet bullet = new Bullet(player.getX() + player.getWidth()/2, 
                                     player.getY(), true);
            playerBullets.add(bullet);
        }
    }
    
    // Getters
    public Player getPlayer() { return player; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<Bullet> getPlayerBullets() { return playerBullets; }
    public List<Bullet> getEnemyBullets() { return enemyBullets; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public boolean isGameRunning() { return gameRunning; }
    
    public void setGameWindow(GameWindow window) {
        this.gameWindow = window;
    }
    
    public void resetGame() {
        score = 0;
        level = 1;
        gameRunning = true;
        enemies.clear();
        playerBullets.clear();
        enemyBullets.clear();
        initializeGame();
    }
}
