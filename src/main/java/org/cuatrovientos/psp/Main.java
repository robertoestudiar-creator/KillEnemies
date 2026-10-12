package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static final int NUM_FRIENDS = 5;
	private static final int NUM_ENEMIES = 5;
	private static final String FRIEND_START = "El carácter ";
	private static final String FRIEND_END = " es un amigo!";
	private static final String ENEMY_START = "El personaje ";
	private static final String ENEMY_END = " es un enemigo! ¡Mátalo!";
	private static final String HERO_NAME_PROMPT = "Introduce el nombre de tu héroe: ";
	private static final String COUNT_FRIENDS = "Amigos: ";
	private static final String COUNT_ENEMIES = "Enemigos: ";

	private static ArrayList<Character> characters = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Hero hero;

	public static void main(String[] args) {
		createHero();
		createCharacters(NUM_FRIENDS, NUM_ENEMIES);
		shuffleCharacters();
		killEnemies();
	}

	private static void createHero() {
		System.out.print(HERO_NAME_PROMPT);
		String name = scanner.nextLine();
		hero = new Hero(name);
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

	private static void countCharacters() {
		int friends = 0;
		int enemies = 0;
		for (Character character : characters) {
			if (character.isEnemy()) {
				enemies++;
			} else {
				friends++;
			}
		}
		System.out.println(COUNT_FRIENDS + friends);
		System.out.println(COUNT_ENEMIES + enemies);
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
