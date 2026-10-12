package org.cuatrovientos.psp;

public class Hero implements Character {

	private static final String ATTACK_MESSAGE = "¡He atacado a un enemigo!";
	private static final String DEFEND_MESSAGE = "¡He defendido a un amigo!";

	private int killedEnemies;
	private int defendedFriends;

	@Override
	public boolean isEnemy() {
		return false;
	}

	public void attack(Enemy enemy) {
		System.out.println(ATTACK_MESSAGE);
		enemy.kill();
		killedEnemies++;
	}

	public void defend(Friend friend) {
		System.out.println(DEFEND_MESSAGE);
		defendedFriends++;
	}

	public int getKilledEnemies() {
		return killedEnemies;
	}

	public int getDefendedFriends() {
		return defendedFriends;
	}

}
