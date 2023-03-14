
public class Tester {
	public void testGetFollows() {
		MarkovOne m1 = new MarkovOne();
		String st = "this is a test yes this is a test.";
		m1.setTraining(st);
		System.out.println(m1.getFollows("t."));
		
	}
	
	public void testGetFollowsWithFile() throws Exception {
		MarkovOne m1 = new MarkovOne();
		ReadFileToString rfs = new ReadFileToString();
		String st = rfs.readFileToString("data/confucius.txt");
		st = st.replace('\n', ' ');
		System.out.println(st.length());
		System.out.println(st.substring(50, 75));
//		String st = "this is a test yes this is a test.";
		m1.setTraining(st.substring(0, 100));
		m1.getFollows(" ");
		
	}
	
	public static void main(String[] args) throws Exception {
		Tester t = new Tester();
		t.testGetFollowsWithFile();
	}
}
