package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_5904_Moo게임_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		
		recurse(1,4,N);		//시작점 1, 처음mooo 크기 4, 찾는 위치
		
		br.close();
	}

	//재귀함수 가지치기한것??조건 넣은것
	private static void recurse(int idx, int count,int N) {
		if(idx+3>N) {		//3칸안에 원하는 값 있는지
			if(N-idx==0)System.out.println("m");	//찾는값과 차가 0이면 m 출력
			else System.out.println("o");
			return;
		}
		idx+=3;		//3칸 증가
		if(idx+count>N) {		//count안에 원하는 값 있는지
			if(N-idx==0)System.out.println("m");	//찾는값과 차가 0이면 m 출력
			else System.out.println("o");
			return;
		}
		recurse(idx+count,count+1,N);	//idx count 만큼 증가, moo의 개수 1증가
		
	}

}
