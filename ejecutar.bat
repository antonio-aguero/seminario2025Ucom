@echo off
echo === Compilando Space Invaders ===
if not exist bin mkdir bin
javac -d bin -sourcepath src -encoding UTF-8 src/spaceinvaders/game/SpaceInvadersGame.java

if %errorlevel% equ 0 (
    echo Compilacion exitosa!
    echo === Ejecutando juego ===
    java -cp bin spaceinvaders.game.SpaceInvadersGame
) else (
    echo Error en la compilacion!
    pause
)
