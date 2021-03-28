import java.io.*;
import java.util.*;

public class Main_bj_1922_네트워크연결_구미_4_이준형 {
	
	static class pos implements Comparable<pos>{	//간선 클래스
		int from;
		int to;
		int num;
		public pos(int from, int to, int num) {
			super();
			this.from = from;
			this.to = to;
			this.num = num;
		}
		@Override
		public int compareTo(pos o) {
			return Integer.compare(num, o.num);
		}
	}
	
	static int N,M;	//컴퓨터수,간선수
	static int[] parents;	//부모 배열
	static PriorityQueue<pos> pq;

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		pq=new PriorityQueue<>();	//우선순위 큐
		StringTokenizer st;
		for(int i=0;i<M;i++) {	//큐에 넣기
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int num=Integer.parseInt(st.nextToken());
			pq.offer(new pos(from,to,num));
		}
		
		
		//크루스칼 알고리즘
		make();	//부모 정점 생성
		int count=0;
		int len=0;
		while(!pq.isEmpty()) {	//간선 최소 길이 순으로 선택 같은 부모면 선택x 아니면 합치기
			if(count==N-1) {	//간선을 정점개수-1개 선택하면 끝
				break;
			}
			pos tmp=pq.poll();
			if(union(tmp.from,tmp.to)) {
				count++;
				len+=tmp.num;
			}
		}
		
		System.out.println(len);
		
		br.close();
	}

	//같은 부모 가리키도록 합치기
	private static boolean union(int a, int b) {
		int aroot=find(a);
		int broot=find(b);
		if(aroot==broot)
			return false;
		parents[broot]=aroot;	//b가 a루트를 부모로 가지도록 변경
		return true;
	}

	//이어진 부모 정점 찾기 찾는 과정에서 
	private static int find(int a) {
		if(parents[a]==a)	//자신이 부모면 리턴
			return a;
		return parents[a]=find(parents[a]);	//부모 정점에 붙여서 압축
	}

	//자신 가리키는 부모 정점 생성
	private static void make() {	
		parents = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parents[i]=i;
		}
	}

}
