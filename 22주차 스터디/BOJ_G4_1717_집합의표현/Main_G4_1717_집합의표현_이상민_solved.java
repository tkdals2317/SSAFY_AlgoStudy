import java.util.*;
import java.io.*;
public class Main_G4_1717_집합의표현_이상민_solved {
	static int N, M;
	static int [] parents;
	//각각의 부모를 자기자신으로 둔 집합 만들기
	static void make(){
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}
	//부모찾기
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}	
	//합집합
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_1717_집합의표현.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		make();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//합집합			
			if(oper == 0) {
				union(a,b);
			}else {
				int aRoot = find(a);
				int bRoot = find(b);
				if(aRoot==bRoot) {
					sb.append("YES").append("\n");
				}else {
					sb.append("NO").append("\n");					
				}
			}
			
		}
		System.out.println(sb.toString());
		br.close();
	}
}
