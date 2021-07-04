package programmers;

import java.util.HashMap;
import java.util.Map.Entry;

public class Soulition_programmers_level2_소수찾기 {
	static int answer = 0;
	static char[] n;
	static boolean[] visited;
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		String numbers = "17";
		/////////////////////////////////////////////////////
		n = new char[numbers.length()];
		visited = new boolean[numbers.length()];

		for (int i = 0; i < numbers.length(); i++)
			n[i] = numbers.charAt(i);
		per(0, "");
		
		for (Entry<Integer, Integer> entrySet : map.entrySet()) 
			isvalid(entrySet.getValue());
		
		System.out.println(answer);
	}

	private static void per(int dep, String num) {
		if (dep != 0) map.put(Integer.parseInt(num), Integer.parseInt(num));
		if (dep == n.length)
			return;

		for (int i = 0; i < n.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				per(dep + 1, num + n[i]);
				visited[i] = false;
			}
		}
	}

	private static void isvalid(int num) {
		if (num == 0) return;
		if (num == 1) return;

		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0)
				return;

//		System.out.println(num);
		answer++;
	}

}
