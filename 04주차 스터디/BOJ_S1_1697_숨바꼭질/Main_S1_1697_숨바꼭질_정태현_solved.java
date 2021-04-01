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
		if(N!=K) { //위치가 다를 경우
			System.out.println(bfs(N)); //수빈이 위치
		}else {
			System.out.println("0");
		}
	}
	
	public static int bfs(int n) { //tree 구조 확인
		Queue<Integer> q = new LinkedList<>();
		visited[n] = 1;
		q.offer(n); //현재 수빈이 위치 큐에 삽입
		while(!q.isEmpty()) {
			int cur = q.poll(); 
			for(int i=0; i<3; i++) {//move 방법 수는 3가지
				int np;
				if(i == 0) //해당 칸 cur에서 갈 수 있는 모든 칸을 체크
	                np = cur - 1;
	            else if(i == 1)
	                np = cur + 1;
	            else
	                np = cur * 2;

	            if(np == K) return visited[cur];
	            //나오자마자 체크해주면 최소값
	
	            if(0 <= np && np <= 100000) {
	                if(visited[np]==0) { //간적 없는 칸일때
	                    q.offer(np); //다음 작업 위해 큐
	                    visited[np] = visited[cur] + 1; //첫번째 칸에서 얼만큼 움직이면 갈 수 있는지
	                    //그 칸에서 한번씩 움직여서 갈수 있는 칸에 +1 값 저장
	                }
	            }
			}
		}
		
		return 0;
	}
}
