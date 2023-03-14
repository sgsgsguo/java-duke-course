
public class WordGram {
    private String[] myWords;
    private int myHash;
    
    public static void main(String[] args) {
    	String st = "this is a test this is a test this is a test of words";
    	String[] words = st.split("\\s+");
    	WordGram key = new WordGram(words, 3,3); 
    	System.out.println(key.toString());
    }
    
    public int hashCode() {
    	myHash = this.toString().hashCode();
    	return this.toString().hashCode();
    }

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int i =0; i<myWords.length; i++) {
        	ret += myWords[i]+" ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != other.myWords.length) { 
        	return false;
        }
        for (int i = 0; i< myWords.length; i++) {
        	if (!myWords[i].equals(other.myWords[i])) {
        		return false;
        	}
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        String[] newWords = new String[myWords.length];
        for (int i =0; i < myWords.length-1; i++) {
        	newWords[i] = myWords[i+1];
        }
        newWords[myWords.length-1] = word;
        out = new WordGram(newWords, 0, myWords.length);
        return out;
    }

}
