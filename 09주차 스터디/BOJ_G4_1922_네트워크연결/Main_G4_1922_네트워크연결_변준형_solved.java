import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G4_1922_네트워크연결_변준형_solved {
	static int[] parent; // 부모 노드 정보를 저장할 배열
	// root 노드를 반환하는 메소드

	static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	} // 두개의 집합을 합치는 메소드. union 을 하는 경우 true 를 반환

	static boolean union(int x, int y) {
		int parentX = find(x);
		int parentY = find(y);
		if (parentX != parentY) {// 부모가 다른경우
			parent[parentY] = parent[parentX]; 
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {// 필요 변수 선언
		int N = 0; // 컴퓨터의 수
		int M = 0; // 연결할 수 있는 선의 수
		int sum = 0; // 연결할 수 있는 최소 비용의 합
		// 입력값 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		// 거리(비용) 오름차순으로 정렬하기 위한 우선순위 큐 선언
		PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				return o1.distance - o2.distance;
			}
		});
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int tempFrom = Integer.parseInt(st.nextToken());
			int tempTo = Integer.parseInt(st.nextToken());
			int tempDistance = Integer.parseInt(st.nextToken());
			pq.add(new Info(tempFrom, tempTo, tempDistance));
		}

		// parent 배열 초기화
		parent = new int[N + 1]; // 컴퓨터의 개수보다 한개 더 크게 배열 생성 (1 베이스로 할꺼기 때문)
		for (int i = 1; i <= N; i++) {
			parent[i] = i; // parent 배열의 값을 인덱스와 동일하게 초기화
		}

		// 메인 로직 시작
		// pq 가 빌 때 까지 반복하고, union 을 하는 경우 비용을 결과값에 합산
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			if (union(info.from, info.to)) {
				sum += info.distance;
			}
		}
		System.out.println(sum); // 최종 합계 출력
	}

	// 시작점, 종료점, 거리(비용) 을 저장하기 위한 클래스
	static class Info {
		int from;
		int to;
		int distance;

		Info(int from, int to, int distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
	}
}
