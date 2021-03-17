package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S1_7571 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int ans = 0;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] forX = new int[M]; //X축이나 Y축으로는 많이 가봤자 M만큼만
        int[] forY = new int[M];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            forX[i] = Integer.parseInt(st.nextToken());
            forY[i] = Integer.parseInt(st.nextToken());
        }
	}
}
