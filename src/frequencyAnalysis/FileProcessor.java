package frequencyAnalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {
	public String contents;
	
	public FileProcessor(String path) throws IOException {
		this.contents = this.getContents(path);
	}
	
	public String getContents(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded,"UTF8");
	}
}
