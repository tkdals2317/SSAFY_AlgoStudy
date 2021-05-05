import java.io.*;
import java.util.*;

public class Main_bj_12865_평범한배낭_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());	//아이템 개수
		int K=Integer.parseInt(st.nextToken());	//무게제한
		
		int[] weight=new int[N+1];	//무게 배열
		int[] profit=new int[N+1];		//가치 배열
		
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine());
			weight[i]=Integer.parseInt(st.nextToken());
			profit[i]=Integer.parseInt(st.nextToken());
			
		}
		
		int[][] bae=new int[N+1][K+1];	//첫칸은 비워둠
		// 상향식 
		for (int i = 1; i <= N; i++) {	//물건
			for(int w=1;w<=K;w++) {	//무게 1부터 제한 무게 K까지
				if(weight[i]<=w) {	// 가방에 넣을수 있는상황
					//이전에서 자신의 무게를 뺀 최적값+자신을 포함시킨 무게  vs 이전의 최적해중 더 높은 가격 선택해서 넣어줌
					bae[i][w]=Math.max(bae[i-1][w-weight[i]]+profit[i],bae[i-1][w]);
				}else {	//가방에 넣지 못하는 상황 이전의 최적해를 그냥 넣어줌
					bae[i][w]=bae[i-1][w];
				}
				
			}
		}
		
		System.out.println(bae[N][K]);
		
		
		br.close();
	}

}
