import java.util.*;
import java.io.*;
/**
 * 
 * (월요일 1~10교시: 1~10, 화요일 1~10교시: 11~20, …)
 */
public class Main_S2_14569_시간표짜기_이상민 {
	static int N, M; // N : 총 과목의 수, M:학생 수
	static ArrayList<Integer>[] subject;
	static ArrayList<Integer>[] student;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14569.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		subject = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			subject[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < k; j++) {
				subject[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		M = Integer.parseInt(br.readLine());
					
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			boolean [] checked = new boolean[51]; 
			for (int j = 0; j < k; j++) {
				checked[Integer.parseInt(st.nextToken())]=true;
			}
			int count = 0;
			for (int j = 0; j < N; j++) {
				int scount = 0;
				for (int x = 0; x < subject[j].size(); x++) {
					if(!checked[subject[j].get(x)]) continue;
					scount++;
				}
				if(scount==subject[j].size()) count++;
			}
			System.out.println(count);
		}
		br.close();
	}//end of main
	
}//end of class
