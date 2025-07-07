#!/bin/bash
echo "=== Compilando Space Invaders ==="
mkdir -p bin
javac -d bin -sourcepath src -encoding UTF-8 src/spaceinvaders/game/SpaceInvadersGame.java

if [ $? -eq 0 ]; then
    echo "Compilación exitosa!"
    echo "=== Ejecutando juego ==="
    java -cp bin spaceinvaders.game.SpaceInvadersGame
else
    echo "Error en la compilación!"
    read -p "Presiona Enter para continuar..."
fi
