package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_D4_1957_수영장_정태현_solved {
	static int[] ticket; //티켓 가격
	static int[] use; //사용할 일
	static int ans; //최소 출력용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ticket = new int[4]; //이용권
			use = new int[12]; //12달
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < ticket.length; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < use.length; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = ticket[3]; //1년 이용가격
			dfs(0, 0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	private static void dfs(int u, int p) {
		if(u>=12) { //0~11 => 12달 다 돌았다
			ans = Math.min(ans, p);
			return;
		}
		
		if(use[u] == 0) { 
			//체크한 월에 사용 안하면 다음 달로 넘어가자
			dfs(u+1, p);
		} else {
			for (int i = 0; i < ticket.length - 1; i++) { //1년 이용권 제외
				if(i==0) {
					dfs(u+1, p+(use[u]*ticket[0])); //1일
				//뒤부터 하나하나씩 1달, 3달로 추가하면서 dfs
				} else if(i==1) {
					dfs(u+1, p+ticket[1]);	//1달		
				}else {
					dfs(u+3, p+ticket[2]);	//3달									
				}
			}
		}
	}
}