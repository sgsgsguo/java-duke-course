import java.util.*;

public class WordGramTester {
	public static void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
			System.out.println(wg.wordAt(3));
		}
		
	}
	
	public static void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 3;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		WordGram second = new WordGram(words, 2, 3);
		System.out.println("checking "+first);
		System.out.println("checking "+second);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (second.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}
	
	public static void testShiftAdd(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		
		WordGram first = new WordGram(words, 4, 4);
//		System.out.println("checking orginal:"+first);
//		System.out.println("checking shifted: "+first.shiftAdd("yes"));
		
	}
	
	
	
	
	
	
	
	
	public static void main (String[] args) {
		MarkovWord mw = new MarkovWord(3);
//		testWordGram();
//		testWordGramEquals();
//		testShiftAdd();
		mw.testIndexOf();
	}
}
