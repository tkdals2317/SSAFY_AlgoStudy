package Algorithm;

import java.util.*;

public class Soulition_programmers_level2_프린터 {

	public static void main(String[] args) {

		int location = 0;
		int[] priorities = { 1, 1, 9, 1, 1, 1 };

		/////////////////////////////////////////////////////

		int answer=1;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (Integer integer : priorities) {
			pq.add(integer);
		}
		
		while(!pq.isEmpty()) {
			for(int i=0; i<priorities.length; i++) {
				if(pq.peek()==priorities[i]) 
					if(i==location) {
						System.out.println(answer);
						System.exit(0);
					}
					pq.poll();
					answer++;				
			}
		}
		System.out.println(answer);;
	}
}
