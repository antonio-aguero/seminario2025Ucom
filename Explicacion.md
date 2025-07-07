# Explicación Completa del Proyecto Space Invaders

## 📋 Índice
1. [Introducción](#-introducción)
2. [Patrones de Diseño Implementados](#-patrones-de-diseño-implementados)
3. [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
4. [Análisis Detallado de Clases](#-análisis-detallado-de-clases)
5. [Flujo de Ejecución](#-flujo-de-ejecución)
6. [Beneficios de los Patrones](#-beneficios-de-los-patrones)
7. [Ejemplos de Uso](#-ejemplos-de-uso)
8. [Extensibilidad](#-extensibilidad)

---

## 🎯 Introducción

Este proyecto implementa un juego **Space Invaders** en Java con el objetivo principal de demostrar la aplicación práctica de tres patrones de diseño fundamentales:

- **Singleton**: Para gestión centralizada del estado
- **Factory**: Para creación flexible de objetos
- **Strategy**: Para comportamientos intercambiables

El juego no solo es funcional y entretenido, sino que sirve como **ejemplo educativo** de cómo los patrones de diseño mejoran la estructura, mantenibilidad y extensibilidad del código.

---

## 🔧 Patrones de Diseño Implementados

### 1. 🔄 Patrón Singleton - GameManager

#### **¿Qué es?**
El patrón Singleton garantiza que una clase tenga **una sola instancia** y proporciona un punto de acceso global a ella.

#### **¿Por qué lo usamos?**
En un juego, necesitamos un **controlador central** que:
- Mantenga el estado global (puntuación, nivel, vidas)
- Coordine todas las entidades (jugador, enemigos, proyectiles)
- Evite conflictos de estado entre diferentes partes del código

#### **Implementación en GameManager:**

```java
public class GameManager {
    private static GameManager instance;  // Única instancia
    
    // Constructor privado - nadie puede crear instancias
    private GameManager() {
        // Inicialización...
    }
    
    // Método thread-safe para obtener la instancia
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
}
```

#### **Ventajas:**
- ✅ **Control centralizado**: Todo el estado del juego en un lugar
- ✅ **Thread-safe**: Seguro en aplicaciones multihilo
- ✅ **Acceso global**: Cualquier clase puede acceder al GameManager
- ✅ **Evita duplicación**: Imposible tener múltiples gestores conflictivos

#### **Responsabilidades del GameManager:**
- Gestionar lista de enemigos, proyectiles y jugador
- Controlar puntuación y nivel
- Detectar colisiones
- Verificar condiciones de victoria/derrota
- Coordinar la actualización de todas las entidades

---

### 2. 🏭 Patrón Factory - EnemyFactory

#### **¿Qué es?**
El patrón Factory proporciona una interfaz para crear objetos sin especificar sus clases exactas.

#### **¿Por qué lo usamos?**
Necesitamos crear diferentes tipos de enemigos sin que el código cliente (GameManager) sepa los detalles de construcción de cada tipo.

#### **Implementación en EnemyFactory:**

```java
public class EnemyFactory {
    public Enemy createEnemy(String type, int x, int y) {
        switch (type.toUpperCase()) {
            case "FAST":
                return new FastEnemy(x, y);
            case "NORMAL":
                return new NormalEnemy(x, y);
            case "HEAVY":
                return new HeavyEnemy(x, y);
            case "BOSS":
                return new BossEnemy(x, y);
            default:
                return new NormalEnemy(x, y);
        }
    }
}
```

#### **Tipos de Enemigos Creados:**

1. **FastEnemy** (Rápido)
   - Color: Rojo
   - Forma: Rombo
   - Puntos: 30
   - Velocidad: Alta (3 píxeles)

2. **NormalEnemy** (Normal)
   - Color: Azul
   - Forma: Rectángulo con detalles
   - Puntos: 20
   - Velocidad: Media (2 píxeles)

3. **HeavyEnemy** (Pesado)
   - Color: Naranja
   - Forma: Rectángulo con armadura
   - Puntos: 10
   - Características: Requiere 2 disparos, velocidad lenta (1 píxel)

4. **BossEnemy** (Jefe)
   - Color: Magenta
   - Forma: Grande con detalles especiales
   - Puntos: 100
   - Características: 5 puntos de vida, movimiento zigzag

#### **Ventajas:**
- ✅ **Extensibilidad**: Agregar nuevos enemigos es simple
- ✅ **Encapsulación**: Lógica de creación separada
- ✅ **Flexibilidad**: Fácil cambiar tipos de enemigos
- ✅ **Mantenimiento**: Cambios en un solo lugar

---

### 3. 🎯 Patrón Strategy - MovementStrategy

#### **¿Qué es?**
El patrón Strategy define una familia de algoritmos, los encapsula y los hace intercambiables.

#### **¿Por qué lo usamos?**
Diferentes enemigos necesitan **comportamientos de movimiento distintos**. En lugar de usar condicionales complejas, cada estrategia es una clase separada.

#### **Interfaz MovementStrategy:**

```java
public interface MovementStrategy {
    void move(int currentX, int currentY, long deltaTime);
    int getNewX();
    int getNewY();
}
```

#### **Estrategias Implementadas:**

##### **1. HorizontalMovementStrategy** (Movimiento Clásico)
```java
public class HorizontalMovementStrategy implements MovementStrategy {
    private static int direction = 1;  // 1 = derecha, -1 = izquierda
    private static boolean shouldMoveDown = false;
    
    public void move(int currentX, int currentY, long deltaTime) {
        if (shouldMoveDown) {
            // Bajar una fila
            newY = currentY + 20;
            shouldMoveDown = false;
        } else {
            // Mover horizontalmente
            newX = currentX + (direction * speed);
            
            // Si llega al borde, cambiar dirección
            if (newX <= 0 || newX >= 750) {
                direction *= -1;
                shouldMoveDown = true;
            }
        }
    }
}
```
**Uso:** FastEnemy, NormalEnemy, HeavyEnemy

##### **2. ZigzagMovementStrategy** (Movimiento en Zigzag)
```java
public class ZigzagMovementStrategy implements MovementStrategy {
    private boolean movingRight = true;
    private long timeAccumulator = 0;
    
    public void move(int currentX, int currentY, long deltaTime) {
        timeAccumulator += deltaTime;
        
        // Cambiar dirección cada segundo
        if (timeAccumulator > 1000) {
            movingRight = !movingRight;
            timeAccumulator = 0;
        }
        
        // Movimiento diagonal
        int horizontalMovement = movingRight ? speed : -speed;
        newX = currentX + horizontalMovement;
        newY = currentY + 1;  // Siempre baja
    }
}
```
**Uso:** BossEnemy

##### **3. StraightDownMovementStrategy** (Movimiento Directo)
```java
public class StraightDownMovementStrategy implements MovementStrategy {
    public void move(int currentX, int currentY, long deltaTime) {
        newX = currentX;  // No se mueve horizontalmente
        newY = currentY + speed;  // Solo baja
    }
}
```
**Uso:** Enemigos especiales o power-ups futuros

#### **Ventajas:**
- ✅ **Intercambiable**: Cambiar comportamiento en tiempo de ejecución
- ✅ **Extensible**: Agregar nuevos movimientos fácilmente
- ✅ **Testeable**: Cada estrategia se puede probar independientemente
- ✅ **Legible**: Código claro y organizado

---

## 🏗️ Arquitectura del Proyecto

### **Estructura de Paquetes:**

```
spaceinvaders/
├── entities/          # Objetos del juego
│   ├── GameObject     # Clase base abstracta
│   ├── Player         # Nave del jugador
│   ├── Enemy          # Clase base de enemigos
│   ├── [Tipos de Enemy] # FastEnemy, NormalEnemy, etc.
│   └── [Tipos de Bullet] # Bullet, LaserBullet, etc.
├── patterns/          # Implementaciones de patrones
│   ├── GameManager    # Singleton
│   ├── EnemyFactory   # Factory
│   └── [Strategies]   # MovementStrategy y implementaciones
└── game/             # Lógica principal y UI
    ├── SpaceInvadersGame  # Clase main
    └── GameWindow         # Interfaz gráfica Swing
```

### **Diagrama de Dependencias:**

```
SpaceInvadersGame
        ↓
    GameWindow
        ↓
    GameManager (Singleton)
        ↓
    EnemyFactory (Factory)
        ↓
    Enemy + MovementStrategy (Strategy)
```

---

## 🔍 Análisis Detallado de Clases

### **GameObject (Clase Base)**
```java
public abstract class GameObject {
    protected int x, y, width, height;
    protected Color color;
    protected boolean active;
    
    // Métodos abstractos que deben implementar las subclases
    public abstract void update();
    public abstract void render(Graphics2D g);
    
    // Método común para detectar colisiones
    public boolean collidesWith(GameObject other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }
}
```

**Propósito:** Clase base que define propiedades y métodos comunes para todas las entidades del juego.

### **Player (Jugador)**
```java
public class Player extends GameObject {
    private int speed = 5;
    private int lives = 3;
    private long lastMoveTime;
    
    public void moveLeft() {
        if (currentTime - lastMoveTime > MOVE_COOLDOWN) {
            if (x > 0) x -= speed;
            lastMoveTime = currentTime;
        }
    }
    
    public void moveRight() {
        if (currentTime - lastMoveTime > MOVE_COOLDOWN) {
            if (x < 800 - width) x += speed;
            lastMoveTime = currentTime;
        }
    }
}
```

**Características:**
- Control de cooldown para movimiento suave
- Sistema de vidas
- Límites de pantalla
- Renderizado como triángulo verde

### **Enemy (Clase Base de Enemigos)**
```java
public abstract class Enemy extends GameObject {
    protected MovementStrategy movementStrategy;
    protected int points;
    
    public void update() {
        if (movementStrategy != null) {
            movementStrategy.move(x, y, deltaTime);
            x = movementStrategy.getNewX();
            y = movementStrategy.getNewY();
        }
    }
    
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
}
```

**Características:**
- Usa Strategy pattern para movimiento
- Cada tipo tiene diferentes puntos
- Actualización basada en estrategia asignada

---

## ⚡ Flujo de Ejecución

### **1. Inicialización:**
```
SpaceInvadersGame.main()
    ↓
GameWindow.constructor()
    ↓
GameManager.getInstance()  // Singleton
    ↓
EnemyFactory.createEnemyFormation()  // Factory
    ↓
Enemy.setMovementStrategy()  // Strategy
```

### **2. Game Loop (60 FPS):**
```
Timer.actionPerformed() cada 16ms
    ↓
handleInput()  // Procesar teclas presionadas
    ↓
GameManager.update()
    ├── Player.update()
    ├── Enemy.update() para cada enemigo
    │   └── MovementStrategy.move()  // Strategy en acción
    ├── Bullet.update() para cada proyectil
    ├── checkCollisions()
    └── checkGameState()
    ↓
GamePanel.repaint()  // Redibujar pantalla
    ├── Player.render()
    ├── Enemy.render() para cada enemigo
    └── Bullet.render() para cada proyectil
```

### **3. Gestión de Eventos:**
```
Usuario presiona tecla
    ↓
KeyListener.keyPressed()
    ↓
Según la tecla:
├── ←/→ o A/D → Player.moveLeft()/moveRight()
├── ESPACIO → GameManager.playerShoot()
├── R → GameManager.resetGame()
└── ESC → System.exit()
```

---

## 🎁 Beneficios de los Patrones

### **Sin Patrones (Código Monolítico):**
```java
// MAL EJEMPLO - Sin patrones
public class Game {
    public void createEnemies() {
        if (type.equals("fast")) {
            // 50 líneas de código específico para FastEnemy
        } else if (type.equals("normal")) {
            // 50 líneas de código específico para NormalEnemy
        } else if (type.equals("heavy")) {
            // 50 líneas de código específico para HeavyEnemy
        }
        // Código repetitivo y difícil de mantener
    }
    
    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.getType().equals("boss")) {
                // Lógica de movimiento zigzag mezclada aquí
            } else {
                // Lógica de movimiento horizontal mezclada aquí
            }
        }
    }
}
```

### **Con Patrones (Código Organizado):**
```java
// BUEN EJEMPLO - Con patrones
public class GameManager {
    public void createEnemies() {
        Enemy enemy = enemyFactory.createEnemy("FAST", x, y);  // Factory
        enemies.add(enemy);
    }
    
    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update();  // Strategy pattern maneja el movimiento
        }
    }
}
```

### **Comparación de Mantenibilidad:**

| **Aspecto** | **Sin Patrones** | **Con Patrones** |
|-------------|-------------------|------------------|
| **Agregar nuevo enemigo** | Modificar múltiples métodos | Crear nueva clase + Factory |
| **Cambiar movimiento** | Buscar código disperso | Modificar Strategy específica |
| **Testing** | Difícil aislar comportamientos | Cada patrón se prueba independiente |
| **Legibilidad** | Código mezclado | Responsabilidades claras |
| **Reutilización** | Copiar y pegar | Composición y herencia |

---

## 🎮 Ejemplos de Uso en Tiempo Real

### **Ejemplo 1: Creación de Enemigos al Iniciar Nivel**
```java
// GameManager.createEnemyFormation()
EnemyFactory factory = new EnemyFactory();

for (int row = 0; row < 5; row++) {
    for (int col = 0; col < 10; col++) {
        String enemyType;
        if (row == 0) enemyType = "FAST";      // Primera fila: rápidos
        else if (row <= 2) enemyType = "NORMAL"; // Filas 2-3: normales
        else enemyType = "HEAVY";               // Últimas filas: pesados
        
        Enemy enemy = factory.createEnemy(enemyType, x, y);  // Factory
        enemies.add(enemy);
    }
}
```

**Resultado:** 50 enemigos con comportamientos diferentes creados con una sola línea cada uno.

### **Ejemplo 2: Movimiento de Enemigos Durante el Juego**
```java
// Enemy.update()
public void update() {
    long currentTime = System.currentTimeMillis();
    long deltaTime = currentTime - lastUpdateTime;
    
    if (movementStrategy != null) {
        movementStrategy.move(x, y, deltaTime);  // Strategy pattern
        x = movementStrategy.getNewX();
        y = movementStrategy.getNewY();
    }
    
    lastUpdateTime = currentTime;
}
```

**Resultado:** Cada enemigo se mueve según su estrategia asignada sin código condicional.

### **Ejemplo 3: Acceso Global al Estado del Juego**
```java
// Desde cualquier parte del código
GameManager gameManager = GameManager.getInstance();  // Singleton

// En GameWindow
if (!gameManager.isGameRunning()) {
    drawGameOver(g);
}

// En Player
gameManager.playerShoot();

// En Enemy
if (gameManager.getScore() > 1000) {
    // Comportamiento especial para puntuaciones altas
}
```

**Resultado:** Estado consistente accesible desde cualquier clase.

---

## 🚀 Extensibilidad

### **Agregar Nuevo Tipo de Enemigo:**

1. **Crear la clase:**
```java
public class ShieldEnemy extends Enemy {
    public ShieldEnemy(int x, int y) {
        super(x, y, 50, 35, Color.GRAY, 50);
        setMovementStrategy(new HorizontalMovementStrategy(1));
        this.shield = true;  // Nueva característica
    }
}
```

2. **Actualizar Factory:**
```java
case "SHIELD":
    return new ShieldEnemy(x, y);
```

3. **¡Listo!** El resto del código funciona automáticamente.

### **Agregar Nueva Estrategia de Movimiento:**

1. **Implementar la interfaz:**
```java
public class CircularMovementStrategy implements MovementStrategy {
    private double angle = 0;
    
    public void move(int currentX, int currentY, long deltaTime) {
        angle += 0.1;
        newX = (int)(currentX + Math.cos(angle) * 50);
        newY = (int)(currentY + Math.sin(angle) * 20);
    }
}
```

2. **Asignar a enemigos:**
```java
enemy.setMovementStrategy(new CircularMovementStrategy());
```

3. **¡Movimiento circular implementado!**

### **Agregar Power-ups:**

1. **Usar Factory existente:**
```java
// En EnemyFactory, agregar método
public PowerUp createPowerUp(String type, int x, int y) {
    switch (type) {
        case "SPEED": return new SpeedPowerUp(x, y);
        case "MULTISHOT": return new MultishotPowerUp(x, y);
    }
}
```

2. **Usar Strategy para movimiento:**
```java
powerUp.setMovementStrategy(new StraightDownMovementStrategy(3));
```

---

## 🎯 Conclusiones

### **¿Por qué este proyecto es educativo?**

1. **Demostración práctica:** Los patrones se ven funcionando en un contexto real
2. **Comparación directa:** Es fácil imaginar cómo sería sin patrones
3. **Escalabilidad visible:** Agregar funcionalidades es simple y claro
4. **Interactividad:** El juego funciona y es divertido

### **Lecciones aprendidas:**

1. **Singleton:** Perfecto para gestores únicos y estado global
2. **Factory:** Esencial cuando tienes múltiples tipos de objetos similares
3. **Strategy:** Ideal para comportamientos intercambiables

### **Aplicaciones en proyectos reales:**

- **Singleton:** Conexiones a base de datos, loggers, configuraciones
- **Factory:** Creación de objetos complejos, parsers, servicios
- **Strategy:** Algoritmos de ordenamiento, validadores, procesadores

### **Código resultante:**
- ✅ **Mantenible:** Cada responsabilidad en su lugar
- ✅ **Extensible:** Fácil agregar nuevas funcionalidades  
- ✅ **Testeable:** Cada patrón se puede probar independientemente
- ✅ **Legible:** Intención clara del código
- ✅ **Reutilizable:** Componentes bien definidos

Este proyecto demuestra que los **patrones de diseño no son abstracciones académicas**, sino **herramientas prácticas** que mejoran significativamente la calidad del código en proyectos reales.

---

*¡Disfruta jugando Space Invaders mientras aprendes patrones de diseño!* 🎮✨
