


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
    	System.out.println(15);
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() throws Exception { 
    	System.out.println(25);
    	ReadFileToString rfs = new ReadFileToString();        
        String st = rfs.readFileToString("data/confucius.txt");       
        
        
        st = st.replace('\n', ' '); 
        
        MarkovWord mw = new MarkovWord(3); 
        runModel(mw, st, 50, 643); 
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
    	System.out.println(51);
    	MarkovRunner mr = new MarkovRunner();
    	mr.runMarkov();
    }

}
