package programmers;

import java.util.*;

public class Soulition_programmers_level2_프린터 {

	public static void main(String[] args) {

		int location = 0; // 요청한 문서의 현재 위치
		int[] priorities = { 1, 1, 9, 1, 1, 1 };// 문서의 중요도

		/////////////////////////////////////////////////////

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int answer = 1;

		for (int p : priorities)
			pq.add(p);

		while (true) 
			for (int i = 0; i < priorities.length; i++) 
				if (priorities[i] == pq.peek()) {
					if (i == location) {
						System.out.println(answer);
						return;
					}
					answer++;
					pq.poll();
				}
	}
}
