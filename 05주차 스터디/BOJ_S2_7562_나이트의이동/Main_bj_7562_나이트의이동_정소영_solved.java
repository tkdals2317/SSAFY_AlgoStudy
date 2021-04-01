import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/7562
class Pair{
	int x;
	int y;
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}

}
public class Main_bj_7562_나이트의이동_정소영_solved {
	static int l, nx, ny, rx, ry;
	static int[][] map;
	static int[] dx = {1,2,2,1,-1,-2,-2,-1};
	static int[] dy = {-2,-1,1,2,2,1,-1,-2};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			l = sc.nextInt();
			nx = sc.nextInt();
			ny = sc.nextInt();
			rx = sc.nextInt();
			ry = sc.nextInt();
			map = new int[l][l];
			
			bfs(nx, ny);
//			for (int j = 0; j < l; j++) {
//				for (int j2 = 0; j2 < l; j2++) {
//					System.out.print(map[j][j2]+" ");
//				}
//				System.out.println();
//			}
		}
	}
	private static void bfs(int x, int y) {
		if(x==rx && y==ry) {
			System.out.println(0);
			return;
		}
		
		Queue<Pair> q = new LinkedList<Pair>();
		Pair temp = new Pair(x, y);
		Pair next = new Pair(0, 0);
		q.offer(temp);
		map[x][y] = 1;

		while(!q.isEmpty()) {
			temp = q.poll();
			
			for (int i = 0; i < 8; i++) {
				next.setX(temp.x+dx[i]);
				next.setY(temp.y+dy[i]);
				
				if(next.x==rx && next.y==ry) {
					System.out.println(map[temp.x][temp.y]);
					return;
				}
				if(next.x>=0 && next.x<l && next.y>=0 && next.y<l && map[next.x][next.y]==0) {
					q.add(new Pair(next.x, next.y));
					map[next.x][next.y] = map[temp.x][temp.y]+1;
				}
				
			}
		}
	}

}


