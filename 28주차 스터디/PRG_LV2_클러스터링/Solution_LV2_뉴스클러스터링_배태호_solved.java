package PG;

import java.util.*;

public class Soulition_programmers_level2_뉴스클러스터링 {

	public static void main(String[] args) {
		String str1 = "handshake";
		String str2 = "	shake hands";
		/////////////////////////////////////////////////////
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		
		ArrayList<String> list1 = new ArrayList<>(); //str1 -> 
		ArrayList<String> list2 = new ArrayList<>(); //str2 ->

		ArrayList<String> union = new ArrayList<>();
		ArrayList<String> intersection = new ArrayList<>();

		for(int i = 0; i < str1.length()-1; i++) {
			char a = str1.charAt(i);
			char b = str1.charAt(i+1);

			if(Character.isLetter(a) && Character.isLetter(b))
				list1.add(Character.toString(a) + Character.toString(b));
		}

		for(int i = 0; i < str2.length()-1; i++) {
			char a = str2.charAt(i);
			char b = str2.charAt(i+1);

			if(Character.isLetter(a) && Character.isLetter(b))
				list2.add(Character.toString(a) + Character.toString(b));
        }


		for(String s : list1) { // 교집합
			if(list2.remove(s))
				intersection.add(s);
			union.add(s);
		}
		
		for(String s : list2) // 합집합
			union.add(s);

   		double answer = 0;
	
		if(union.size() == 0) answer = 1;
		else answer = (double) intersection.size() / (double) union.size();
		System.out.println(answer);
//		return (int) (answer * 65536);
		
	}

}
