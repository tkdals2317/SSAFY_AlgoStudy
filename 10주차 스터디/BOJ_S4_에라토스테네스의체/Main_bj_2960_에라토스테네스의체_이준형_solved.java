import java.io.*;
import java.util.*;

public class Main_bj_2960_에라토스테네스의체_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		
		boolean[] bae=new boolean[N+1];
		
		int count=0;
		while(true) {	//무한 반복
			int num=-1;
			for(int i=2;i<N+1;i++) {	//작은 값 찾기
				if(bae[i]==false) {
					num=i;
					break;
				}
			}
			
			if(num==-1)	//값 찾은게 없으면 좋료
				break;

			int go=num;	//기존 숫자
			while(num<N+1) {	//배열 끝까지 가면서 항목 찾으면 true
				if(bae[num]==false) {
					bae[num]=true;
					count++;
				}
				if(count==K) {	//K번째꺼 찾으면 종료
					System.out.println(num);
					System.exit(0);
				}
				num+=go;
			}
		}
		
		br.close();
	}

}
