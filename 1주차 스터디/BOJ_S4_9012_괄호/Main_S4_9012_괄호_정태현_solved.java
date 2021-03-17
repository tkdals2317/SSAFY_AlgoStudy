package algorithm.boj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
 
public class Main_S4_9012_괄호_정태현_solved {
 
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/S4_9012_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=0; tc<T; tc++) {
			boolean check = true; //Ǫ���� ���߿� pop������
			String s = sc.next();
			Stack<Character> stc = new Stack<>();
			for(int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				
				if(c == '(') stc.push(c);
				else if(stc.empty()) { //for�ۿ� ������ empty���� �ϴµ�
					check = false;
					break;
				}
				else stc.pop();//������ ����־ pop�� �� ���� ��
			}
			if(check == false) {
				System.out.println("NO");
				continue; //NO�ϰ� ���� �ݺ�
			}
			
			if (stc.empty()) {
				System.out.println("YES");
			}
			
			else {
				System.out.println("NO");
			}
			//System.out.println(solve(sc.next()));
		}
		
		sc.close();
	}
}
