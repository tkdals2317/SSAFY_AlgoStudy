package swea;
import java.io.*;
import java.util.*;

public class Solution_d3_7964_부먹왕국의차원관문_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());	//도시수
			int D=Integer.parseInt(st.nextToken());	//제한거리
			
			int[] bae=new int[N];	//도시 배열
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				bae[i]=Integer.parseInt(st.nextToken());
			}
			
			int count=0;
			int door=0;
			for(int i=0;i<N;i++) {		
				if(bae[i]==1) {		//1이면 카운트 초기화
					count=0;
				}
				else if(bae[i]==0) {	//0이면 카운트 증가
					count++;
				}
				if(count==D) {	//도시가 D개 나오면 다음에 관문 필요
					count=0;
					door++;		//문개수 증가
				}
			}
			System.out.println("#"+tc+" "+door);
			
		}
			
		br.close();
	}

}
