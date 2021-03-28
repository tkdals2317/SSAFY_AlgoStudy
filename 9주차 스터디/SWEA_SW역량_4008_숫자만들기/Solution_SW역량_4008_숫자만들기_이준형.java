import java.io.*;
import java.util.*;

public class Solution_SW역량_4008_숫자만들기_구미_4_이준형2 {

	static int N; // 배열 개수
	static int min_val, max_val; // 최소 최대
	static int[] bae_num; // 숫자배열
	static int[] bae_oper; // 계산할때 사용할 배열
	static int[] bae;	//연산자 개수 저장
	static int[] select; // 선택체크

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			bae = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) { // 연사자의 개수 배열에 넣기
				bae[i] = Integer.parseInt(st.nextToken());
			}

			bae_num = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { // 숫자 배열에 넣기
				bae_num[i] = Integer.parseInt(st.nextToken());
			}

			min_val = Integer.MAX_VALUE; // 초기화
			max_val = Integer.MIN_VALUE;
			select = new int[N - 1];
			bae_oper = new int[N - 1];
			choice(0); // 순열 문자 선택

			// 차이계산
			int ans = Math.abs(max_val - min_val);
			System.out.println("#" + tc + " " + ans);

		}

	}

	// 문자 선택 dfs
	private static void choice(int idx) {
		if (idx == N - 1) { // 문자 선택 완료
			int ans = calc(); // 연산
			min_val = Math.min(min_val, ans); // 최대 최소값과 비교
			max_val = Math.max(max_val, ans);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(bae[i]>0) {	//항목이 남아있으면
				bae_oper[idx]=i;	//연산할 배열에 넣어주기
				bae[i]--;	//해당항목 사용
				choice(idx+1);	//다음거 선택
				bae[i]++;	//다시 복구
			}

		}
	}

	// 선택한것 연산
	private static int calc() {
		int ans = bae_num[0]; // 결과 저장용
		for (int i = 0; i < N - 1; i++) { // 연산자의 개수만큼 연산
			switch (bae_oper[i]) { // 문자를 기준으로 다음 값 과 연산
			case 0:
				ans += bae_num[i + 1];
				break;
			case 1:
				ans -= bae_num[i + 1];
				break;
			case 2:
				ans *= bae_num[i + 1];
				break;
			case 3:
				ans /= bae_num[i + 1];
				break;
			}
		}
		return ans;
	}

}
