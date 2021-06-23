import java.io.*;
import java.util.*;

class Solution_LV2_전화번호목록_변준형_solved {
	public boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for (int i = 0; i < phone_book.length - 1; i++) {
			if (phone_book[i + 1].startsWith(phone_book[i])) {	//	startsWith() 함수는 대상 문자열이 특정 문자 또는 문자열로 시작하는지 체크하는 함수이다.
				answer = false;									//	해당 문자열로 시작되는지 여부를 확인하고 boolean 에 맞춰 true/false 값을 리턴한다.
				break;
			}
		}
		return answer;
	}
}