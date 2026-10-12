package org.cuatrovientos.psp;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	private static ArrayList<Character> characters = new ArrayList<>();

	public static void main(String[] args) {

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

}
