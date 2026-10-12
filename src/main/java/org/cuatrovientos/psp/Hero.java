package org.cuatrovientos.psp;

public class Hero implements Character {

	private static final String ATTACK_MESSAGE = "¡He atacado a un enemigo!";

	@Override
	public boolean isEnemy() {
		return false;
	}

	public void attack(Enemy enemy) {
		System.out.println(ATTACK_MESSAGE);
		enemy.kill();
	}

}
