
/**
 /* runner for interface and abstract project
 * 
 * @author susan
 * @version 1.0
 */



public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
   
    
    public void runMarkov() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();
		String st = rfs.readFileToString("data/romeo.txt");
		st = st.replace('\n', ' ');
		
		int gtsize = 70;
		int seed = 615;
		
//        MarkovZero mz = new MarkovZero();
//        runModel(mz, st, gtsize);
//    
//        MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, gtsize);
//        
//        MarkovModel mTwo = new MarkovModel(2);
//        runModel(mThree, st, gtsize, seed);
        
        EfficientMarkovModel em = new EfficientMarkovModel(5);
//      runModel(mThree, st, gtsize, seed);
        
//        MarkovFour mFour = new MarkovFour();
//        System.out.println(st.substring(0, 100));
        runModel(em, st, gtsize, seed);     
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);        
//        ((EfficientMarkovModel) markov).buildMap();
        ((EfficientMarkovModel) markov).buildMap();
       
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void compareMethods() throws Exception {
    	ReadFileToString rts = new ReadFileToString(); 
    	String st = rts.readFileToString("data/romeo.txt");
    	st= st.replace('\n', ' ');
    	int seed = 615;    	
    	
//    	long time1 = System.nanoTime();
//    	MarkovModel mm = new MarkovModel(2);
//    	mm.setRandom(seed);
//    	mm.setTraining(st);
//
//    	for(int k=0; k < 3; k++){
//			String gt= mm.getRandomText(1000);
//			printOut(gt);
//		}
//    	long time2 = System.nanoTime();
    	
    	EfficientMarkovModel em = new EfficientMarkovModel(5);
    	em.setRandom(seed);
    	em.setTraining(st);
    	em.buildMap();
    	for(int k=0; k < 1; k++){
			String gt= em.getRandomText(70);
			printOut(gt);
		}
    	long time3 = System.nanoTime();
//    	long diff = time3-time2-time2+time1;
//    	System.out.println("time diff:"+diff);
    	
    }
    
    public void testHashMap() {
    	String text ="yes-this-is-a-thin-pretty-pink-thistle";
    	System.out.println(text.length());
    	EfficientMarkovModel em = new EfficientMarkovModel(2);    	
//    	em.setRandom(42);
    	em.setTraining(text);
    	
    	em.buildMap();
//    	em.getRandomText(50);
    	
    }

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
		MarkovRunnerWithInterface mw = new MarkovRunnerWithInterface();
//		mw.runMarkov();
//		mw.testHashMap();
		mw.compareMethods();
	}
	
}
