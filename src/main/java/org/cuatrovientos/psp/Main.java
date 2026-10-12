package org.cuatrovientos.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static final int NUM_FRIENDS = 5;
	private static final String HERO_NAME_PROMPT = "Introduce el nombre de tu héroe: ";
	private static final String COUNT_FRIENDS = "Amigos: ";
	private static final String COUNT_ENEMIES = "Enemigos: ";
	private static final String SHOW_FRIEND = " - Amigo";
	private static final String SHOW_ENEMY = " - Enemigo";
	private static final int EXIT = 0;
	private static final int ATTACK = 1;
	private static final int DEFEND = 2;
	private static final String OPTION_ATTACK = "1 - Atacar";
	private static final String OPTION_DEFEND = "2 - Defender";
	private static final String OPTION_EXIT = "0 - Salir";
	private static final String OPTION_PROMPT = "Elige una opción: ";
	private static final String INDEX_PROMPT = "Elige el índice del personaje: ";
	private static final String SAVE_FILE = "partida.dat";
	private static final String SAVE_ERROR = "No se ha podido guardar la partida";
	private static final String LOAD_ERROR = "No se ha podido cargar la partida";
	private static final String TITLE_GAME = "===== KILL ENEMIES =====";
	private static final String TITLE_COUNT = "----- RECUENTO -----";
	private static final String TITLE_CHARACTERS = "----- PERSONAJES -----";
	private static final String TITLE_OPTIONS = "----- ¿QUÉ QUIERES HACER? -----";
	private static final int MAX_PHASE = 8;
	private static final int EXTRA_ENEMIES = 2;
	private static final int BOSS_LIVES = 6;
	private static final String TITLE_PHASE_START = "===== FASE ";
	private static final String TITLE_PHASE_SEPARATOR = " / ";
	private static final String TITLE_PHASE_END = " =====";
	private static final String SHOW_BOSS_START = " - Jefe (vidas: ";
	private static final String SHOW_BOSS_END = ")";
	private static final String PHASE_CLEARED = "¡Fase superada!";

	private static ArrayList<Character> characters = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Hero hero;
	private static int phase = 1;
	private static int deadFriends = 0;

	public static void main(String[] args) {
		System.out.println(TITLE_GAME);
		if (new File(SAVE_FILE).exists()) {
			loadGame();
		} else {
			createHero();
			createPhase();
		}
		showPhase();
		play();
		saveGame();
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

	private static void createPhase() {
		characters.clear();
		deadFriends = 0;
		if (phase == MAX_PHASE) {
			createCharacters(NUM_FRIENDS, 0);
			characters.add(new Boss(BOSS_LIVES));
		} else {
			createCharacters(NUM_FRIENDS, phase + EXTRA_ENEMIES);
		}
		shuffleCharacters();
	}

	private static void showPhase() {
		System.out.println();
		System.out.println(TITLE_PHASE_START + phase + TITLE_PHASE_SEPARATOR + MAX_PHASE + TITLE_PHASE_END);
		countCharacters();
	}

	private static void countCharacters() {
		System.out.println();
		System.out.println(TITLE_COUNT);
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

	private static void showCharacters() {
		System.out.println();
		System.out.println(TITLE_CHARACTERS);
		for (int i = 0; i < characters.size(); i++) {
			Character character = characters.get(i);
			if (character instanceof Boss) {
				System.out.println(i + SHOW_BOSS_START + ((Boss) character).getLives() + SHOW_BOSS_END);
			} else if (character.isEnemy()) {
				System.out.println(i + SHOW_ENEMY);
			} else {
				System.out.println(i + SHOW_FRIEND);
			}
		}
	}

	private static boolean hasEnemies() {
		for (Character character : characters) {
			if (character.isEnemy()) {
				return true;
			}
		}
		return false;
	}

	private static int askOption() {
		System.out.println();
		System.out.println(TITLE_OPTIONS);
		if (hasEnemies()) {
			System.out.println(OPTION_ATTACK);
		}
		System.out.println(OPTION_DEFEND);
		System.out.println(OPTION_EXIT);
		System.out.print(OPTION_PROMPT);
		return Integer.parseInt(scanner.nextLine());
	}

	private static int askIndex() {
		int index = -1;
		while (index < 0 || index >= characters.size()) {
			System.out.print(INDEX_PROMPT);
			index = Integer.parseInt(scanner.nextLine());
		}
		return index;
	}

	private static void attackCharacter(int index) {
		Character character = characters.get(index);
		if (character instanceof Boss) {
			Boss boss = (Boss) character;
			hero.attackBoss(boss, 1);
			if (boss.isDead()) {
				characters.remove(index);
			}
		} else {
			if (character.isEnemy()) {
				hero.attack((Enemy) character);
			} else {
				deadFriends++;
			}
			characters.remove(index);
		}
	}

	private static void defendCharacter(int index) {
		Character character = characters.get(index);
		if (character.isEnemy()) {
			characters.add(new Enemy());
		} else {
			hero.defend((Friend) character);
		}
	}

	private static void nextPhase() {
		System.out.println(PHASE_CLEARED);
		phase++;
		if (phase <= MAX_PHASE) {
			createPhase();
			showPhase();
		}
	}

	private static void play() {
		int option = -1;
		while (option != EXIT && phase <= MAX_PHASE) {
			showCharacters();
			option = askOption();
			if (option == ATTACK && hasEnemies()) {
				attackCharacter(askIndex());
			} else if (option == DEFEND && !characters.isEmpty()) {
				defendCharacter(askIndex());
			}
			if (!hasEnemies()) {
				nextPhase();
			}
		}
	}

	private static void saveGame() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
			out.writeObject(characters);
			out.writeObject(hero);
		} catch (IOException e) {
			System.out.println(SAVE_ERROR);
		}
	}

	private static void loadGame() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
			characters = (ArrayList<Character>) in.readObject();
			hero = (Hero) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(LOAD_ERROR);
		}
	}

}
