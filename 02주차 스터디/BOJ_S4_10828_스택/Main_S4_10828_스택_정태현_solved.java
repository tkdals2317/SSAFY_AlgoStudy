package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S4_10828_solved {
	public static int[] stack; //배열 전역선언
	public static int index = 0;
	public static void push(int num) {
		stack[index] = num; //해당 칸에 삽입
		index++; //index가리키는 변수 ++
	}
	public static int pop() {
		if(index == 0) return -1; //비어있으면 -1
		else {
			int temp = stack[index - 1]; //return하기 위함
			stack[--index] = 0; //index는 채운 칸 다음 칸을 가르키고 있기 때문에 -1
			return temp;
		}
	}
	public static int size() {
		return index;
	}
	public static int empty() {
		if(index == 0) return 1;
		else return 0;
	}
	public static int top() {
		if(index == 0) return -1;
		else return stack[index -1];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); //명령어 개수
		stack = new int[T];
		
		for(int tc=0; tc<T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			switch (str) {
			case "push":
				push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				System.out.println(pop());
				break;
			case "top":
				System.out.println(top());
				break;
			case "size":
				System.out.println(size());
				break;
			case "empty":
				System.out.println(empty());
				break;
			}
		}
		
	}
}
