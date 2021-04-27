package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G5_9019_DSLR_정태현_solved {
	static boolean[] visited;
	static char[] register;
	static int[] node;
	static int A, B;
	static Queue<Integer> q;
	static Stack<Character> s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken()); //원래 숫자
			B = Integer.parseInt(st.nextToken()); //바뀐 숫자
			visited = new boolean[10000]; //숫자 체크용 1~9999
			register = new char[10000];
			node = new int[10000];
			s = new Stack<>();
			q = new LinkedList<>();
			
			//처음 값, 초기에 없는 reg 값은 이렇게 처리해도 될까?
			q.offer(A);
			visited[A] = true;
			process();
			
			int size = s.size();
			for (int i = 0; i < size; i++) {
				System.out.print(s.pop());
			}
			System.out.println();
		}
	}
	
	private static void process() {
		while(true) { //찾을 때까지 계속 돈다
			int cur = q.poll();
			int D = cal('D', cur);
			int S = cal('S', cur);
			int L = cal('L', cur);
			int R = cal('R', cur);
			
			if(!visited[D]) { //그 값이 아직 나오지 않았으면
				visited[D] = true;
				node[D] = cur;
				register[D] = 'D';
				q.offer(D);
			}
			if(!visited[S]) { //그 값이 아직 나오지 않았으면
				visited[S] = true;
				node[S] = cur;
				register[S] = 'S';
				q.offer(S);
			}
			if(!visited[L]) { //그 값이 아직 나오지 않았으면
				visited[L] = true;
				node[L] = cur;
				register[L] = 'L';
				q.offer(L);
			}
			if(!visited[R]) { //그 값이 아직 나오지 않았으면
				visited[R] = true;
				node[R] = cur;
				register[R] = 'R';
				q.offer(R);
			}
			
			//4개의 register 다 확인하고 나서는 무조건 B를 체크
			if(visited[B]) {
				int curNode = B;
				while(curNode != A) { //처음 값에 도달 시 stop
					s.push(register[curNode]);
					curNode = node[curNode];
				}
				
				break;
			}
		}
	}

	private static int cal(char rval, int n) {
		int c1 = n % 1000; //좌우 이동 용
		int c2 = n / 1000;
		switch(rval) {
			case 'D': //n을 2배로
				n = (2*n) % 10000;
				break;
			case 'S':
				n = n==0? 9999: n-1;
				break;
			case 'L':
				n = (c1 * 10) + c2;
				break;
			case 'R':
				n = (n / 10) + ((n%10) * 1000);
		}
		
		return n;
	}
}
