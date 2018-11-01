package frequencyAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AlgorithmicSolver {
	public String text;
	public AlgorithmicFrequencies frequencies;
	public Key keyUsed;
	
	public Digrams digrams;
	public Trigrams trigrams;
	
	public HashMap<Character, Character> decoded = new HashMap<Character, Character>();
	public ArrayList<Character> fixed = new ArrayList<Character>();
	
	public AlgorithmicSolver(String text,CharSet cs) throws IOException {
		this.text = text;
		this.frequencies = new AlgorithmicFrequencies(text,cs);
		this.keyUsed = generatePossibleKey();
		
		digrams = new Digrams();
		trigrams = new Trigrams();
	}
	
	public void fix(Character c) {
		decoded.put(c, this.keyUsed.encode.get(c));
		fixed.add(c);
	}
	
	public HashSet<String> getTrigrams() {
		HashSet<String> trigram = new HashSet<String>();
		for(Character c : fixed) {
			trigram.addAll(trigrams.matches(c));
		}
		return trigram;
	}
	
	public Key generatePossibleKey() {
		String sortedAlphabet = "аоиетнрсвдклпмузјгбчшцжњфќхѓџљѕ";
		String possibleMapping = this.frequencies.getSorted();
		
		return new Key(sortedAlphabet,possibleMapping);
	}
	
	private void partlyDecodedTrigrams() {
		System.out.println("Fixed letters: " + fixed);
		System.out.println(this.keyUsed.decode(frequencies.showTrigrams()));
	}
	
	private void unionFind() {
		
	}
	
	private void findA() {
		fix('а');
	}
	
	private void findT() {
		System.out.println(getTrigrams());
		this.partlyDecodedTrigrams();
	}
	
	public void initiate() {
		findA();
		findT();
	}
}
