import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel implements IMarkovModel{

	private int myOrder;
	private HashMap<String, ArrayList<Character>> myMap;
	
	public EfficientMarkovModel(int n) {
		myRandom = new Random();
		myOrder = n;
	}
	
	public HashMap<String, ArrayList<Character>> buildMap(){
		
		HashMap<String, ArrayList<Character>> tmap = new HashMap<String, ArrayList<Character>>();
		ArrayList<Character> follows = null;
		for (int i=0; i <(myText.length() - myOrder); i++) {
			follows = new ArrayList<Character>();
			String key =myText.substring(i, i+myOrder);
//			System.out.println("key:"+key);
			if (tmap.containsKey(key)) {
				follows = tmap.get(key);				
			}
			follows.add(myText.charAt(i+myOrder));
			tmap.put(key, follows);
//			System.out.println(follows);
//			System.out.println("end");
		}
		ArrayList<Character> follows2 = new ArrayList<Character>();
//		System.out.println();
	
		String lastKey = myText.substring(myText.length()-myOrder);
		
//		System.out.println(lastKey);
//		
		if (tmap.containsKey(lastKey)) {
			
			follows2 = tmap.get(lastKey);				
		}
//		System.out.println(tmap.get(lastKey));
		follows2.add('\u0000');
		tmap.put(lastKey, follows2);
//		System.out.println(tmap.get(lastKey).size());

		
		this.myMap = tmap;
		printHashMapInfo();
		return tmap;
	}
	
	public void printHashMapInfo() {
		Iterator<Map.Entry<String, ArrayList<Character>>> it = myMap.entrySet().iterator();
		String key;
		ArrayList<Character> follows;
		int maxValueSize = 0;		
		
		/*
		 * get dictionary mavValuesize
		 */
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<Character>> entry = it.next();
			key = entry.getKey();
			follows = entry.getValue();
//			System.out.println("Key: "+key);

//			System.out.println("Value: "+ follows);

//			System.out.println("Value size :" + follows.size());

			if (follows.size() > maxValueSize) {
				maxValueSize = follows.size();				
			}
		}
		System.out.println("max value size: "+maxValueSize);
		
		/*
		 * get keys with maxValuesize
		 */
		it = myMap.entrySet().iterator();
		ArrayList<String> maxKeyList = new ArrayList<String>();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<Character>> entry = it.next();
			if (entry.getValue().size()==maxValueSize) {
				maxKeyList.add(entry.getKey());
			}
		}
//		System.out.println(73);
		
		System.out.println("Key size: "+myMap.size());
//		System.out.println("Max Value size: "+maxValueSize);
//		System.out.println("The keys have the max value size are: "+maxKeyList);
		
		
	}
	
	public ArrayList<Character> getFollows(String key) {
		
		return myMap.get(key);
	}
	
	
	
	
	
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-myOrder);
		String key = myText.substring(index, index+myOrder);
		sb.append(key);
//		System.out.println("key:"+key);
//		System.out.println("sb: "+sb);
		ArrayList<Character> follows = new ArrayList<Character>();
		
		for(int k=1; k < numChars+1-myOrder; k++){
			follows = getFollows(key);
//			System.out.println(follows.size());
			index = myRandom.nextInt(follows.size());
//			System.out.println("index ="+index);
			char next = follows.get(index);
			
			sb.append(next);
			key = key.substring(1)+next;
//			System.out.println("key:"+key);
//			
//			System.out.println("sb: "+sb);
		}
//		System.out.println(sb);
		return sb.toString();
	}
	
	public String toString() {
		return "EfficientMarkovModel of order %d.".formatted(myOrder);
	}
}
