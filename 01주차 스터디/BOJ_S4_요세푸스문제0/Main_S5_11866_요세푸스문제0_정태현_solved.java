package algorithm.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_S5_11866_요세푸스문제0_정태현_solved {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //���� ����
		int turn = sc.nextInt(); //������ ����
		
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> qtemp = new LinkedList<>(); //������ ���ڸ� ������� �ִ� ť
		
		for(int i=1; i<=N; i++) {
			q.add(i); //�� �迭�� 1~N����
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=turn; j++) { //turn°�� ����
				if(j%turn != 0) q.add(q.poll()); //
				else qtemp.add(q.poll());
				//�տ��� poll�ؼ� �ǵڷ�
			}
		}
		
		System.out.print("<");
		for(int i=0; i<N; i++) {
			System.out.print(qtemp.poll());
			if(i!=N-1) System.out.print(", ");
			
		}
		System.out.print(">");
		
		sc.close();
	}
}
