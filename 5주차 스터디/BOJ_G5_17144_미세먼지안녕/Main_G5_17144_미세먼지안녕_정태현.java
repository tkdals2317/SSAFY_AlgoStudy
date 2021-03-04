package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main_G5_17144_미세먼지안녕_정태현_solved {
	static int R, C, T;
	static int[] di = {0,0,1,-1};
	static int[] dj = {1,-1,0,0};
	static int[][] arr;
    public static void main(String args[]) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        arr = new int[R][C];
        
        for (int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == -1) {
					
				}
			}
			
		}
    };
}