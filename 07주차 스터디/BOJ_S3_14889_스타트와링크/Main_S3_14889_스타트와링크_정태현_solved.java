package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_14889_스타트와링크_정태현_solved {
	static int N;
	static boolean[] isSelected; //뽑힌팀, 안뽑힌팀 모두 저장용
	static int[][] arr;
	static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        isSelected = new boolean[N]; //조합으로 뽑기용
        min = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        dfs(0, 0);
        System.out.println(min);
    }
	private static void dfs(int s, int cnt) {
		if(cnt == N/2) {
			min = Math.min(min, check());
			return;
		} 
		
		for (int i = s; i < N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				dfs(i+1, cnt+1);
				isSelected[i] = false;
			}
		}
	}
	private static int check() {
		int start = 0;
		int end = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isSelected[i] && isSelected[j]) {
					start += arr[i][j];
				}
				
				if(!isSelected[i] && !isSelected[j]) {
					end += arr[i][j];
				}
			}
		}
		
		if(start >= end) return start - end; 
		else return end - start;
	}
}
