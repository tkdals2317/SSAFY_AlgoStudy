import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SW역량_5648_원자소멸시뮬레이션_변준형_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int[][] ary = new int[42][42]; // 맵(2002로 하면 두 원소의 거리가 홀수일때 해결 불가능)
		for (int testcase = 1; testcase <= TC; testcase++) {
			int atom_cnt = Integer.parseInt(br.readLine());
			int[][] atom = new int[atom_cnt][4]; // 원자 속성 (x좌표, y좌표, 방향, 에너지)
			for (int i = 0; i < atom_cnt; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 10)*2; // x좌표

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SW역량_5648_원자소멸시뮬레이션_변준형_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int[][] ary = new int[4002][4002]; // 맵(2002로 하면 두 원소의 거리가 홀수일때 해결 불가능)
		for (int testcase = 1; testcase <= TC; testcase++) {
			int atom_cnt = Integer.parseInt(br.readLine());
			int[][] atom = new int[atom_cnt][4]; // 원자 속성 (x좌표, y좌표, 방향, 에너지)
			for (int i = 0; i < atom_cnt; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000)*2; // x좌표
				int y = (Integer.parseInt(st.nextToken()) + 1000)*2; // y좌표
				int d = Integer.parseInt(st.nextToken()); // 방향
				int e = Integer.parseInt(st.nextToken()); // 에너지
				atom[i] = new int[] { x, y, d, e };
				ary[x][y] = 1;
			}
			int result = 0;
			boolean[][] visit = new boolean[4002][4002];
			for (int time = 0; time < 4002 && atom_cnt > 0; time++) {
				for (int i = atom_cnt - 1; i >= 0; i--) {
					int x = atom[i][0];
					int y = atom[i][1];
					ary[x][y]--;
					//System.out.println(Arrays.toString(atom[i]));
					switch (atom[i][2]) {
					case 0:
						if (y == 4000) {
							atom[i] = atom[atom_cnt - 1];
							atom_cnt--;
							break;
						}
						atom[i][1]++;
						y++;
						ary[x][y]++;
						break;
					case 1:
						if (y == 0) {
							atom[i] = atom[atom_cnt - 1];
							atom_cnt--;
							break;
						}
						atom[i][1]--;
						y--;
						ary[x][y]++;
						break;
					case 2:
						if (x == 0) {
							atom[i] = atom[atom_cnt - 1];
							atom_cnt--;
							break;
						}
						atom[i][0]--;
						x--;
						ary[x][y]++;
						break;
					case 3:
						if (x == 4000) {
							atom[i] = atom[atom_cnt - 1];
							atom_cnt--;
							break;
						}
						atom[i][0]++;
						x++;
						ary[x][y]++;
						break;
					}
					if (ary[x][y] > 1) {
						visit[x][y] = true;
					}
				}// 충돌하면 map이 개수가 되고
				//System.out.println("없어짐");
				for(int i =atom_cnt-1; i>=0; i--) {
					int x = atom[i][0];
					int y = atom[i][1];
					if(visit[x][y]) {
						ary[x][y]-=1;
						if(ary[x][y]==0) {
							visit[x][y] = false;
						}
						System.out.println(atom[i]);
						result+=atom[i][3];
						atom[i] = atom[atom_cnt-1];
						atom_cnt--;
					}
				}
				//System.out.println(atom_cnt+"남은거");
			}
			System.out.println("#"+testcase+" " + result);
		}
	}
}
