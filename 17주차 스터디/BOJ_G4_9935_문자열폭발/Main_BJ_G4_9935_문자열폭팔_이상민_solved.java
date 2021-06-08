import java.util.*;
import java.io.*;
public class Main_BJ_G4_9935_문자열폭팔_이상민_solved {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9935.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		Stack <Character> stack = new Stack<Character>();
		for(int i = str.length()-1; i >= 0; i--) {
			stack.push(str.charAt(i));
			//스택의 크기가 폭탄의 길이보다 크거나 같을 경우에 현재 마지막에 쌓인 캐릭터가 폭탄의 시작 캐릭터와 같으면 
			if(stack.size()>=bomb.length()&&stack.peek()==bomb.charAt(0)) {
				//폭탄인지 판단할 변수
				boolean isbomb = false;
				//검사 시작
				for (int j = 0; j < bomb.length(); j++) {
					//폭탄문자열과 같은지 검사
					if(bomb.charAt(j)==stack.get(stack.size()-1-j)) {
						//같을 경우 true
						isbomb = true;
					}else {
						//다를 경우 false로 검사 중지
						isbomb = false;
						break;
					}
				}
				//마지막까지 폭탄(true)이라고 판별된다면
				if(isbomb) {
					//스택에서 제거
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}		
			}
		}
		//스택이 비어있지않다면 남은 문자열 pop
		if(!stack.isEmpty()) {
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
		}else { //스택이 비어있다면 
			sb.append("FRULA");
		}
		System.out.println(sb);
		br.close();
	}
}
