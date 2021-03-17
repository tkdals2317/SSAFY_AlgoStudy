package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_1697_solved {
	static int N, K;
	static int[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001]; //0~10000
		if(N!=K) { //��ġ�� �ٸ� ���
			System.out.println(bfs(N)); //������ ��ġ
		}else {
			System.out.println("0");
		}
	}
	
	public static int bfs(int n) { //tree ���� Ȯ��
		Queue<Integer> q = new LinkedList<>();
		visited[n] = 1;
		q.offer(n); //���� ������ ��ġ ť�� ����
		while(!q.isEmpty()) {
			int cur = q.poll(); 
			for(int i=0; i<3; i++) {//move ��� ���� 3����
				int np;
				if(i == 0) //�ش� ĭ cur���� �� �� �ִ� ��� ĭ�� üũ
	                np = cur - 1;
	            else if(i == 1)
	                np = cur + 1;
	            else
	                np = cur * 2;

	            if(np == K) return visited[cur];
	            //�����ڸ��� üũ���ָ� �ּҰ�
	
	            if(0 <= np && np <= 100000) {
	                if(visited[np]==0) { //���� ���� ĭ�϶�
	                    q.offer(np); //���� �۾� ���� ť
	                    visited[np] = visited[cur] + 1; //ù��° ĭ���� ��ŭ �����̸� �� �� �ִ���
	                    //�� ĭ���� �ѹ��� �������� ���� �ִ� ĭ�� +1 �� ����
	                }
	            }
			}
		}
		
		return 0;
	}
}
