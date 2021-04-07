import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_16234_인구이동_변준형_solved {
	static int N,L,R, A[][],ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new  StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans =0;
		while(true) {
			//	바꾸는 h집합 
			ArrayList<ArrayList> al = new ArrayList<ArrayList>();
			// 	바꾸는 걸 찾자 이제
			// 	방문한건 visitied[]로 
			boolean [] visitied = new boolean[N*N];
			for (int n = 0; n < N*N; n++) {
				if(visitied[n])continue;
				ArrayList<Integer> h = 	new ArrayList<Integer>();
				h.add(n);
				visitied[n]= true;
				int i  = 0;
				int sum=A[n/N][n%N];
				while(i<h.size()) {
					int nn = h.get(i);
					int r = nn/N;
					int c = nn%N;
					//	두 나라가 공유하는 국경선을 오늘 하루동안 연다
					if(r>0&& !visitied[nn-N]) {
						int d = Math.abs(A[r][c]-A[r-1][c]);
						if(d>=L&& d<=R) {
							h.add(nn-N);
							visitied[nn-N]=true;
							sum+=A[r-1][c];
						}
					}
					if(c>0&& !visitied[nn-1]) {
						int d = Math.abs(A[r][c]-A[r][c-1]);
						if(d>=L&& d<=R) {
							h.add(nn-1);
							visitied[nn-1]=true;
							sum+=A[r][c-1];
						}
					}
					if(r<N-1&& !visitied[nn+N]) {
						int d = Math.abs(A[r][c]-A[r+1][c]);
						if(d>=L&& d<=R) {
							h.add(nn+N);
							visitied[nn+N]=true;
							sum+=A[r+1][c];
						}
					}
					if(c<N-1&& !visitied[nn+1]) {
						int d = Math.abs(A[r][c]-A[r][c+1]);
						if(d>=L&& d<=R) {
							h.add(nn+1);
							visitied[nn+1]=true;
							sum+=A[r][c+1];
						}
					}
					i++;
				}
				if(h.size()>1) {
					h.add(sum/h.size());
					al.add(h);
				}
			}
			
			
			
			//hs 집합이 비어있으면 break;
			if(al.isEmpty())break;	// 만약 변경할 것이 없으면 더이상 인구이동을 멈춘다.
			for (ArrayList<Integer> h : al) {
				int s = h.size();
				for (int j = 0; j < s-1; j++) {
					int nn =h.get(j);
					int r = nn/N;
					int c = nn%N;
					A[r][c]= h.get(s-1);
				}
			}
			// 아니면 ans+=1;
			ans++;
		}
		System.out.println(ans);
	}
}

