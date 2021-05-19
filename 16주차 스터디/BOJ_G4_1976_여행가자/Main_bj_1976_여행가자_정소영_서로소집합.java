import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_1976_여행가자_서로소집합 {
	static int N, M;
	static int[] city;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		city = new int[N+1];	// 1~N개의 도시
		make();
		System.out.println(Arrays.toString(city));
		
		StringTokenizer st; 
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp==1) {	// i와 j가 연결이 되어 있을 경우
					union(i,j);
//					System.out.println(union(i,j));
//					System.out.println(i+"와 "+j+" 연결 : "+"[0, 1, 2, 3, 4, 5]");
//					System.out.println(i+"와 "+j+" 연결 : "+Arrays.toString(city));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");	
		int start = findSet(Integer.parseInt(st.nextToken()));	// 시작점의 루트노드 설정
		for (int i = 2; i <= M; i++) {	// 시작점에서 M개의 도시까지 탐색
			int next = Integer.parseInt(st.nextToken());	// 다음 도시 설정
			
			if(start != findSet(next)) {	// 다음 도시와 시작점이 연결되어 있지 않으면 불가
				System.out.println("NO");
				br.close();
				System.exit(0);
			}
		}
		
		System.out.println("YES");
		br.close();
	}
	
	static void make() {	// 크기가 1인 단위집합을 만든다.
		for (int i = 1; i <= N; i++) {
			city[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(city[a]==a) {
			return a;
		}
		return city[a] = findSet(city[a]);
	}
	
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		city[bRoot] = aRoot;
		return true;
	}

}
