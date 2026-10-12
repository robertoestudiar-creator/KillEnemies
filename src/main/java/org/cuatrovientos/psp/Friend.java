package org.cuatrovientos.psp;

public class Friend implements Character {

	private static final String HEAL_MESSAGE = "¡Te he curado!";

	@Override
	public boolean isEnemy() {
		return false;
	}

	public void heal() {
		System.out.println(HEAL_MESSAGE);
	}

}
