<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

# Copilot Instructions para Space Invaders

Este proyecto implementa un juego Space Invaders en Java demostrando los patrones de diseño Singleton, Factory y Strategy.

## Contexto del Proyecto

- **Lenguaje:** Java
- **Propósito:** Demostración académica de patrones de diseño
- **Patrones implementados:**
  - Singleton: GameManager
  - Factory: EnemyFactory 
  - Strategy: MovementStrategy

## Pautas de Código

- Mantener la implementación de los patrones de diseño clara y bien documentada
- Usar comentarios en español para explicar la implementación de cada patrón
- Seguir las convenciones de nomenclatura de Java
- Priorizar la claridad y legibilidad sobre la optimización
- Cada clase debe tener responsabilidades bien definidas

## Estructura Recomendada

- `entities/`: Todas las entidades del juego (GameObject, Player, Enemy, Bullet)
- `patterns/`: Implementaciones específicas de los patrones de diseño
- `game/`: Lógica principal del juego y interfaz gráfica

## Consideraciones Especiales

- El GameManager debe ser thread-safe
- La EnemyFactory debe ser extensible para nuevos tipos
- Las estrategias de movimiento deben ser intercambiables en tiempo de ejecución
- Mantener separación clara entre lógica del juego y presentación visual
