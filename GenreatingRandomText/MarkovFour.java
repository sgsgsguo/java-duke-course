import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
	private String myText;
	private Random myRandom;
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
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
		System.out.println("follows size ="+follows.size());
//		System.out.println(follows);
		return follows;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String key = myText.substring(index, index+4);
		sb.append(key);
		System.out.println("key:"+key);
		System.out.println("sb: "+sb);
		ArrayList<Character> follows = new ArrayList<Character>();
		
		for(int k=1; k < numChars+1-4; k++){
			follows = getFollows(key);
			System.out.println(follows.size());
			index = myRandom.nextInt(follows.size());
			System.out.println("index ="+index);
			char next = follows.get(index);
			
			sb.append(next);
			key = key.substring(1)+next;
			System.out.println("key:"+key);
			
			System.out.println("sb: "+sb);
		}
		
		return sb.toString();
	}
}
