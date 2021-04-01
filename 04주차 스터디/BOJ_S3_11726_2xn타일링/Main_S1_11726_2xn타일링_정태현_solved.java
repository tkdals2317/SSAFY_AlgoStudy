package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main_S1_11726_solved {
    public static void main(String args[]) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(bf.readLine());

        int[] sum = new int[n+1];
        
        sum[0] = 0;
        sum[1] = 1;
        sum[2] = 2;
        for(int i=3; i<=n; i++) {
        	sum[i] = (sum[i-1] + sum[i-2]) % 10007;
        }
        
        System.out.println(sum[n]);
    };
}