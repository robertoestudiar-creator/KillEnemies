package org.cuatrovientos.psp;

public class Boss extends Enemy {

	private static final String LIVES_START = "¡Jajaja! Aún me quedan ";
	private static final String LIVES_END = " vidas";
	private static final String LAST_LIFE_MESSAGE = "¡Jajaja! Aún me queda 1 vida";
	private static final String DEFEATED_MESSAGE = "¡NOOOO! Has derrotado al jefe...";

	private int lives;

	public Boss(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public void hit(int damage) {
		lives = lives - damage;
		if (lives == 1) {
			System.out.println(LAST_LIFE_MESSAGE);
		} else if (lives > 1) {
			System.out.println(LIVES_START + lives + LIVES_END);
		}
	}

	public boolean isDead() {
		return lives <= 0;
	}

	@Override
	public void kill() {
		System.out.println(DEFEATED_MESSAGE);
	}

}
