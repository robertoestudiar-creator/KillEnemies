# KillEnemies: extensión del juego

## Fases y jefe final
- El juego tiene 8 fases. En todas hay 5 amigos y el número de enemigos sube en cada fase.
- En la fase 8 aparece un jefe (`Boss`, hereda de `Enemy`) con 6 vidas. Cada ataque le quita vidas y solo sale de la lista cuando llegan a 0.
- Al ganar la última fase se muestra "¡Has ganado el juego!" y se borra la partida guardada.

## Habilidad especial
Cada acción tiene un 10% de probabilidad de activar la habilidad especial del héroe:
- Al atacar, golpe doble: daña a 2 enemigos o quita 2 vidas al jefe.
- Al defender, revive a un aliado muerto en la fase o, si no hay ninguno, contraataca a un enemigo.

## Partida guardada con versión
- Además de los personajes y el héroe, se guarda la fase, los aliados muertos y la versión del juego.
- Si la partida guardada es de otra versión, se avisa y se pregunta si borrarla para empezar una nueva.

## Estadísticas
Al terminar se muestran el nombre del héroe, los enemigos matados y los amigos defendidos.
