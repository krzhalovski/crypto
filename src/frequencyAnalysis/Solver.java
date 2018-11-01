package frequencyAnalysis;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {
	public HashMap<Character, Character> found;
	public HashSet<Integer> instructionSet;
	
	public Key keyUsed;
	public String text;
	public Frequencies frequencies;
	
	public Solver(String text,CharSet cs) throws IOException {
		this.found = new HashMap<Character, Character>();
		this.instructionSet = (HashSet<Integer>) IntStream.range(0, 5).boxed().collect(Collectors.toSet());
		
		this.text = text;
		this.frequencies = new Frequencies(this.text,cs);
		this.keyUsed = this.generatePossibleKey();
	}
	
	public void showCharactersFrequency() {
		this.frequencies.showCharacters();
	}
	
	public void showDigramsFrequency() {
		this.frequencies.showDigrams();
	}
	
	public void showTrigramsFrequency() throws IOException {
		this.frequencies.showTrigrams();
	}
	
	public Key generatePossibleKey() {
		String sortedAlphabet = "аоиетнрсвдклпмузјгбчшцжњфќхѓџљѕ";
		String possibleMapping = this.frequencies.getSorted();
		
		return new Key(sortedAlphabet,possibleMapping);
	}
	
	public void fixCharacter(Character a) {
		this.found.put(a, this.keyUsed.encode.get(a));
	}
	
	public void swapCharacters(Character a, Character b) {
		this.keyUsed.swap(a, b);
	}
	
	public void showFoundCharacters() {
		if(this.found.size()!=0) {
			System.out.println(this.found);
		} else {
			System.out.println("No characters found");
		}
	}
	
	public void initiate() throws IOException {
		System.out.println(":::Solver started:::");
		System.out.println("Possible mapping of Characters:\n" + this.keyUsed);
		
		Character instruction = '1';
		this.frequencies.showCharacters();
		
		Scanner scanner = new Scanner(System.in);
		
		while(instruction != '0') {
			System.out.println("Next instruction");
			instruction = scanner.next().charAt(0);
			
			switch (instruction) {
			case '1':
				System.out.println("Choose characters to swap");
				char toSwapA,toSwapB;
				toSwapA = scanner.next().charAt(0);
				toSwapB = scanner.next().charAt(0);
				this.swapCharacters(toSwapA, toSwapB);
				break;
			case '2':
				System.out.println("Chose character to fix");
				char toFix = scanner.next().charAt(0);
				this.fixCharacter(toFix);
				break;
			case '3':
				System.out.println("Encoded text");
				System.out.println(this.text);
				break;
			case '4':
				System.out.println("Text decoded so far");
				System.out.println(this.keyUsed.decode(this.text));
				break;
			case '5':
				System.out.println("Frequency of trigrams");
				this.showTrigramsFrequency();
				break;
			case '6':
				System.out.println("Frequency of digrams");
				this.showDigramsFrequency();
				break;
			case '7':
				System.out.println("Key used\n" + this.keyUsed);
				break;
			case '8':
				System.out.println("Found characters");
				this.showFoundCharacters();
				break;
			}
		}
		
		scanner.close();
	}
}
