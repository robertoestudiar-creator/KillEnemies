# KillEnemies

Juego de consola en Java en el que eres un héroe que tiene que acabar con los enemigos sin hacer daño a sus amigos.

## Clases
- `Character`: interfaz con el método `isEnemy()`.
- `Friend`: amigo. `isEnemy()` devuelve `false` y se puede curar con `heal()`.
- `Enemy`: enemigo. `isEnemy()` devuelve `true` y muere con `kill()`.
- `Hero`: el héroe. Tiene nombre, ataca con `attack(Enemy)`, defiende con `defend(Friend)` y cuenta los enemigos matados y los amigos defendidos.
- `Main`: crea los personajes, los baraja con `Collections.shuffle` y gestiona los turnos.

## Cómo se juega
- Al empezar se pide el nombre del héroe.
- Se muestra cuántos amigos y enemigos hay y la lista de personajes con su índice.
- En cada turno puedes:
  - **1 - Atacar** (solo si quedan enemigos): el enemigo elegido muere y sale de la lista. Si atacas a un amigo, también sale de la lista.
  - **2 - Defender**: el amigo elegido se cura. Si defiendes a un enemigo, se duplica en la lista.
  - **0 - Salir**.

## Partida guardada
- Al salir, el estado del juego se guarda en `partida.dat` con `ObjectOutputStream`.
- Al empezar, si existe ese fichero, se carga con `ObjectInputStream` en lugar de crear personajes nuevos.

## Cómo ejecutarlo
Hace falta Java 21 o superior.
- Desde Eclipse: importar como proyecto Maven y ejecutar `Main`.
- Con el jar de la última release: `java -jar KillEnemies-5.1.jar`

## Extensión del juego
Las características añadidas como extra están en [EXTENSION.md](EXTENSION.md).
