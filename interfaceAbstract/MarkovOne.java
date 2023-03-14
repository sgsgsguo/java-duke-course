import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel implements IMarkovModel{

	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	
	
	
	

	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-1);
		char key = myText.charAt(index);
		sb.append(key);
		System.out.println("key:"+key);
		System.out.println("sb: "+sb);
		ArrayList<Character> follows = new ArrayList<Character>();
		
		for(int k=1; k < numChars; k++){
			follows = this.getFollows(String.valueOf(key));
			System.out.println(follows.size());
			index = myRandom.nextInt(follows.size());
			System.out.println("index ="+index);
			key = follows.get(index);
			System.out.println("key:"+key);
			sb.append(key);
			
			System.out.println("sb: "+sb);
		}
		
		return sb.toString();
	}
	
	public String toString() {
		return "MarkovModel of order 1.";
	}
}
