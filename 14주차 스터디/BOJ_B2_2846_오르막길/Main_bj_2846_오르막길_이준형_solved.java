package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_2846_오르막길_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=0;	//현재 높이
		int max_len=0;	//최대 높이 차이
		int start=0;	//시작높이
		num=start=Integer.parseInt(st.nextToken());
		for(int i=1;i<N;i++) {
			int tmp=Integer.parseInt(st.nextToken());
			if(tmp<=num) {	//높이가 같거나 낮으면 시작 높이 초기화
				start=tmp;
			}
			num=tmp;
			max_len=Math.max(max_len, num-start);
		}

		System.out.println(max_len);
		
		br.close();
	}

}
