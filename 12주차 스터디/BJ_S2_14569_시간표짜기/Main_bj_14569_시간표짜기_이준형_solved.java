import java.io.*;
import java.util.*;

public class Main_bj_14569_시간표짜기_구미_4_이준형 {

	static int N, M; // 수업수,학생수
	static ArrayList<Integer>[] clas; // 수업
	static ArrayList<Integer>[] student; // 학생

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 수업 입력
		N = Integer.parseInt(br.readLine());
		clas = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			clas[i] = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				clas[i].add(Integer.parseInt(st.nextToken()));
			}
		}
//		printclas();

		// 학생 입력
		M = Integer.parseInt(br.readLine());
		student = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			student[i] = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				student[i].add(Integer.parseInt(st.nextToken()));
			}
		}
//		printstudnet();

		// 학생 기준으로 찾기시작
		for (int i = 0; i < M; i++) {
			int ans = search_student(i);
			System.out.println(ans); // 학생 가능 수업 수 출력
		}

		br.close();
	}

	// 학생 기준으로 찾기 시작
	private static int search_student(int i) {
		int allcount = 0; // 가능한 수업수

		for (int go = 0; go < N; go++) {
			int count=0;
			int csize = clas[go].size();
			for(int k=0;k<csize;k++) {
				int tmp=clas[go].get(k);
				boolean ans = search_class(i,tmp);
				if (ans == true)
					count++;
			}
			if(count==csize)
				allcount++;
		}
		return allcount;

	}

	// 수업 기준으로 찾기 시작
	private static boolean search_class(int i,int tmp) {
		int size = student[i].size();
		for(int k=0;k<size;k++) {	//수업 돌면서 시간이있으면 true 반환
			if(student[i].get(k)==tmp) {
				return true;
			}
		}
		return false;
	}

	// 학생출력
	private static void printstudnet() {
		for (int i = 0; i < M; i++) {
			int k = student[i].size();
			for (int j = 0; j < k; j++) {
				System.out.print(student[i].get(j) + " ");
			}
			System.out.println();
		}
		System.out.println("-----------");
	}

	// 수업출력
	private static void printclas() {
		for (int i = 0; i < N; i++) {
			int k = clas[i].size();
			for (int j = 0; j < k; j++) {
				System.out.print(clas[i].get(j) + " ");
			}
			System.out.println();
		}
		System.out.println("-----------");
	}

}
