import java.io.*;
import java.util.*;

public class Main_bj_2852_NBA농구_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int[] score= new int[3];	//점수
		int[][] time= new int[3][5];	//시간 0:전체시간 1,2: 각팀이 이긴 시간
		
		
		int T=Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int team=Integer.parseInt(st.nextToken());
			String str=st.nextToken();
			//점수 비교 각팀 시간증가
			if(score[1]!=score[2]) {
				int win=score[1]>score[2]?1:2;
				for(int i=0;i<5;i++) {
					if(i==2)
						continue;
					int tmp=(str.charAt(i)-'0');
					time[win][i]+=tmp-time[0][i];	//각팀 시간 증가
				}
			}
			//전체 시간  증가
			for(int i=0;i<5;i++) {
				if(i==2)
					continue;
				time[0][i]=(str.charAt(i)-'0');
			}
			//점수 증가
			score[team]++;
			
		}
		//48분까지 남은 시간 처리
		String str="48:00";
		if(score[1]!=score[2]) {
			int win=score[1]>score[2]?1:2;
			for(int i=0;i<5;i++) {
				if(i==2)
					continue;
				int tmp=(str.charAt(i)-'0');
				time[win][i]+=tmp-time[0][i];	//각팀 시간 증가
			}
		}
		//시간 초로 통합
		int[] ans=new int[3];
		for(int i=1;i<=2;i++) {
			int t=0;
			t+=time[i][0]*600;
			t+=time[i][1]*60;
			t+=time[i][3]*10;
			t+=time[i][4];
			ans[i]=t;
		}
		//형태에 맞게 출력
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=2;i++) {
			int t=ans[i];
			int h=t/60;
			if(h<10)
				sb.append(0);
			sb.append(h+":");
			int m=t%60;
			if(m<10)
				sb.append(0);
			sb.append(m+"\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.print(sb);
		
		
		br.close();
	}

}
