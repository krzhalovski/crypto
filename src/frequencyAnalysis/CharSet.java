package frequencyAnalysis;

import java.util.HashSet;
import java.util.Set;

public class CharSet {
	public Set<Character> charset = new HashSet<Character>();
	
	public CharSet() {
		generateSet();
	}
	
	private void generateSet() {
		charset.add('а');
		charset.add('б');
		charset.add('в');
		charset.add('г');
		charset.add('д');
		charset.add('ѓ');
		charset.add('е');
		charset.add('ж');
		charset.add('з');
		charset.add('ѕ');
		charset.add('и');
		charset.add('ј');
		charset.add('к');
		charset.add('л');
		charset.add('љ');
		charset.add('м');
		charset.add('н');
		charset.add('њ');
		charset.add('о');
		charset.add('п');
		charset.add('р');
		charset.add('с');
		charset.add('т');
		charset.add('ќ');
		charset.add('у');
		charset.add('ф');
		charset.add('х');
		charset.add('ц');
		charset.add('ч');
		charset.add('џ');
		charset.add('ш');
	}
}
