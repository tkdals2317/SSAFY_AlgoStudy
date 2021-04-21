import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_S2_14569_시간표짜기_정소영_solved {
	static int N, M;
	static boolean[][] student;
	static int[] result;
	static ArrayList<Integer>[] subjects;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		subjects = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			subjects[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				subjects[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		M = Integer.parseInt(br.readLine());
		student = new boolean[M][51];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			for (int j = 0; j < p; j++) {
				student[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		result = new int[M];
		// 입력 완료

		countSubject();

		br.close();
	}

	private static void countSubject() {
		int tmp = 0;
		for (int i = 0; i < N; i++) {	// 과목수만큼 돈다.
			int sublen = subjects[i].size();	// 해당 과목 시간 길이
			for (int s = 0; s < M; s++) {	// 학생들을 차례로 비교
				for (int j = 0; j < sublen; j++) { // 각 과목이 students에 있는지 확인
					tmp = subjects[i].get(j); 
					if (!student[s][tmp])
						break;
				}
				if (student[s][tmp])
					result[s]++;
			}
		}
		for (int i = 0; i < M; i++) {
			System.out.println(result[i]);
		}
	}

}
