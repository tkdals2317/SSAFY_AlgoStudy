package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_2075_N번째큰수 {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int n = Integer.parseInt(br.readLine());
		 
		 List<Integer> pq = new ArrayList<>();
		 for(int i=0; i<n; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 for(int j=0; j<n; j++)
				 pq.add(Integer.parseInt(st.nextToken()));
		 }
		 Collections.sort(pq,Collections.reverseOrder());
		 System.out.println(pq.get(n-1));
	}
}