package org.cuatrovientos.psp;

import java.util.Random;

public class Hero implements Character {

	private static final String ATTACK_MESSAGE = "¡He atacado a un enemigo!";
	private static final String DEFEND_MESSAGE = "¡He defendido a un amigo!";
	private static final int SPECIAL_CHANCE = 10;
	private static final String SPECIAL_NAME = "Roberto";
	private static final String EASTER_EGG_MESSAGE = "Bonito nombre, buen señor.";
	private static final String REVIVE_MESSAGE = "¡He revivido a un aliado!";
	private static final String COUNTER_MESSAGE = "¡Contraataque!";

	private static Random random = new Random();

	private String name;
	private boolean alwaysSpecial;
	private int killedEnemies;
	private int defendedFriends;

	public Hero(String name) {
		this.name = name;
		if (name.equalsIgnoreCase(SPECIAL_NAME)) {
			alwaysSpecial = true;
			System.out.println(EASTER_EGG_MESSAGE);
		}
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
		if (alwaysSpecial) {
			return true;
		}
		return random.nextInt(100) < SPECIAL_CHANCE;
	}

	public void attackBoss(Boss boss, int damage) {
		System.out.println(ATTACK_MESSAGE);
		boss.hit(damage);
		if (boss.isDead()) {
			boss.kill();
			killedEnemies++;
		}
	}

	public void revive() {
		System.out.println(REVIVE_MESSAGE);
	}

	public void counterattack(Enemy enemy) {
		System.out.println(COUNTER_MESSAGE);
		attack(enemy);
	}

	public int getKilledEnemies() {
		return killedEnemies;
	}

	public int getDefendedFriends() {
		return defendedFriends;
	}

}
