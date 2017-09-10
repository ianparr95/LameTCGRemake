package cardAbstract;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pokemonCard.PokemonCard;

public class ParsePokemonCardsFile {
	
	private static final File POKEMON = new File("data/pokemon.txt");
	
	/**
	 * Returns list of pokemon cards with name and level
	 * @return A String[][] where the second array has in index 0 the name of
	 * the card, and in index 1 the level of the card.
	 */
	public static String[][] getNameAndLevel() {
		try {
			Scanner fScanner = new Scanner(POKEMON);
			int numCards = getNumberOfPokemonLoadedFromFile();
			String[][] rs = new String[numCards][];
			int c = 0;
			while (fScanner.hasNextLine()) {
				String s = fScanner.nextLine();
				String k = getTag(s);
				if (k.equals("name")) {
					rs[c] = new String[2];
					rs[c][0] = getAssoc(s);
				}
				if (k.equals(("level"))) {
					rs[c][1] = getAssoc(s);
					// level should always be after name.
					c++;
				}
				if (getTag(s).equals("ENDFILE")) {
					fScanner.close();
					return rs;
				}
			}
			fScanner.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get the number of cards in the file
	 * @return the number of cards in the file.
	 */
	private static int getNumberOfPokemonLoadedFromFile() {
		try {
		int c = 0;
		Scanner fScanner = new Scanner(POKEMON);
		while(fScanner.hasNextLine()) {
			String s = fScanner.nextLine();
			if (getTag(s).equals("name")){
				c++;
			} else if(getTag(s).equals("ENDFILE")) {
			//	System.err.println("Finished reading eof");
				fScanner.close();
				return c;
			}
		}
		fScanner.close();
		return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Get the pokemon card entry for the associated card
	 * with the name and level
	 * @param name, name of the card
	 * @param level, level of the card
	 * @return the pokemon card for this card, null if the card doesn't exist.
	 */
	public static PokemonCard getPokemonCard(String name, String level) {
		try {
			Scanner fScanner = new Scanner(POKEMON);
			while (fScanner.hasNextLine()) {
				String s = fScanner.nextLine();
				if (getTag(s).equals("name")) {
					String n = getAssoc(s);
					if (n.equals(name)) {
						s = fScanner.nextLine();
						if (getTag(s).equals("level")) {
							String l = getAssoc(s);
							if (l.equals(level)) {
								// System.err.println("Found the card");
								String cardDes = "";
								while (fScanner.hasNextLine()) {
									s = fScanner.nextLine();
									if (getTag(s).equals("ENDFILE") || getTag(s).equals("name")) {
										break;
									} else {
										cardDes += s;
									}
								}
								fScanner.close();
								return new PokemonCard(name, level, cardDes, 0);
							} else {
								// may be a duplicate name but different level -
								// search on
								continue;
							}
						} else {
							// file parse error, levle should always be after.
							break;
						}
					}
				}
			}
			fScanner.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get the tag associated with a single line read in from the file
	 * eg: [name]Pikachu, this will return name.
	 * @param s, the string to read
	 * @return the tag associated with a single line read in from the file
	 */
	private static String getTag(String s){
		if (s.isEmpty()) return "";
		return s.substring(1, s.indexOf(']'));
	}
	
	/**
	 * Get the tag association with a single line read in from the file
	 * eg: [name]Pikachu, this will return Pikachu.
	 * @param s, the string to read
	 * @return the associated string from a tag with a single line read in from the file
	 */
	private static String getAssoc(String s){
		if (s.isEmpty()) return "";
		return s.substring(s.indexOf(']') + 1);
	}
}
