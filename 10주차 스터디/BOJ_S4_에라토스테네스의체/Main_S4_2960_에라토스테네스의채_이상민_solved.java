import java.util.*;
import java.io.*;

public class Main_S4_2960_에라토스테네스의채_이상민_solved {
	static int N; //2부터 N까지 정수
	static int K; //K번째 지워질 수 
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2960.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//방문처리 할 배열
		boolean [] visited = new boolean[1001];
		//시작값은 2로 초기화
		int P = 2;
		//없어진 숫자를 카운팅할 변수
		int cnt = 0;
		//곱해줄 수
		int idx = 1;
		//삭제할 수
		int C = 0;
		while(true) {
			C=P*idx;
			//N보다 커지면 P값을 증가시키고 idx를 1로 초기화 후 넘어감
			if(C>N) {
				P++;
				idx=1;
				continue;
			}
			//방문하지 않은 숫자라면
			if(!visited[C]) {
				//해당 숫자 방문처리
				visited[C] = true;
				//곱해줄 숫자 1 증가
				idx++;
				//제거된 숫자 카운팅 1 증가
				cnt++;
			//방문한 숫자라면 곱해줄 수 만 1 증가
			}else {
				idx++;
			}
			//카운팅 숫자가 K와 같으면 loop 탈출
			if(cnt==K) break;
		}
		System.out.println(C);
		br.close();
    }

}
