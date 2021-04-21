package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_S2_14569_시간표짜기_정태현_solved {
	static int N, M;
	static boolean[][] timetable;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); //과목수
		ArrayList<Integer>[] subj = new ArrayList[N]; //과목
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			subj[i] = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				subj[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		M = Integer.parseInt(br.readLine()); //학생수
		ArrayList<Integer>[] std = new ArrayList[M]; //학생
		timetable = new boolean[M][51];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			std[i] = new ArrayList<>();
			for (int j = 0; j < p; j++) {
				int a = Integer.parseInt(st.nextToken());
				std[i].add(a);
				timetable[i][a] = true;
			}
		}
		
		for (int i = 0; i < M; i++) { //학생
			int cnt = 0;
			for (int j = 0; j < N; j++) { //과목
				if(find(subj, i, j)) { //다 있네
					cnt++;
				};
			}
			System.out.println(cnt);
		}
		
	}
	private static boolean find(ArrayList<Integer>[] subj, int stdnum, int idx) {
		for (int i = 0; i < subj[idx].size(); i++) {
			if(!timetable[stdnum][subj[idx].get(i)]) {
				return false;
			}
		}
		return true;
	}
	
	
}
