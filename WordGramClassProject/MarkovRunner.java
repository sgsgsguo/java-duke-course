public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
    	
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() throws Exception { 
    
    	ReadFileToString rfs = new ReadFileToString();        
        String st = rfs.readFileToString("data/confucius.txt");       
        
//        String st = "this is a test this is a test this is a test of words";
        st = st.replace('\n', ' '); 
        
        MarkovWord mw = new MarkovWord(5); 
        
        runModel(mw, st, 60, 844); 
    } 
    
    public void testHashMap() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();        
        String st = rfs.readFileToString("data/confucius.txt"); 
//    	String st = "this is a test yes this is really a test yes a test this is wow";
    	String[] words = st.split("\\s+");
    	EfficientMarkovWord em = new EfficientMarkovWord(3);
    	
    	em.setTraining(st);
    	em.buildMap();
    	em.printHashMapInfo();
    	System.out.println("running with " + em); 
        
    	
    	em.setRandom(371);
    	em.printHashMapInfo();
//    	for(int i = 0; i<st.length()-2; i++) {
//    		WordGram x = new WordGram(words, i, 2);
//            System.out.println(x.toString().hashCode());
//    	}
        

    }
    
    public void compareMethods() throws Exception {
    	ReadFileToString rfs = new ReadFileToString();        
    	String st = rfs.readFileToString("data/hawthorne.txt"); 
//	  	String st = "this is a test yes this is really a test yes a test this is wow";
//	  	String[] words = st.split("\\s+");
    	
    	
    	
    	long t1= System.nanoTime();
    	MarkovWord mw = new MarkovWord(2);
    	runModel(mw, st, 100, 42);
    	long t2= System.nanoTime();
    	
    	EfficientMarkovWord em = new EfficientMarkovWord(3);
    	
    	
	  	
	  	em.setRandom(371);
	  	em.setTraining(st);
	  	em.buildMap();
//	  	em.printHashMapInfo();
	  	System.out.println("running with " + em); 
	      
	      for(int k=0; k < 3; k++){ 
	          String gt = em.getRandomText(100); 
	          printOut(gt); 
	      } 

	      long t3= System.nanoTime();
	      System.out.println("diff1:"+(t2-t1)+ " diff2: "+(t3-t2));
    	
    	
    	
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
    
    public static void main (String[] args) throws Exception {
    	
    	MarkovRunner mr = new MarkovRunner();
//    	mr.runMarkov();
    	mr.testHashMap();
//    	mr.compareMethods();
    	
//    	MarkovWord mw = new MarkovWord(3);
//    	mw.getFollowsTest();
    }

}
