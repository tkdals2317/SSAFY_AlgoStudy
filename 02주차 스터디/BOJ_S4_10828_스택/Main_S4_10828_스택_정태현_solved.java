package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S4_10828_solved {
	public static int[] stack; //�迭 ��������
	public static int index = 0;
	public static void push(int num) {
		stack[index] = num; //�ش� ĭ�� ����
		index++; //index����Ű�� ���� ++
	}
	public static int pop() {
		if(index == 0) return -1; //��������� -1
		else {
			int temp = stack[index - 1]; //return�ϱ� ����
			stack[--index] = 0; //index�� ä�� ĭ ���� ĭ�� ����Ű�� �ֱ� ������ -1
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
		
		int T = Integer.parseInt(br.readLine()); //��ɾ� ����
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
