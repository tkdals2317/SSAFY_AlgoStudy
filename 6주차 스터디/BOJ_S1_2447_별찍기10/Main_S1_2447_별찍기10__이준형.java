import java.io.*;
import java.util.*;

public class Main_bj_2447_별찍기10_구미_4_이준형 {

	static int[][] map;
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int size=Integer.parseInt(br.readLine());
		
		map=new int[size][size];
		search(0,0,size);
		
		for(int i=0;i<size;i++) {	//별찍은거 출력
			for(int j=0;j<size;j++) {
				if(map[i][j]==1)	//1이면 별
					sb.append("*");
				else sb.append(" ");//아니면 빈칸
			}sb.append("\n");//띄워쓰기
		}

		System.out.println(sb);
		br.close();
	}

	//재귀 분할정복
	private static void search(int get_i, int get_j, int size) {
		if(size==1) {
			map[get_i][get_j]=1;
			return;
		}
		size=size/3;	//크기 나누기3
		for(int i=0;i<3;i++) {	//9분할 탐색
			for(int j=0;j<3;j++) {
				if(i==1&&j==1) {}	//중간일경우 처리x
				else {
					search(get_i+i*size, get_j+j*size, size);
				}
			}
		}
		
	}

}
