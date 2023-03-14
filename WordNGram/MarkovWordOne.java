
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
    
    private int indexOf(String[] words, String target, int start) {
    	for (int i =start; i < words.length; i++) {    		
    		if (words[i].equals(target)) {
    			return i;
    		}
    	}    	
		return -1;
    }
    
    public void testIndexOf() {    	
    	String text = "this is just a test yes this is a simple test";
    	String[] words = text.split("\\s+");
    	System.out.println(indexOf(words, "this", 0));
    	System.out.println(indexOf(words, "this", 3));
    	System.out.println(indexOf(words, "frog", 0));
    	System.out.println(indexOf(words, "frog", 5));
    	System.out.println(indexOf(words, "simple", 2));
    	System.out.println(indexOf(words, "test", 5));
    }
    
    
    private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int index =-1;	
	    for (int pos =0; pos < myText.length-1; pos++) {	    
	    	index = indexOf(myText, key, pos);
	    	if (index < 0 || index >= myText.length-1) {
	    		break;
	    	}
	    	follows.add(myText[index+1]);   
	    	pos = index +1;	    	
	    }	    
	    return follows;
    }
    
    public void getFollowsTest() {    	
    	System.out.println(getFollows("test"));
    	System.out.println(getFollows("is"));
    }
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		
		// random word to start with
		int index = myRandom.nextInt(myText.length-1);  
		
		String key = myText[index];
		System.out.println(key);
		sb.append(key);
		sb.append(" ");
		System.out.println(sb);
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    System.out.println(follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}		
		return sb.toString().trim();
	}
	
	public String toString() {
		return "MarkovWordOne";
	}	
}
