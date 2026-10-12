package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	private static final int NUM_FRIENDS = 5;
	private static final int NUM_ENEMIES = 5;
	private static final String FRIEND_START = "El carácter ";
	private static final String FRIEND_END = " es un amigo!";
	private static final String ENEMY_START = "El personaje ";
	private static final String ENEMY_END = " es un enemigo! ¡Mátalo!";

	private static ArrayList<Character> characters = new ArrayList<>();

	public static void main(String[] args) {
		createCharacters(NUM_FRIENDS, NUM_ENEMIES);
		shuffleCharacters();
		killEnemies();
	}

	private static void createCharacters(int numFriends, int numEnemies) {
		for (int i = 0; i < numFriends; i++) {
			characters.add(new Friend());
		}
		for (int i = 0; i < numEnemies; i++) {
			characters.add(new Enemy());
		}
	}

	private static void shuffleCharacters() {
		Collections.shuffle(characters);
	}

	private static void killEnemies() {
		for (int i = 0; i < characters.size(); i++) {
			Character character = characters.get(i);
			System.out.println(buildMessage(character, i));
			if (character.isEnemy()) {
				((Enemy) character).kill();
			}
		}
	}

	private static String buildMessage(Character character, int index) {
		String message;
		if (character.isEnemy()) {
			message = ENEMY_START + index + ENEMY_END;
		} else {
			message = FRIEND_START + index + FRIEND_END;
		}
		return message;
	}

}
