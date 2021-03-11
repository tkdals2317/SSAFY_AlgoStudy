package algorithm.boj;

import java.io.*;
import java.util.*;

public class Main_G4_17135_캐슬디펜스_정태현_X {
	static int stoi(String str) {
		return Integer.parseInt(str);
	};

	static int N, M, D, kill = Integer.MIN_VALUE;
	static ArrayList<Location> animes = new ArrayList<Location>();
	static ArrayList<Location> copyAnimes;
	
	static ArrayList<Location> armies = new ArrayList<Location>();
	static class Location implements Comparable<Location>{
		int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
//���� �Ÿ��� �ִ� �� �� ���ʿ� �ִ� ������ ó���ϱ����� ����
		@Override
		public int compareTo(Location loc) {
			int diff = this.y - loc.y;
			return diff != 0 ? diff : loc.x - this.x;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				int temp = stoi(st.nextToken());
				if (temp == 1) {
					animes.add(new Location(i, j));
				}
			}
		}
		
//		���ʿ� �ִ� ������ ó���ϱ����� ����
		Collections.sort(animes);	
		comb(new boolean[M], 0, M, 3);
		System.out.println(kill);
	}

//	�ü� 3���� ��ġ ����
	static void comb(boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			ArrayList<Location> hunters = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (visited[i])
					hunters.add(new Location(N + 1, i + 1));
			}
//			animes ����
			copy();
			simulation(hunters);
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			comb(visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	static void simulation(ArrayList<Location> hunters) {
		int curKill = 0;
		while (copyAnimes.size() != 0) {
//			�ü� ��
			Set<Integer> animeLocs = new HashSet<>();
			for (int i = 0; i < 3; i++) {
				int dis = Integer.MAX_VALUE;
				int animeLoc = -1;
				Location hunter = hunters.get(i);
				
				for (int j = 0; j < copyAnimes.size(); j++) {
					int temp = calcDis(copyAnimes.get(j), hunter);	
					if (temp <= D) {
						if(temp < dis) {
							dis = temp;
							animeLoc = j;	
						}
					}
				}	
				
				if(animeLoc != -1) {
					animeLocs.add(animeLoc);
				}				
			}
			
			LinkedList<Integer> animeLocsList = new LinkedList<Integer>(animeLocs);
            Collections.sort(animeLocsList);
//			�� ����
			for(int i = 0; i < animeLocsList.size(); i++) {
				int target = animeLocsList.get(i);
				if(i == 0) {
					copyAnimes.remove(target);
				} else {
					copyAnimes.remove(target - i);
				}
				curKill++;
			}
			
			animeLocs = new HashSet<>();
//			�� ��
			for (int j = 0; j < copyAnimes.size(); j++) {
				copyAnimes.get(j).x++;
				if(copyAnimes.get(j).x > N) {
					animeLocs.add(j);
				}
			}
			
			animeLocsList = new LinkedList<Integer>(animeLocs);
            Collections.sort(animeLocsList);
			
//			�� ����
			for(int i = 0; i < animeLocsList.size(); i++) {
				int target = animeLocsList.get(i);
				if(i == 0) {
					copyAnimes.remove(target);
				} else {
					copyAnimes.remove(target - i);
				}
			}
		}	
		kill = Math.max(curKill, kill);
	}

	
	static void copy() {
		copyAnimes = new ArrayList<Location>();
		for(int i = 0; i < animes.size(); i++) {
			Location temp = new Location(animes.get(i).x, animes.get(i).y);
			copyAnimes.add(temp);
		}
	}
	
	static int calcDis(Location a, Location b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}