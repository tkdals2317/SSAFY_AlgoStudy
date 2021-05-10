package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B2_2852_NBA농구_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); //골이 들어간 횟수
		//골 정보, N+1인 이유는 마지막에 48:00을 저장하기 위함
		//다음 칸에서 지금 시간을 빼는 방식으로 계산하기 위해 마지막 48:00 값 저장
		int[][] goal = new int[N+1][3]; 
		int[][] win = new int[3][2]; //각 팀의 이기고 있던 시간 정보

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			goal[i][0] = Integer.parseInt(st.nextToken());
			//분과 초를 :로 split
			String[] str = st.nextToken().split(":");
			goal[i][1] = Integer.parseInt(str[0]);
			goal[i][2] = Integer.parseInt(str[1]);
		}
		
		//종료지점 48:00
		goal[N][0] = 0;
		goal[N][1] = 48;
		goal[N][2] = 0;
		
		int one = 0, two = 0;
		int leader = 0;
		for (int i = 0; i < N; i++) {
			//각 득점한 팀에 +1해준다.
			if(goal[i][0]==1) one++;
			else two++;
			
			//득점 처리 해줬을 때 무승부인 상태면 아래 시간처리하는 부분을 돌 필요가 없다
			if(one==two) continue;
			
			//앞서가는 팀
			leader = one > two ? 1 : 2;
			
			//분, win 배열의 leader번째 인덱스의 0번째 칸에 저장
			win[leader][0] += goal[i+1][1] - goal[i][1];
			//초, win 배열의 leader번째 인덱스의 1번째 칸에 저장
			//분과 초를 따로 계산하기 때문에 초 계산결과가 음수임을 생각해야 함
			if(goal[i+1][2] - goal[i][2] < 0) { //초를 뺐을 때 음수가 나온다면
				//분에서 1분(60)을 넘겨줘서(-1) 초 윗항에 60을 더해줘서 빼는 흔한 빼기 방식
				win[leader][0]--;
				win[leader][1] += 60+goal[i+1][2] - goal[i][2];
			} else { //양수라면 그냥 그대로 넣어줌
				win[leader][1] += goal[i+1][2] - goal[i][2];
			}
			
			//기존 저장된 시간에 계산한 값을 더해주는 거라서 초가 60이 넘어갈 수 있음
			//초가 60이 넘어가면 60을 1분으로 넘겨주는 부분
			if(win[leader][1]>=60) {
				win[leader][0]++;
				win[leader][1] -= 60;
			}
		}
		
		for (int i = 1; i <= 2; i++) {
			StringBuilder min = new StringBuilder();
			StringBuilder sec = new StringBuilder();
			if(win[i][0]/10 < 1) { //분이 한자리수, 0~9 ex) 1 -> 01
				min.append("0").append(win[i][0]);
			} else {
				min.append(win[i][0]);
			}
			if(win[i][1]/10 < 1) { //초가 한자리수, 0~9 ex) 1 -> 01
				sec.append("0").append(win[i][1]);
			} else {
				sec.append(win[i][1]);
			}
			System.out.println(min+":"+sec);
		}
	}
}
