package PG;

import java.util.*;

public class Soulition_programmers_level2_로또의최고순위와최저순위 {
	
	public static void main(String[] args) {

		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};

		/////////////////////////////////////////////////////
		HashMap<Integer, Integer> map = new HashMap<>();
		int cnt =0;
		int zcnt=0;
		int[] answer = new int [2];
		for(int i=0; i<win_nums.length; i++) 
			map.put(win_nums[i], win_nums[i]);
		
		for(int i=0; i<lottos.length; i++) {
			if(lottos[i]==0) zcnt++;
			else if(map.containsValue(lottos[i])) cnt++;
		}
		answer[0]= 7-cnt-zcnt;
		answer[1]= 7-cnt;
		if(answer[0]==7) answer[0]=6;
		if(answer[1]==7) answer[1]=6;
//		return answer;
	}
}
