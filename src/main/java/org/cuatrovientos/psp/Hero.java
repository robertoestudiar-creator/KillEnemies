package org.cuatrovientos.psp;

import java.util.Random;

public class Hero implements Character {

	private static final String ATTACK_MESSAGE = "¡He atacado a un enemigo!";
	private static final String DEFEND_MESSAGE = "¡He defendido a un amigo!";
	private static final int SPECIAL_CHANCE = 10;

	private static Random random = new Random();

	private String name;
	private int killedEnemies;
	private int defendedFriends;

	public Hero(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

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
		friend.heal();
		defendedFriends++;
	}

	public boolean isSpecial() {
		return random.nextInt(100) < SPECIAL_CHANCE;
	}

	public int getKilledEnemies() {
		return killedEnemies;
	}

	public int getDefendedFriends() {
		return defendedFriends;
	}

}
