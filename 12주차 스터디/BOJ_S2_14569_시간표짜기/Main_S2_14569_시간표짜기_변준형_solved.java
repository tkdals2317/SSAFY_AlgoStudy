import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_14569_시간표짜기_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 과목수
		long[] ary = new long[N + 1];
		for (int n = 0; n < N; n++) { // 과목수 만큼 반복
			// 4 1 2 3 4
			// 6 5 6 7 8 9 10
			// 4 11 21 31 41
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken()); // 수업시간 갯수 입력
			for (int i = 0; i < k; i++) {
				int t = Integer.parseInt(st.nextToken()); // 수업시간 갯수 만큼 수업시간 입력
				if (i == 0)
					ary[n] = 0 | (1L << (t - 1));
				else
					ary[n] = ary[n] | (1L << (t - 1)); // 1 int형이라 <<32 부터 에러남
				// 1111
				// 1111110000
				// 10000000001000000000100000000010000000000
			}
		}
		int M = Integer.parseInt(br.readLine()); // 학생수
		long[] stu = new long[M + 1];
		for (int m = 0; m < M; m++) { // 학생수 만큼 반복
			// 8 1 2 3 4 5 6 7 8
			// 7 1 2 3 7 8 9 10
			// 14 1 2 3 4 5 6 7 8 9 10 11 21 31 41
			// 5 41 42 43 44 45
			// 10 1 5 6 7 8 9 10 11 21 31
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // 수업시간 갯수 입력
			for (int i = 0; i < p; i++) {
				int t = Integer.parseInt(st.nextToken()); // 수업시간 갯수 만큼 수업시간 입력
				if (i == 0)
					stu[m] = 0 | (1L << (t - 1));
				else
					stu[m] = stu[m] | (1L << (t - 1));
				// 11111111
				// 1111000111
				// 10000000001000000000100000000011111111111
				// 111110000000000000000000000000000000000000000
				// 1000000000100000000011111110001
			}
		}
		int cnt = 0;
		for (int m = 0; m < M; m++) {
			for (int n = 0; n < N; n++) {
				// if((stu[m]&ary[n])==(ary[n])) // 학생 과 과목을 & 연산해서 기존 과목과 가능하면 수강 가능
				// bitcount를 이용하여 학생 과 과목을 xor연산한 값의 1의 개수를 구하고 기존 과목의 1의 합과 더한후  학생의 1의값이 같다면 수강 가능
				if (Long.bitCount(stu[m] ^ ary[n]) + Long.bitCount(ary[n]) == Long.bitCount(stu[m]))
					cnt++;
			}
			System.out.println(cnt);
			cnt = 0;
		}
	}
}
