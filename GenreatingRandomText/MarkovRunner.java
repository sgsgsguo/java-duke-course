
/**
 /* Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

//import edu.duke.*;

public class MarkovRunner {
	
	/*
	 * This method reads in a file the user chooses, 
	 * creates a MarkovZero object, and then generates
	 *  three sets of randomly generated text using the
	 *   file read in to choose the random characters from.
	 */
    public void runMarkovZero() {
//		FileResource fr = new FileResource();
//		String st = fr.asString();
		String st = "this is a test yes a test";
		st = st.replace('\n', ' ');

		MarkovZero markov = new MarkovZero();
		
		markov.setTraining(st);
		markov.setRandom(101);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(50);
			printOut(text);
		}
	}
    
    public void runMarkovOne() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();
    	String st = rfs.readFileToString("data/confucius.txt");
//		st = "this is a test yes a test";
    	
    	st = st.replace('\n', ' ');
    	
		MarkovOne markov = new MarkovOne();
		
		markov.setTraining(st);
		markov.setRandom(42);
		for(int k=0; k < 1; k++){
			String text = markov.getRandomText(50);
			printOut(text);
		}
	}
    
    public void runMarkovFour() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();
    	String st = rfs.readFileToString("data/confucius.txt");
//		st = "this is a test yes a test";
    	
    	st = st.replace('\n', ' ');
    	
		MarkovFour markov = new MarkovFour();
		
		markov.setTraining(st);
		markov.setRandom(25);
		for(int k=0; k < 1; k++){
			String text = markov.getRandomText(70);
			printOut(text);
		}
	}
    
    public void runMarkovModel() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();
    	String st = rfs.readFileToString("data/confucius.txt");
//		st = "this is a test yes a test";
    	
    	st = st.replace('\n', ' ');
    	
		MarkovModel markov = new MarkovModel(6);
		
		markov.setTraining(st);
		markov.setRandom(38);
		for(int k=0; k < 1; k++){
			String text = markov.getRandomText(70);
			printOut(text);
		}
	}
	
    /*
     * print out the random text that was generated with around 60
     *  characters per line. DO NOT CHANGE THIS METHOD.
     */
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public static void main(String[] args) throws Exception {
		MarkovRunner mr = new MarkovRunner();
//		mr.runMarkovZero();
//		mr.runMarkovOne();
//		mr.runMarkovFour();
		mr.runMarkovModel();
		
	}
	
}


