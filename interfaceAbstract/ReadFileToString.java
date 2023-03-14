import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFileToString {
	public String readFileToString(String filename) throws Exception  {
		String result;
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String next;
		while ((next = br.readLine()) != null) {
			sb.append(next);
			sb.append('\n');
		}
		result = sb.toString();
		
		br.close();
		fr.close();		
		return result;
	}
}
