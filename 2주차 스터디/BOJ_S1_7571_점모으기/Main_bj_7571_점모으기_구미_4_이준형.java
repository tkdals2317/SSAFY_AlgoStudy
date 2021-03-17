package baekjun;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_7571_점모으기_구미_4_이준형 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dx = new int[M + 1];
		int[] dy = new int[M + 1];

		// 배열 입력
		for (int t = 1; t <= M; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			dx[t] = x;
			dy[t] = y;
		}

		// 각 지점 순환
		int len_short=M*N*2;
		int len=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				len=0;
				//점까지 거리측정
				for(int t=1;t<=M;t++) {
					len+=Math.abs(dx[t]-i)+Math.abs(dy[t]-j);
					if(len>=len_short)
						break;
				}
				//크기비교
				if(len<len_short)
					len_short=len;
			}
		}

		System.out.println(len_short);
		br.close();
	}

}
