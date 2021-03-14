import java.io.*;
import java.util.*;

public class Solution_SW역량_1952_수영장_구미_4_이준형 {

	static int[] bae;
	static int[] bae_money;
	static int min_money;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {	//테스트케이스 만큼 반복
			
			bae_money=new int[4];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {	//달별 요금제
				bae_money[i]=Integer.parseInt(st.nextToken());
			}
			bae=new int[12];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<12;i++) {	//월별 사용량
				bae[i]=Integer.parseInt(st.nextToken());
			}
			
			min_money=Integer.MAX_VALUE;	//초기화
			choice(0,0);
			
			System.out.print("#"+tc+" ");
			if(min_money>bae_money[3])		//1년과 비교후 답 출력
				System.out.println(bae_money[3]);
			else
				System.out.println(min_money);
			
		}
		
	}

	//요금 선택 dfs
	private static void choice(int idx, int money) {
		if(idx>=12) {	//탈출조건
			min_money=Math.min(min_money, money);	//가장 적은 소모값과 비교
			return;
		}
		if(bae[idx]==0)	//이용일이 없는경우 그냥 증가
			choice(idx+1, money);
		else {
			choice(idx+1, money+bae[idx]*bae_money[0]);	//1일단위
			choice(idx+1, money+bae_money[1]);	//1달단위
			choice(idx+3, money+bae_money[2]);	//3달단위
		}
	}

}
