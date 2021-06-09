import java.util.Scanner;

public class Main_G4_1719_택배_변준형_solved {
	static int MAX = 9999;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt(); // 집하장 수
		int path = sc.nextInt(); // 경로 수
		int box[][] = new int[cnt + 1][cnt + 1];
		int weights[][] = new int[cnt + 1][cnt + 1];
		// 입력값 대입
		for (int i = 0; i < path; i++) {
			int s = sc.nextInt(); // 시작 지점
			int e = sc.nextInt(); // 도착 지점
			int w = sc.nextInt(); // 가중치
			box[s][e] = w;
			box[e][s] = w;
		}
		//	경로 설정
		for (int i = 1; i <= cnt; i++) {
			for (int j = 1; j <= cnt; j++) {
				if (i == j)
					continue;
				weights[i][j] = j;
				if(box[i][j] == 0)
					box[i][j] = MAX;
			}
		}
		//	최적 경로 찾기
		for (int i = 1; i <= cnt; i++) {			//	거처 가는 지점
			for (int j = 1; j <= cnt; j++) {		//	시작점
				for (int k = 1; k <= cnt; k++) {	//	도착 점
					if(j==k)
						continue;
					if(box[j][k] > box[j][i]+box[i][k]) {
						box[j][k] = box[j][i]+box[i][k];
						weights[j][k] = weights[j][i];
					}
				}
			}
		}
		
		for (int i = 1; i <= cnt; i++) {
			for (int j = 1; j <= cnt; j++) {
				if(i==j)
					System.out.print("- ");
				else
					System.out.print(weights[i][j]+" ");
				if(j==cnt)
					System.out.println();
			}
		}
	}
}
