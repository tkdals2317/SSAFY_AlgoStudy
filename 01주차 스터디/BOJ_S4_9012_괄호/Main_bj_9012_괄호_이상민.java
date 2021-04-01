import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Main_bj_9012_괄호 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/Main_9012.txt"));
		String sol = "";
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Stack<Character> stack = new Stack<>();
		for (int tc = 1; tc <= T; tc++) {
			String ps = sc.next();
			for (int i = 0; i < ps.length(); i++) {
				if (ps.charAt(i) == '(') {
					stack.push(ps.charAt(i));
				} else {
					if (stack.isEmpty()) {
						stack.push(ps.charAt(i));
						sol = "NO";
						break;
					}
					else
						stack.pop();
				}
			}

			if (stack.isEmpty())
				sol = "YES";
			else
				sol = "NO";
			
			System.out.println(sol);
			stack.clear();
		}
		sc.close();
	}
}
