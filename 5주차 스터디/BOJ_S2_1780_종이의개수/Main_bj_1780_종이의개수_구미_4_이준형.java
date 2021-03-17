package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_1780_종이의개수_구미_4_이준형 {

	static int[] bae_count;	//값 저장 배열
	static int[][] bae;
	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		bae=new int[N][N];
		for(int i=0;i<N;i++) {	//배열 입력
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				bae[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		bae_count=new int[3];
		search(0,0,N);	//재귀함수 호출
		
		for(int i=0;i<3;i++) {	//저장한 값 출력
			System.out.println(bae_count[i]);
		}
		
		br.close();
	}
	
	//분할탐색 재귀
	private static void search(int get_i, int get_j, int size) {
		int flag=0;
		int tmp=bae[get_i][get_j];
		for(int i=get_i;i<get_i+size;i++) {	//배열 탐색하면서 다른지 체크
			for(int j=get_j;j<get_j+size;j++) {
				if(bae[i][j]!=tmp) {	//다른 값이 나오면 flag 1로
					flag=1;
					break;
				}
			}if(flag==1)break;
		}
		if(flag==0) {		//모두 같은 값이면 해당 값 배열 증가하고 종료
			bae_count[tmp+1]++;
			return;
		}
		else {		//다른 값이 존재하면 다시 9분할
			int s=size/3;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					search(get_i+s*i,get_j+s*j,size/3);	//재귀
				}
			}
		}
		
	}

}
