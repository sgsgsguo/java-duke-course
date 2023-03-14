
/**
 /* Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
//    private String[] myText;
	public String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
    
    private int indexOf(String[] words, WordGram target, int start) {
    	WordGram match;
    	//"this is a test"  4,2
    	
    	for (int i = start; i <= words.length-myOrder; i++ ) {
    		match = new WordGram(words, i, myOrder);

    		if (match.equals(target)) {    			 	
    			return i;    			
    		}
    	}
    	return -1;
    }
    
    public void testIndexOf() {
		String s = "this is just a test yes this is a simple test";
		String[] words = s.split("\\s+");
		WordGram t1 = new WordGram(words, 0, 3);//this is just
		WordGram t2 = new WordGram(words, 0, 11);//
//		WordGram t3 = new WordGram(words, 0, 12);
		WordGram t4 = new WordGram(words, 5, 3);
//		WordGram t5 = new WordGram(words, 9, 3);
		WordGram t6 = new WordGram(words, 8, 3);//a simple test
        System.out.print(indexOf(words, t1, 0) + "\n");
//        System.out.print(indexOf(words, t2, 0) + "\n");
        System.out.print(indexOf(words, t4, 0) + "\n");//yes this is
        System.out.print(indexOf(words, t6, 0) + "\n"); //should be -1
	}
    
    public ArrayList<String> getFollows(WordGram kGram) {
//    private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int start = 0;
	    int index ;
	    while(start <= myText.length-myOrder) {//"this is just a, 4,2
	    	
	    	index = -1;
	    	index = indexOf(myText, kGram, start);
	    	System.out.println(index);
	    	if (index <0) {	    		
	    		break;
	    	} else if (index == myText.length-myOrder) {	    		
	    		follows.add(null);
	    		break;
	    	} else {	    		
	    		follows.add(myText[index+myOrder]);
	    		kGram = kGram.shiftAdd(myText[index+myOrder]);
	    	}	    	
	    }    
	    return follows;
    }
    
//    public void getFollowsTest() {
//
//      }
    

	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		
		WordGram kGram = new WordGram(myText, index, myOrder);
		System.out.println(kGram);
		sb.append(kGram);
		sb.append(" ");
		System.out.println(sb);
		
		for(int k=0; k < numWords-myOrder; k++){
//			System.out.println(key);
		    ArrayList<String> follows = getFollows(kGram);
//		    System.out.println(String.join(" ", follows));
		    if (follows.size() == 0) {
		        break;
		    } 
			int followsIndex = myRandom.nextInt(follows.size());
			
			String next = follows.get(followsIndex);
			
			if (next==null) {
				
				break;
			}
			
			sb.append(next);
			sb.append(" ");
			kGram = kGram.shiftAdd(next);
			System.out.println(kGram);
			System.out.println(sb);
		}
		
		return sb.toString().trim();
	}
	
	public String toString() {
		return "MarkovWord("+myOrder+")";
	}
}
