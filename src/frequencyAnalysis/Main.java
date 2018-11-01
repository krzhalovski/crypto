package frequencyAnalysis;

import java.io.IOException;

public class Main {
	
	public static String sorted = "аоиетнрсвдклпмузјгбчшцжњфќхѓџљѕ"; 

	public static void main(String[] args) throws IOException {
		FileProcessor otherFile = new FileProcessor("gracija.txt");
		FileProcessor myFile = new FileProcessor("my-text.txt");
		CharSet cyrilic = new CharSet();
		
		Text input = new Text(myFile.contents,cyrilic);
		Key myKey = new Key("абвгдѓежзѕијклљмнњопрстќуфхцчџш", "фужлцншозкњиевѕчбѓљстхарјмќгдџп");
		
		String myEncodedInput = myKey.encode(input.contents);
		String testingEncoding = myKey.decode(myEncodedInput);
		
		System.out.println("Encoding of my text:\n++++++++++++++++++");
		System.out.println(myEncodedInput+"\n" +testingEncoding+"\n++++++++++++++++++\n");
		
		Text encodedInput = new Text(otherFile.contents,cyrilic);
		System.out.println(otherFile.contents);
		
		//AlgorithmicSolver aSolver = new AlgorithmicSolver(encodedInput.contents,cyrilic);
		//aSolver.initiate();
		
		Solver solver = new Solver(encodedInput.contents,cyrilic);
		solver.initiate();
	}

}
