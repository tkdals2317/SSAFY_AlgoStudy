package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_9012_괄호 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_bj_9012.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < T; i++) {
			int flag = 0;
			String str = sc.next();

			for (int j = 0; j < str.length(); j++) {
//				System.out.print(str.charAt(j));
				if (str.charAt(j) == '(') {
					stack.push('(');
				} else {
					if (stack.isEmpty()&&str.charAt(j)==')') {
						System.out.println("NO");
						flag = 1;
						break;
					} else {
						stack.pop();
					}
				}
			}

			if (stack.isEmpty() && flag == 0) {
				System.out.println("YES");
			} else if (flag == 0) {
				System.out.println("NO");
			}
			stack.clear();

		}

		sc.close();
	}

}
