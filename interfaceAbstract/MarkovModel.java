import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel implements IMarkovModel{

	private int myOrder;
	
	public MarkovModel(int n) {
		myRandom = new Random();
		myOrder = n;
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
		
		return sb.toString();
	}
	
	public String toString() {
		return "MarkovModel of order %d.".formatted(myOrder);
	}
}
