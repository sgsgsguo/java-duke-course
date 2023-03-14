
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
		myRandom = new Random(seed);
	}
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public ArrayList<Character> getFollows(String key) {
		ArrayList<Character> follows = new ArrayList<Character>();
//		System.out.println(myText.length());
//		System.out.println(key.length());
		for (int i=0; i<myText.length()-key.length(); i++) {
			if (myText.substring(i, i+key.length()).equals(key)) {
				follows.add(myText.charAt(i+key.length()));
			}
		}
//		System.out.println("follows size ="+follows.size());
//		System.out.println(follows);
		return follows;
	}
 
    abstract public String getRandomText(int numChars);

}
