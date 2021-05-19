package list;

import java.util.*;

public class 링크드리스트 {
	
	public static void main(String[] args) {
		
		//선언
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		//추가
		list.add(0, 10);	//넣을 위치, 데이터
		for(int i=11;i<20;i++) {
			list.add(i); //가장 끝에 넣어짐
		}
		
		//값 참조
		System.out.println("0 위치의 값:"+list.get(0));	//참조위치

		//값 제거
		list.remove(5);	//제거 할 위치
		list.remove();	//가장 첫번째 데이터 제거
		
		//사이즈 확인
		System.out.println("사이즈: "+list.size());
		
		//리스트 출력
		System.out.println("리스트출력:"+list.toString());
		
		//데이터 존재 여부 확인
		System.out.println("16을 포함하고 있는지?"+list.contains(16));
		
		//데이터의 위치 확인(없으면 -1 있으면 그중에서 가장 앞의 위치)
		System.out.println("16의 위치는?"+list.indexOf(16));
		
		//리스트 비우기
		list.clear();
	
	}
}
