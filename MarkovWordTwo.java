
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
    
    private int indexOf(String[] words, String key1, String key2, int start) {
    	for (int i =start; i < words.length-1; i++) {    		
    		if (words[i].equals(key1) && words[i+1].equals(key2)) {
    			return i;
    		}
    	}    	
		return -1;
    }
    
    public void testIndexOf() {    	
    	String text = "this is just a test yes this is a simple test";
    	String[] words = text.split("\\s+");
    	System.out.println(indexOf(words, "this","is", 0));
    	System.out.println(indexOf(words, "this","is", 3));
    	System.out.println(indexOf(words, "frog","is", 0));
    	System.out.println(indexOf(words, "frog","is", 5));
    	System.out.println(indexOf(words, "simple","test", 2));
    	System.out.println(indexOf(words, "test", "yes",5));
    }
    
    
    private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int index =-1;	
	    //this is just a   4,2
	    for (int pos =0; pos <= myText.length-2; pos++) {
//	    	System.out.println("pos:"+pos);
	    	index = indexOf(myText, key1, key2, pos);
//	    	System.out.println(index);
	    	if (index < 0 || index >= myText.length-2) {
	    		
	    		break;
	    	}
	    	System.out.println(myText[index+2]);
	    	follows.add(myText[index+2]);   
	    	pos = index +1;	    	
	    }	    
	    return follows;
    }
    
    public void getFollowsTest() {    	
    	System.out.println(getFollows("test", "yes"));
    	System.out.println(getFollows("is", "just"));
    	System.out.println(getFollows("this", "is"));
    	System.out.println(getFollows("simple", "test"));
    	System.out.println(getFollows("simple", "tes"));
    }
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		
		// random word to start with
		if (myText.length <2) {
			return "Text length too short!";
		}
		int index = myRandom.nextInt(myText.length-2);  
		
		String key1 = myText[index];
		String key2 = myText[index+1];
		
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		System.out.println(sb);
		
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    System.out.println(follows);
		    System.out.println(96);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 =	next;
		}
		
		return sb.toString().trim();
	}
	
	public String toString() {
		return "MarkovWordTwo";
	}
	
	
	
	

}
