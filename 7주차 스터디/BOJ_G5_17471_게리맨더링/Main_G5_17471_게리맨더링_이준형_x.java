import java.io.*;
import java.util.*;

public class Main_bj_17471_게리맨더링_구미_4_이준형 {

	static int N;
	static int min_cha;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> list1 = new ArrayList<>(); // 도시 이어진 정보 저장할 에레이 리스트
		ArrayList<Integer> list2 = new ArrayList<>();

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] people = new int[N + 1]; // 도시별 인구수저장
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

//		for(int i=1;i<=N;i++) {
//			System.out.print(people[i]);
//		}System.out.println();

		
		int count = 1; // 사용하는 리스트 개수
		for (int k = 1; k <= N; k++) {
			st = new StringTokenizer(br.readLine());
			int city = Integer.parseInt(st.nextToken());
			if (city != 0) { // 이어진 곳이 없지 않으면 시도
				// 현재 도시가 존재하는지 체크
				int k_flag = 0; // 현재 도시 존재 체크용
				if (count == 1) {
					if (list1.isEmpty()) { // 비었으면 현재 도시추가
						list1.add(k);
					}
					for (int j = 0; j < list1.size(); j++) {
						if (list1.get(j) == k)
							k_flag = 1;
					}
				} else if (count == 2) {
					if (list2.isEmpty()) { // 비었으면 현재 도시추가
						list2.add(k);
					}
					for (int j = 0; j < list2.size(); j++) {
						if (list2.get(j) == k)
							k_flag = 1;
					}
				} else // 도시가 3개 이상일경우
					break;
				if (k_flag == 0) { // 현재 도시 존재 x
					count++; // 리스트 개수 증가
				}

				// 항목 넣는거 체크
				if (count == 1) { // 구역이 1개인경우
					for (int i = 0; i < city; i++) {
						int num = Integer.parseInt(st.nextToken());
						int flag = 0; // 항목체크용
						// 리스트 돌아서 항목이 없으면 추가
						for (int j = 0; j < list1.size(); j++) {
							if (list1.get(j) == num)
								flag = 1;
						}
						if (flag == 0)
							list1.add(num); // 도시 항목에 추가
					}
				} else if (count == 2) { // 구역이 2개인경우
					for (int i = 0; i < city; i++) {
						int num = Integer.parseInt(st.nextToken());
						int flag = 0; // 항목체크용
						// 리스트 돌아서 항목이 없으면 추가
						for (int j = 0; j < list2.size(); j++) {
							if (list2.get(j) == num)
								flag = 1;
						}
						if (flag == 0)
							list2.add(num); // 도시 항목에 추가
					}
				} else { // 구역이 3개이상이면 중지
					break;
				}
			}
		}

//		System.out.println(list1.toString());
//		System.out.println(list2.toString());
//		System.out.println(count);

		// 남은거 계산처리
		if (count >= 3 || list1.isEmpty()) { // 도시가 3개 이상 이거나 아예 없거나 1개 있는거도 해야하는지?? 아마 하는듯
			System.out.println(-1);
		} else if (count == 2) { // 도시가 2개 일경우 각 도시의 합의 차가 답
			int list1_count = 0;
			for (int j = 0; j < list1.size(); j++) {
				list1_count += list1.get(j);
			}
			int list2_count = 0;
			for (int j = 0; j < list2.size(); j++) {
				list2_count += list2.get(j);
			}
			int ans = Math.abs(list1_count - list2_count);
			System.out.println(ans);
		} else { // 도시가 1개 인경우 도시 안에서 조합을 통해 최소의 차이 구해야함
			min_cha = Integer.MAX_VALUE; // 최소차이 값 구하기 위한 초기화
			combi(1, 0, 0, people);
			System.out.println(min_cha);
		}

		br.close();
	}

	//최소차이 구하기 조합
	private static void combi(int idx, int count1, int count2, int[] people) {
		if (idx >= N + 1) {
			// 계산후에 최소값과 비교
			int ans = Math.abs(count1 - count2);
			min_cha = Math.min(ans, min_cha);
			return;
		}
		combi(idx + 1, count1 + people[idx], count2, people); // 앞에배치
		combi(idx + 1, count1, count2 + people[idx], people); // 뒤에배치

	}

}
