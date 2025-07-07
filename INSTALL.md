# Instrucciones de Instalación y Ejecución

## Requisitos

- Java JDK 8 o superior
- Sistema operativo: Windows, Linux o macOS

## Instalación

1. **Verificar instalación de Java:**
   ```bash
   java -version
   javac -version
   ```

2. **Clonar o descargar el proyecto:**
   - Descarga todos los archivos en un directorio
   - Mantén la estructura de carpetas intacta

## Ejecución

### Opción 1: Scripts automatizados

**En Windows:**
```cmd
ejecutar.bat
```

**En Linux/macOS:**
```bash
chmod +x ejecutar.sh
./ejecutar.sh
```

### Opción 2: Compilación manual

1. **Crear directorio de salida:**
   ```bash
   mkdir bin
   ```

2. **Compilar:**
   ```bash
   javac -d bin -sourcepath src -encoding UTF-8 src/spaceinvaders/game/SpaceInvadersGame.java
   ```

3. **Ejecutar:**
   ```bash
   java -cp bin spaceinvaders.game.SpaceInvadersGame
   ```

## Estructura de Archivos

```
Seminario/
├── src/
│   └── spaceinvaders/
│       ├── entities/       # Clases de entidades
│       ├── patterns/       # Implementación de patrones
│       └── game/          # Lógica principal
├── bin/                   # Archivos compilados
├── ejecutar.bat          # Script Windows
├── ejecutar.sh           # Script Linux/macOS
├── README.md             # Documentación principal
└── INSTALL.md            # Este archivo
```

## Solución de Problemas

### Error de encoding
Si aparecen errores relacionados con caracteres especiales:
```bash
javac -d bin -sourcepath src -encoding UTF-8 src/spaceinvaders/game/SpaceInvadersGame.java
```

### Memoria insuficiente
Si el juego se ejecuta lento:
```bash
java -Xmx512m -cp bin spaceinvaders.game.SpaceInvadersGame
```

### Problemas de visualización
- Asegúrate de tener Java con soporte para Swing
- En algunos sistemas Linux puede requerir: `sudo apt-get install openjdk-*-jdk`

## Verificación de Funcionamiento

Al ejecutar correctamente deberías ver:
1. Información sobre los patrones implementados en la consola
2. Una ventana del juego de 800x600 píxeles
3. Una formación de enemigos en la parte superior
4. Una nave verde controlable en la parte inferior

## Controles del Juego

- **Movimiento:** Flechas izquierda/derecha o A/D
- **Disparar:** ESPACIO
- **Reiniciar:** R (cuando termine el juego)
- **Salir:** ESC
