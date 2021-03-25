import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main_bj_1991_트리순회_이상민_solved {
	private static int N;
	private static char[][] node;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1991.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		node = new char[N+1][3];
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			node[i][0] = st.nextToken().charAt(0);
			node[i][1] = st.nextToken().charAt(0);
			node[i][2] = st.nextToken().charAt(0);
		}
		preoder(1);
		System.out.println();
		inoder(1);
		System.out.println();
		postorder(1);
		br.close();
	}

	private static void preoder(int current) {
		if(current>N) return;
		System.out.print(node[current][0]);
		for (int i = 1; i < N+1; i++) {
			if(node[current][1]==node[i][0]) {
				preoder(i);
			}
		}
		for (int i = 1; i < N+1; i++) {
			if(node[current][2]==node[i][0]) {
				preoder(i);
			}
		}
	}
	private static void inoder(int current) {
		if(current>N) return;

		for (int i = 1; i < N+1; i++) {
			if(node[current][1]==node[i][0]) {
				inoder(i);
			}
		}
		System.out.print(node[current][0]);
		for (int i = 1; i < N+1; i++) {
			if(node[current][2]==node[i][0]) {
				inoder(i);
			}
		}
	}
	private static void postorder(int current) {
		if(current>N) return;

		for (int i = 1; i < N+1; i++) {
			if(node[current][1]==node[i][0]) {
				postorder(i);
			}
		}

		for (int i = 1; i < N+1; i++) {
			if(node[current][2]==node[i][0]) {
				postorder(i);
			}
		}
		System.out.print(node[current][0]);
	}
}
