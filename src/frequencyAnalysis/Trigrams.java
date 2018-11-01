package frequencyAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Trigrams {
	public HashSet<String> trigrams = new HashSet<String>();
	
	public Trigrams() throws IOException {
		generateTrigrams();
	}
	
	private void generateTrigrams() throws IOException {
		FileProcessor fp = new FileProcessor("trigrams.txt");
		trigrams = (HashSet<String>) Arrays.stream(fp.contents.split(" ")).collect(Collectors.toSet());
	}
	
	public ArrayList<String> matches(Character c) {
		return (ArrayList<String>) trigrams.stream().filter(s -> s.indexOf(c) >= 0).collect(Collectors.toList());
	}
}
