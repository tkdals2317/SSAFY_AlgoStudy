package Algorithm;

import java.util.*;

public class Soulition_programmers_level2_메뉴리뉴얼 {
	static HashMap<String, Integer> map;
	static int max;

	public static void main(String[] args) {

		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };
		/////////////////////////////////////////////////////
		PriorityQueue<String> pq = new PriorityQueue<>();
		for (int i = 0; i < course.length; i++) { // 입력받은 코스 배열 개수만큼
			map = new HashMap<>();
			max = 0; // 제일 많이 주문 받은 메뉴 숫자
			for (int j = 0; j < orders.length; j++) // 조합으로 전체 주문 내역 map에 저장
				combiantion(0, "", course[i], 0, orders[j]);
			
			for (String s : map.keySet()) // map의 key 집함을 가져와 
				if (map.get(s) == max && max > 1) // key에 해당하는 값이 제일 많이 주문된 메뉴와 같으고 1보다 크면 큐에 넣음
					pq.offer(s);
		}
		
		String ans[] = new String[pq.size()];
		int k = 0;
		while (!pq.isEmpty())  // pq에서 답 출력
			ans[k++] = pq.poll();
		
		for (String s : ans) 
			System.out.print(s+ " ");
		
	}

	static void combiantion(int dep, String str, int couseNum, int start, String word) {
		if (dep == couseNum) { //course 수 만큼 메뉴를 생성했으면
			String menu = "";
			char[] temp = str.toCharArray(); //만들어 둔 메뉴 정렬
			Arrays.sort(temp);
			
			for (int i = 0; i < temp.length; i++) // 정렬 시킨 뒤 다시 string으로 합침
				menu += temp[i];
			
			map.put(menu, map.getOrDefault(menu, 0) + 1); // 메뉴의 횟수를 증가
			max = Math.max(max, map.get(menu)); // 제일 많이 주문 받은 메뉴 숫자 저장
			return;
		}
		
		for (int i = start; i < word.length(); i++) // 조합으로 메뉴생성
			combiantion(dep + 1, str + word.charAt(i), couseNum, i + 1, word);
		
	}
}
