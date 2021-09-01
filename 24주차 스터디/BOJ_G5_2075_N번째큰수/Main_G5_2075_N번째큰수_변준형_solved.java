import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G5_2075_N번째큰수_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>(Collections.reverseOrder());
		int N=Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
		}
		
		for(int i=0; i<N-1; i++) {
			pq.poll();
		}
		
		System.out.println(pq.poll());
	}

}