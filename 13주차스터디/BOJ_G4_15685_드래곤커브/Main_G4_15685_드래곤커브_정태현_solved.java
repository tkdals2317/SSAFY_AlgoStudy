package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_15685_드래곤커브_정태현_solved {
	static int[] dy = {0, -1, 0, 1}; //우상좌하
	static int[] dx = {1, 0, -1, 0};
	static int cos = 0, sin = 1; //cos 90, sin 90
	static int[][] map;
	static ArrayList<Pos> clist; //커브리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[101][101];
		clist = new ArrayList<>();
		int N = Integer.parseInt(br.readLine()); //드래곤 커브 개수
		Pos[] curve = new Pos[N]; //드래곤 커브 정보 저장
		int x, y, d, g;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			//use "map[y][x]"
			x = Integer.parseInt(st.nextToken()); //Col
			y = Integer.parseInt(st.nextToken()); //Row
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			curve[i] = new Pos(x, y, d, g);
		}
		
		for (int i = 0; i < N; i++) {
			int ty = curve[i].y;
			int tx = curve[i].x;
			//해당 좌표 1, 방향에 해당하는 한칸 1로 0세대 init
			map[ty][tx] = 1;
			map[ty+dy[curve[i].d]][tx+dx[curve[i].d]] = 1;
			//변환하기 위해 0세대는 리스트에 넣는다
			clist.add(new Pos(tx, ty));
			clist.add(new Pos(tx+dx[curve[i].d], ty+dy[curve[i].d]));
			//첫 기준점은 방향으로 뻗어나간 한칸 그 위치
			process(curve[i], ty+dy[curve[i].d], tx+dx[curve[i].d]);
			clist.clear();
		}
		
		System.out.println(squareCheck());
	}
	private static int squareCheck() {
		int[] xC = {1,0,1}; //상,우,대각 체크
	    int[] yC = {0,1,1};
	    int result = 0;
	    int squarecnt = 0;
	    for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]==1) { //1이면 상,우,대각 체크로 사각형 확인
					for (int k = 0; k < 3; k++) {
						int ni = i + yC[k];
						int nj = j + xC[k];
						
						if(ni>=0 && nj>=0 && ni<101 && nj<101 && map[ni][nj]==1) { //범위 내부 & 
							result++;
						}
					}
					if(result == 3) squarecnt++;
					
					result = 0;
				}
			}
		}
		
	    return squarecnt;
	}
	private static void process(Pos curve, int y, int x) {
		int ty, tx, ansx, ansy, nx, ny;
		//x, y는 기준점
		for (int i = 1; i <= curve.g; i++) {
			int size = clist.size();
			//기준점과 돌릴점을 리스트에 넣는다
			for (int j = 0; j < size; j++) {
				Pos cur = clist.get(j);
				//원점 기준으로 회전 변환 행렬을 사용하기 위해
				nx = cur.x - x;
				ny = cur.y - y;
				//원점 기준으로 90도 회전 변환
				ansx = nx*cos - ny*sin;
				ansy= nx*sin + ny*cos;
				//다시 원점 기준에서 기준점 기준으로 변환시키기 위해 +x, +y
				if(map[ansy+y][ansx+x]==0) {
				} //아님
				map[ansy+y][ansx+x] = 1;
				clist.add(new Pos(ansx+x, ansy+y));					
			}
			//기준점 갱신, 기준점은 첫점을 기준점으로 돌린점이 된다
			tx = (curve.x - x)*cos - (curve.y - y)*sin;
			ty = (curve.x - x)*sin + (curve.y - y)*cos;
			x = tx + x;
			y = ty + y;
		}
	}
	static class Pos {
		int x, y;
		int d; //방향
		int g; //세대
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pos(int x, int y, int d, int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
}
