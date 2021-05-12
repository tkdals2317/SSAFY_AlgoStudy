import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_bj_9093_단어뒤집기_정소영_solved {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		
		for (int t = 0; t < T; t++) {
			char[] str = br.readLine().toCharArray();
			
			int len = str.length;
			Stack<Character> st = new Stack<>();
			
			for (int i = 0; i < len; i++) {
				if(str[i]==' ') {
					while(!st.isEmpty()) bw.append(st.pop());
					bw.append(' ');
				} else {
					st.add(str[i]);
				}
			}
			
			while(!st.isEmpty()) bw.append(st.pop());
			bw.append('\n');
			
		}
		
		bw.flush();
		
		bw.close();
		br.close();
	}

}
