package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_14226_이모티콘_정태현_solved {
	static class Emo {
		int cur = 0; //현재 화면에
		int cb = 0; //클립보드
		int sec = 0; //시간
		
		public Emo(int cur, int cb, int sec) {
			super();
			this.cur = cur;
			this.cb = cb;
			this.sec = sec;
		}

		@Override
		public String toString() {
			return "Emo [cur=" + cur + ", cb=" + cb + ", sec=" + sec + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int S = Integer.parseInt(br.readLine());
		
		Queue<Emo> q = new LinkedList<Emo>();
		q.offer(new Emo(1, 0, 0));
		//dp[현재][클립보드]
		boolean[][] dp = new boolean[10001][10001];
		//처음에 1개로 시작
//		dp[1][0] = 0;
//		for (int i = 0; i < dp[0].length; i++) {
//			Arrays.fill(dp[i], Integer.MAX_VALUE);
//		}
		while(!q.isEmpty()) {
			Emo current = q.poll();
			System.out.println(current);
			//종료
			if(current.cur == S) {
				System.out.println(current.sec);
				break;
			}
			
			//1번(cur 이모티콘 수 모두 복사해서 클립보드에 저장) 수행
			if(!dp[current.cur][current.cur]) {
				q.offer(new Emo(current.cur, current.cur, current.sec+1));
				dp[current.cur][current.cur] = true;
			}
			
			//2번(클립보드의 이모티콘 화면에) 수행
			if(current.cb != 0 && current.cur + current.cb <= S && !dp[current.cur+current.cb][current.cb]) {
				q.offer(new Emo(current.cur+current.cb, current.cb, current.sec+1));
				dp[current.cb][current.cur+current.cb] = true;
			}
			
			//3번(이모티콘 한개 삭제) 수행
			if(current.cur >= 1 && !dp[current.cur-1][current.cb]) {
				q.offer(new Emo(current.cur-1, current.cb, current.sec+1));
				dp[current.cur-1][current.cb] = true;
			}
		}
	}
}
