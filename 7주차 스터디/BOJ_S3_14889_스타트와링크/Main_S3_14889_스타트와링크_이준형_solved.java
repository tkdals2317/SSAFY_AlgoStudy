import java.io.*;
import java.util.*;

public class Main_bj_14889_스타트와링크_구미_4_이준형 {

	static int N;	//배열 크기
	static int[][] map;	//맵
	static int[] use;	//사용 체크
	static int min_ans;	//최소차이값
	
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		
		map=new int[N][N];
		for(int i=0;i<N;i++) {	//배열 입력
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		use=new int[N];
		min_ans=Integer.MAX_VALUE;
		select_team(0,0);
		
		System.out.println(min_ans);
		
		br.close();
	}

	//팁 나누기 조합
	private static void select_team(int idx, int count) {
		if(count==N/2) {	//선택조건
			check_score();
			return;
		}
		else if(idx>=N)return;	//탈출조건
		
		use[idx]=1;
		select_team(idx+1, count+1);	//선택
		use[idx]=0;
		select_team(idx+1, count);	//선택x
		
	}
	
	//점수 체크
	private static void check_score() {
		int[][] map_count=new int[N][N];	//선택 체크용 배열 생성
		
		for(int k=0;k<N;k++) {	//항목별로돌면서
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(i==k||j==k) {	//행이나 가로중 하나가 해당하면
						if(use[k]==0) {
							map_count[i][j]++;	//1팀용
						}else {
							map_count[i][j]--;	//2팀용
						}
					}
				}
			}
		}
		
		int score1=0;	//팀별 점수
		int score2=0;
		for(int i=0;i<N;i++) {	//팀별점수 계산
			for(int j=0;j<N;j++) {
				if(map_count[i][j]==2) {
					score1+=map[i][j];
				}else if(map_count[i][j]==-2) {
					score2+=map[i][j];
				}
			}
		}
		
		int ans=Math.abs(score1-score2);	//팀별 차이 계산
		min_ans=Math.min(min_ans, ans);	//팀별 가장 작은 차이
		
	}

}
