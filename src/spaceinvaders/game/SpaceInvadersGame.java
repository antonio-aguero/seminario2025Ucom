package spaceinvaders.game;

import javax.swing.SwingUtilities;

/**
 * Clase principal para ejecutar el juego Space Invaders
 * Demuestra la implementación de los patrones:
 * - Singleton: GameManager maneja el estado global del juego
 * - Factory: EnemyFactory crea diferentes tipos de enemigos
 * - Strategy: Diferentes estrategias de movimiento para enemigos
 */
public class SpaceInvadersGame {
    
    public static void main(String[] args) {
        // Mostrar información sobre los patrones implementados
        System.out.println("=== SPACE INVADERS - PATRONES DE DISEÑO ===");
        System.out.println();
        System.out.println("Patrones implementados:");
        System.out.println("1. SINGLETON: GameManager");
        System.out.println("   - Una única instancia gestiona todo el estado del juego");
        System.out.println("   - Thread-safe con sincronización");
        System.out.println();
        System.out.println("2. FACTORY: EnemyFactory");
        System.out.println("   - Crea diferentes tipos de enemigos (Fast, Normal, Heavy, Boss)");
        System.out.println("   - Encapsula la lógica de creación de objetos");
        System.out.println();
        System.out.println("3. STRATEGY: MovementStrategy");
        System.out.println("   - HorizontalMovementStrategy: Movimiento clásico de Space Invaders");
        System.out.println("   - ZigzagMovementStrategy: Movimiento en zigzag para jefes");
        System.out.println("   - StraightDownMovementStrategy: Movimiento directo hacia abajo");
        System.out.println();
        System.out.println("Controles:");
        System.out.println("- Flechas izquierda/derecha o A/D: Mover nave");
        System.out.println("- ESPACIO: Disparar");
        System.out.println("- R: Reiniciar (cuando termine el juego)");
        System.out.println("- ESC: Salir");
        System.out.println();
        System.out.println("¡Iniciando juego...");
        
        // Crear y mostrar la ventana del juego en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar la ventana del juego
            GameWindow gameWindow = new GameWindow();
            gameWindow.setVisible(true);
            
            System.out.println("Juego iniciado exitosamente!");
            System.out.println("Puedes ver los patrones en acción:");
            System.out.println("- Singleton: Solo hay un GameManager controlando todo");
            System.out.println("- Factory: Diferentes enemigos se crean según el tipo");
            System.out.println("- Strategy: Observa los diferentes movimientos de enemigos");
        });
    }
}
