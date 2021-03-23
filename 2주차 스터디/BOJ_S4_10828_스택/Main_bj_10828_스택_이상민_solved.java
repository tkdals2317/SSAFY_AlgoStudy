import java.util.*;
import java.io.*;

public class Main_bj_10828_스택_이상민_solved {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_10828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<String> stack = new ArrayDeque<String>();
		String command = null;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			command = st.nextToken();
			
			if(command.equals("push")) {
				stack.push(st.nextToken());
			}
			else if(command.equals("pop")) {
				if(!stack.isEmpty())
				System.out.println(stack.pop());
				else
				System.out.println(-1);
			}
			else if(command.equals("top")) {
				if(!stack.isEmpty())
				System.out.println(stack.peek());
				else
				System.out.println(-1);
			}
			else if(command.equals("size")) {
				System.out.println(stack.size());
			}
			else if(command.equals("empty")) {
				if(stack.isEmpty()) {
					System.out.println(1);
				}else {
					System.out.println(0);
				}
			}
		}
		br.close();
	}
}
