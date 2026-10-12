package org.cuatrovientos.psp;

public class Enemy implements Character {

	private static final String KILL_MESSAGE = "Ahhhggg, me mataste, bastardo!";

	@Override
	public boolean isEnemy() {
		return true;
	}

	public void kill() {
		System.out.println(KILL_MESSAGE);
	}

}
