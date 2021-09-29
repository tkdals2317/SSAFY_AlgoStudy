import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(maxQ.size() == minQ.size()) {
				maxQ.add(num);
				
				if(!minQ.isEmpty() && maxQ.peek() > minQ.peek()) {
					minQ.add(maxQ.poll());
					maxQ.add(minQ.poll());
				}
			}
			else {
				minQ.offer(num);
				
				if(maxQ.peek() > minQ.peek()) {
					minQ.add(maxQ.poll());
					maxQ.add(minQ.poll());
				}
			}
			
			System.out.println(maxQ.peek());
		}
	}
}
