
public class Main_합승택시요금_정소영_solved {

}

class Solution {
	static int[][] map;
	static final int INF = Integer.MAX_VALUE;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		// s 출발, a, b
		

		map = new int[n + 1][n + 1];	// 인접 행렬
		int size = fares.length;
		for (int i = 0; i < size; i++) {
			int c = fares[i][0];
			int d = fares[i][1];
			int v = fares[i][2];
			map[c][d] = map[d][c] = v;	// 연결관계 입력(양방향)
		}

		// 입력 완료
        
        int answer = Integer.MAX_VALUE;	// 최소값이 답이니 MAX 로 초기화
        

		for (int k = 1; k <= n; k++) {	// 경유지 k
			for (int i = 1; i <= n; i++) {	// 출발지 i
				// if (i == k)
				// 	continue; // 출발지와 경유지가 같다면 다음 출발지 -> 하지만 이번 경우에는 경유지를 거치지 않는 경우도 고려
				for (int j = 1; j <= n; j++) {	// 도착지 j
					if (i == j) continue;	// 출발지와 목적지가 같은 경우 패스
					if (map[i][k] != 0 && map[k][j] != 0){ // i->k->j 순으로 가는데 각 경로의 값이 있는 경우
                        if(map[i][j]==0)    map[i][j] = map[i][k] + map[k][j];	
                        // 만약 i->j로 직접 가는 경우가 없었던 경우 k를 경유해서 가는 걸로 값 지정
                        else map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                        // 만약 i->j로 갈 때 바로 가거나 다른 곳을 경유하거나 어쨌든 값이 있는 경우 최소값을 해당 인접행렬에 저장.
                    }
				}
			}
		}

		//show(n);

        
        
		for(int i=1;i<=n;i++) {
			int tmp = map[s][i] + map[i][a] + map[i][b];	
            // s->i->a,b 가는 경우 (s==i, 즉 map[s][i]==0 일 수도 있음)
            
			if(tmp==0) continue;	// tmp가 0이라면 해당 i 지점을 경유해서 가는 것이 불가라는 뜻
			answer = Math.min(answer, tmp);	// 0이 아니라면 최소값 answer에 저장
		}
        
        
        // case 1 : 경유지가 5일때 => 4->5 : 34 / 5->6(a) : 2 / 5->2(b) : 46 => 총 82
        // case 2 : 경유지가 없을때(즉 경유지가 3일때) => 3->3 : 0 / 3->4(a) : 5 / 3->1(b) : 9 => 총 14
		return answer;
	}

	private void show(int n) {
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}