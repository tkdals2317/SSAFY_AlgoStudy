import java.io.*;
import java.util.*;

public class Main_S1_2667_단지번호붙히기_이상민_solved {
	
	static int N; // map의 크기
	static int [][] map; // map을 담을 int 2차원 배열
	static boolean [][] v; // 방문처리를 위한 boolean 2차원 배열
	static int [] di = {-1, 0, 1, 0}; //상 우 하 좌
	static int [] dj = { 0, 1, 0, -1};//상 우 하 좌
	static ArrayList<Integer> danji; // 단지 별 세대수를 저장할 ArrayList
	
	//좌표를 표시하기 위한 클래스
	static class Point{ 
		int pi;
		int pj;
		public Point(int pi, int pj) {
			super();
			this.pi = pi;
			this.pj = pj;
		}	
	}
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("res/input_bj_2667.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input 및 초기화
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N];
		danji = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		//map[i][j]가 아파트이고 방문하지 않은 경우에 bfs 호출
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1&&!v[i][j]) bfs(new Point(i,j));
			}
		}
		
		System.out.println(danji.size()); //총 단지 수 출력
		
		//단지별 세대수 오름차순 정렬
		danji.sort(new Comparator<Integer>() { 
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		// 결과 출력
		for (int i = 0; i < danji.size(); i++) {
			System.out.println(danji.get(i));
		}
		br.close();
	}
	static void bfs(Point start) {
		int cnt = 0; //세대수 카운트를 위한 cnt 변수
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		v[start.pi][start.pj] = true;
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			int ci = current.pi;
			int cj = current.pj;
			
			cnt++;
			//System.out.println(cnt);
			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				// 범위체크 + 방문 확인
				if(ni>=0 && ni<N && nj>=0 && nj<N &&!v[ni][nj] && map[ni][nj]==1) {
					queue.offer(new Point(ni,nj));
					//여기서 방문체크를 해줘야 함! 안그러면 다른 큐에 있던 놈이 중복으로 큐에 삽입 가능
					v[ni][nj] = true;
				}
			}	
		}
		// 총 세대수를 단지에 추가
		danji.add(cnt);
	}
}
