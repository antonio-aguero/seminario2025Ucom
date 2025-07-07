# Space Invaders - Implementación de Patrones de Diseño

Un juego de Space Invaders implementado en Java que demuestra la aplicación de tres patrones de diseño fundamentales.

## Patrones de Diseño Implementados

### 1. Patrón Singleton
**Clase:** `GameManager`
- **Propósito:** Asegurar que solo exista una instancia del gestor del juego
- **Implementación:** Constructor privado con método getInstance() thread-safe
- **Uso:** Controla el estado global del juego, enemigos, proyectiles y puntuación

### 2. Patrón Factory
**Clase:** `EnemyFactory`
- **Propósito:** Crear diferentes tipos de enemigos sin especificar sus clases concretas
- **Implementación:** Método factory que retorna diferentes subclases de Enemy
- **Tipos creados:**
  - `FastEnemy`: Enemigo rápido con movimiento horizontal
  - `NormalEnemy`: Enemigo estándar 
  - `HeavyEnemy`: Enemigo resistente que requiere múltiples disparos
  - `BossEnemy`: Enemigo jefe con movimiento especial

### 3. Patrón Strategy
**Interfaz:** `MovementStrategy`
- **Propósito:** Definir diferentes algoritmos de movimiento para enemigos
- **Implementaciones:**
  - `HorizontalMovementStrategy`: Movimiento clásico de Space Invaders (izquierda-derecha-abajo)
  - `ZigzagMovementStrategy`: Movimiento en zigzag para enemigos especiales
  - `StraightDownMovementStrategy`: Movimiento directo hacia abajo

## Estructura del Proyecto

```
src/
├── spaceinvaders/
│   ├── entities/           # Entidades del juego
│   │   ├── GameObject.java      # Clase base
│   │   ├── Player.java          # Jugador
│   │   ├── Enemy.java           # Clase base enemigos
│   │   ├── FastEnemy.java       # Enemigo rápido
│   │   ├── NormalEnemy.java     # Enemigo normal
│   │   ├── HeavyEnemy.java      # Enemigo pesado
│   │   ├── BossEnemy.java       # Enemigo jefe
│   │   ├── Bullet.java          # Proyectil básico
│   │   ├── LaserBullet.java     # Proyectil láser
│   │   └── PlasmaBullet.java    # Proyectil plasma
│   ├── patterns/           # Implementación de patrones
│   │   ├── GameManager.java           # Singleton
│   │   ├── EnemyFactory.java          # Factory
│   │   ├── MovementStrategy.java      # Strategy interface
│   │   ├── HorizontalMovementStrategy.java
│   │   ├── ZigzagMovementStrategy.java
│   │   └── StraightDownMovementStrategy.java
│   └── game/               # Lógica principal del juego
│       ├── SpaceInvadersGame.java     # Clase main
│       └── GameWindow.java            # Interfaz gráfica
```

## Controles del Juego

- **Movimiento:** Flechas izquierda/derecha o teclas A/D
- **Disparar:** ESPACIO o flecha arriba
- **Reiniciar:** R (cuando el juego termine)
- **Salir:** ESC

## Características del Juego

- Múltiples tipos de enemigos con diferentes comportamientos
- Sistema de puntuación
- Diferentes tipos de proyectiles
- Efectos visuales simples
- Detección de colisiones
- Sistema de vidas del jugador

## Compilación y Ejecución

### Compilar:
```bash
javac -d bin src/spaceinvaders/game/SpaceInvadersGame.java src/spaceinvaders/**/*.java
```

### Ejecutar:
```bash
java -cp bin spaceinvaders.game.SpaceInvadersGame
```

## Análisis de los Patrones

### Ventajas del Singleton (GameManager)
- Control centralizado del estado del juego
- Evita problemas de sincronización entre componentes
- Fácil acceso desde cualquier parte del código

### Ventajas del Factory (EnemyFactory)
- Facilita la adición de nuevos tipos de enemigos
- Encapsula la lógica de creación
- Reduce el acoplamiento entre clases

### Ventajas del Strategy (MovementStrategy)
- Permite cambiar comportamientos en tiempo de ejecución
- Facilita la adición de nuevos tipos de movimiento
- Mantiene el código limpio y modular

## Extensibilidad

El diseño permite fácilmente:
- Agregar nuevos tipos de enemigos implementando la clase `Enemy`
- Crear nuevos patrones de movimiento implementando `MovementStrategy`
- Añadir nuevos tipos de proyectiles extendiendo `Bullet`
- Implementar power-ups y mejoras

Este proyecto demuestra cómo los patrones de diseño mejoran la organización, mantenibilidad y extensibilidad del código.
