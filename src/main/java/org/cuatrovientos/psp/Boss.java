package org.cuatrovientos.psp;

public class Boss extends Enemy {

	private int lives;

	public Boss(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

}
