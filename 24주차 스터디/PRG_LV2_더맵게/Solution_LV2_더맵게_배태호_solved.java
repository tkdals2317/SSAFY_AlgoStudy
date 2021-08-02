package PG;

import java.util.PriorityQueue;

public class Soulition_programmers_level2_더맵게 {

	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;

		//////////
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue();

		for (int i=0; i<scoville.length; i++) 
			pq.offer(scoville[i]);

		while (pq.peek() <= K) {
			if (pq.size() < 2) {
				answer=-1;
				break;
			}

			pq.offer(pq.poll() + (pq.poll() * 2));
			answer++;
		}
		System.out.println(answer);
	}

}
