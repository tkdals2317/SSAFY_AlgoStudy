package PG;

import java.util.HashMap;

public class Soulition_programmers_level2_위장 {
	static int ans=1;
	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
//		String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
		/////////////////////////////////////////////////////
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<clothes.length; i++)
			map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 1)+1);
		map.forEach((key, value) -> {	ans*=value; });
		System.out.println(ans-1);
	}
}
