import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class Main_G4_9935_문자열폭팔_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		String str = br.readLine();			//	문자열
		String bomb = br.readLine();		//	폭탄

		Stack<Character> stack = new Stack<>();
		for (int i = str.length()-1 ; i >= 0; i--) {	//	문자열 뒤부터 길이 만큼 반복
			boolean is_bomb = false;	// 폭발 여부
			stack.push(str.charAt(i));	// 뒤부터 push
			if (str.charAt(i) == bomb.charAt(0) && stack.size() >= bomb.length()) {
				is_bomb = true;
				for (int j = 0; j < bomb.length(); j++) {
					if (bomb.charAt(j) != stack.get(stack.size() - 1 - j)) {
						is_bomb = false;
						break;
					}
				}
				if (is_bomb) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		if (stack.isEmpty())
			System.out.println("FRULA");
		else {
			for (int i = stack.size() - 1; i >= 0; i--) {
				sb.append(stack.pop());
			}
		}
		System.out.println(sb);
	}
}
