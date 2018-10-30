package frequencyAnalysis;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Frequencies {
	Map<Character, Integer> count = new HashMap<Character, Integer>();
	Map<String, Integer> trigrams = new HashMap<String, Integer>();
	Map<String, Integer> digrams = new HashMap<String, Integer>();
	String text;
	CharSet cs;
	
	public Frequencies(String tex,CharSet cs) {
		this.text = tex;
		getCount();
		count=sortByVal(count,false);
		this.cs = cs;
		fillCount();
		this.generateTrigrams();
		trigrams = this.sortByValString(trigrams, false);
		this.generateDigrams();
		digrams = this.sortByValString(digrams, false);
	}
	
	private void getCount() {
		for(int i = 0;i<text.length();i++) {
			char ch = text.charAt(i);
			count.put(ch, count.getOrDefault(ch, 0) + 1);
		}
	}
	
	private void fillCount() {
		for(Character c : cs.charset) {
			if(!count.containsKey(c)) {
				count.put(c, 0);
			}
		}
	}
	
	public void showTrigrams() {
		//System.out.println(trigrams);
		StringBuilder sb = new StringBuilder();
		for(Entry<String,Integer> e: trigrams.entrySet()) {
			//String toPrint = new String()
			String toAppend = new String(e.getKey() + " " + e.getValue() + ", ");
			//System.out.println(toAppend);
			sb.append(toAppend);
			//System.out.flush();
		}
		System.out.println(sb.toString().substring(0,402));
		//trigrams.entrySet().stream().forEach(t -> System.out.print(t.getKey() + " " + t.getValue() + " "));
	}
	
	public void showDigrams() {
		digrams.entrySet().stream().forEach(t -> System.out.print(t.getKey() + " " + t.getValue() + " "));
	}
	
	public void showCharacters() {
		count.entrySet().stream().forEach(t -> System.out.print(t.getKey() + " " + t.getValue() + " "));
	}
	
	public String getSorted() {
		return count.entrySet().stream().map(x -> x.getKey().toString()).collect(Collectors.joining(""));
	}
	
	public void generateTrigrams() {
		for(int i = 0;i<this.text.length()-3;i++) {
			String trigram = this.text.substring(i, i+3);
			this.trigrams.put(trigram, trigrams.getOrDefault(trigram, 0) + 1);
		}
	}
	
	public void generateDigrams() {
		for(int i = 0;i<this.text.length()-2;i++) {
			String trigram = this.text.substring(i, i+2);
			this.digrams.put(trigram, digrams.getOrDefault(trigram, 0) + 1);
		}
	}
	
	public Map<Character,Integer> sortByVal(Map<Character, Integer> unsortMap, boolean order)
    {
        List<Entry<Character, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
	
	public Map<String,Integer> sortByValString(Map<String, Integer> unsortMap, boolean order)
    {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
