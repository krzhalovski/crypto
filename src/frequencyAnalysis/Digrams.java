package frequencyAnalysis;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Digrams {
	public HashSet<String> digrams;
	
	public Digrams() throws IOException {
		generateDigrams();
	}
	
	private void generateDigrams() throws IOException {
		FileProcessor fp = new FileProcessor("digrams.txt");
		digrams = (HashSet<String>) Arrays.stream(fp.contents.split(" ")).collect(Collectors.toSet());
	}
}
