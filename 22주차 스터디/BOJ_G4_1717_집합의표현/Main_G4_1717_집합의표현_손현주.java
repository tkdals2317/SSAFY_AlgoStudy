import java.io.*;
import java.util.*;

public class bj_1717_G4 {

	static int N,M,P[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = new int[N+1];
		for(int i=0;i<=N;++i) P[i]=i;//나는 두목
		for(int i=0;i<M;++i) {//명령수행
			st = new StringTokenizer(br.readLine()," ");
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(order==0) union(a,b); // 합연산
			else System.out.println(find(a)==find(b)?"YES":"NO");// 같은부모(=같은집합)판별 후 출력
		}
	}
	
	static int find(int a) {//두목찾기
		if(P[a]==a) return a;
		return P[a]=find(P[a]);
	}
	
	static void union(int a, int b) {//합치기
		a=find(a);
		b=find(b);
		if(a!=b) P[b]=a;
	}
}
