package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_16236_아기상어_정태현 {
	static int n;
	static int[][] map;
    static boolean[][] visited;
    static int[][] tmp_map;
    static int s_x, s_y;
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        tmp_map = new int[n][n];
        visited = new boolean[n][n];
        
        for(int i=0; i<n; i++) {
            String[] str = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                
                if(map[i][j] == 9) {
                    s_x = i;
                    s_y = j;
                }
            }
        }
        
        solve(); 
	}
	private static void solve() {
		// TODO Auto-generated method stub
		
	}
}
