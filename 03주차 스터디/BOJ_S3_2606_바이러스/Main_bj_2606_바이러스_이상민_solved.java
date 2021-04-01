import java.io.FileInputStream;
import java.util.*;

public class Main_bj_2606_바이러스_이상민_solved {

	static int N;
	static int P;
	//첫번째 컴퓨터는 카운팅 하지 않으므로 -1로 초기화
	static int cnt=-1;
	//컴퓨터 별 연결된 컴퓨터를 저장할 어레이 리스트 배열
	static ArrayList<Integer> [] network;
	//해당 컴퓨터의 방문 여부를 체크하는 배열
	static boolean [] v;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2606.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		P = sc.nextInt();
		//인덱스 보기 편하기 위해 N+1만큼 생성
		network = new ArrayList[N+1];
		v = new boolean[N+1];
		//어레이리스트 배열 초기화
		for (int i = 1; i < network.length; i++) {
			network[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < P; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			//네트워크 양쪽에 둘 다 추가 해줌
			network[start].add(end);
			network[end].add(start);
		}
		System.out.println(Arrays.toString(network));
		dfs(1);
		System.out.println(cnt);
		sc.close();
	}
	static void dfs(int current) {
		//현재 컴퓨터 방문처리
		v[current] = true;
		//감염된 컴퓨터 수 증가
		cnt++;
		//어레이리스트의 사이즈 만큼 반복
		for (int i = 0; i < network[current].size(); i++) {
			//방문을 안한 컴퓨터에 대해서만 dfs 재귀
			if(!v[network[current].get(i)]) {
				dfs(network[current].get(i));
			}
		}
		return;
	}

}
