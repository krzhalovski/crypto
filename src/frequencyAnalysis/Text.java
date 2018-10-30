package frequencyAnalysis;

public class Text {
	public String contents;
	public CharSet charset;
	
	public Text(String input, CharSet charset) {
		this.charset = charset;
		contents = filter(input);
	}
	
	public Text encode(Key key) {
		return new Text(key.encode(this.contents),this.charset);
	}
	
	public Text decode(Key key) {
		return new Text(key.decode(this.contents),this.charset);
	}
	
	public String filter(String input) {
		StringBuilder sbcont = new StringBuilder();
		for(Character ch : input.toCharArray()) {
			char check = Character.toLowerCase(ch);
			if(charset.charset.contains(check)) {
				sbcont.append(check);
			}
		}
		return sbcont.toString();
	}
	
	public String toString() {
		return this.contents;
	}
}
