import java.util.*;
import java.io.*;
public class Main_B1_1157_단어공부_이상민 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//toUpperCase() : 문자열을 대문자 알파벳으로 변환하는 메소드
		//toCharArray() : String을 char형 Array로 변경하는 메소드 
		char [] word = br.readLine().toUpperCase().toCharArray();
		
		//A는 아스키 코드상 65 a는 아스키코드상 97
		
		//알파벳(대소문자 구별 X)의 개수는 26개
		int [] alphaArr = new int[26];
		//해당글자의 65를 빼면 숫자가 나오는데 이 숫자를 배열의 인덱스로 사용
		for(int i = 0; i < word.length; i++) {
			//알파벳 갯수 카운트
			++alphaArr[word[i]-65];
		}
		int max = 0;
		int sol = 0;
		//전체 알파벳 26글자를 검사
		for(int i = 0; i < alphaArr.length; i++) {
			//가장 큰 알파벳 갯수 갱신
			if(max<alphaArr[i]) {
				max = alphaArr[i];
				//해당 인덱스를 저장
				sol = i;
			//만약 이전의 max값과 동일하다면 max개인 알파벳이 한 개 이상
			}else if(max==alphaArr[i]) {
				//63번째 아스키 코드가 ?
				sol = -2;
			}
		}
		System.out.println((char)(sol+65));
		br.close();
	}
}
