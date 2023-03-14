
/**
 /* Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
//    private String[] myText;
	public String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> myHashMap;
    
    public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    
    public HashMap<Integer, ArrayList<String>> buildMap(){
    	HashMap<Integer, ArrayList<String>> keyMap = new HashMap<Integer, ArrayList<String>>();
    	ArrayList<String> follows = new ArrayList<String>();
    	//this is a test
    	for (int i=0; i < myText.length-myOrder; i++) {
    		follows = new ArrayList<String>();
    		WordGram key = new WordGram(myText, i, myOrder);
    		int keyHash = key.hashCode();
    		if (keyMap.containsKey(keyHash)) {
    			follows = keyMap.get(keyHash);
    		}
    		follows.add(myText[i+myOrder]);
    		keyMap.put(keyHash, follows);
    	}
    	int i= myText.length-myOrder; 
    	follows = new ArrayList<String>();
    	WordGram key = new WordGram(myText, i, myOrder); 
    	int keyHash = key.hashCode();
    	if (!keyMap.containsKey(keyHash)) {
    		keyMap.put(keyHash, follows);
		}
    	myHashMap = keyMap;
		return keyMap;
    }
    
    public void printHashMapInfo() {
//    	String st = "this is a test this is a test this is a test of words";
//    	setTraining(st);
    	buildMap();
    	int keyHash;
    	ArrayList<String> follows;
    	Iterator<Map.Entry<Integer, ArrayList<String>>> it = myHashMap.entrySet().iterator();
    	int maxValueSize = 0; 
    	while (it.hasNext()) {
    		Map.Entry<Integer, ArrayList<String>> entry = it.next();
//    		System.out.println("keyHash: "+entry.getKey());
//    		System.out.println("follows: "+ entry.getValue());    
    		if (entry.getValue().size()>maxValueSize) {
    			maxValueSize = entry.getValue().size();
    		}
    		
    	}
    	
    	System.out.println("Key size: "+myHashMap.size());
    	System.out.println("Max value size: "+maxValueSize);
    	
    	ArrayList<Integer> maxValueKeyList = new ArrayList<Integer>();
    	it = myHashMap.entrySet().iterator();
    	while (it.hasNext()) {
    		Map.Entry<Integer, ArrayList<String>> entry = it.next();
    		if (entry.getValue().size()==maxValueSize) {
    			maxValueKeyList.add(entry.getKey());
    		}
    	}
		System.out.println("Keys with maxValueSize: "+maxValueKeyList);
    }
    
    
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
    
    public ArrayList<String> getFollows(WordGram kGram) {
    	return myHashMap.get(kGram.hashCode());
    } 	
  
	public String getRandomText(int numWords){
//		System.out.println("Call getRT");
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		
		WordGram kGram = new WordGram(myText, index, myOrder);
//		System.out.println(kGram);
		sb.append(kGram);
		sb.append(" ");
//		System.out.println(sb);
		
		for(int k=0; k < numWords-myOrder; k++){
			
//			System.out.println("k="+k);

		    ArrayList<String> follows = getFollows(kGram);
		    
//		    System.out.println(String.join(" ", follows));
		    if (follows.size() == 0) {
		    	
		        break;
		    } 
			int followsIndex = myRandom.nextInt(follows.size());
//			System.out.println(followsIndex);
			String next = follows.get(followsIndex);
//			System.out.println(next);
			if (next==null) {
				
				break;
			}
			
			sb.append(next);
			sb.append(" ");
			kGram = kGram.shiftAdd(next);
//			System.out.println(kGram);
//			System.out.println(sb);
			
		}
		
		return sb.toString().trim();
	}
	
	public String toString() {
		return "EfficientMarkovWord("+myOrder+")";
	}
}
