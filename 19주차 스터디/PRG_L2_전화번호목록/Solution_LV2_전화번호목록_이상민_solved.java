import java.io.*;
import java.util.*;
class Solution_LV2_전화번호목록_이상민_solved {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for(int i = 0; i<phone_book.length-1; i++){
        	if(phone_book[i+1].startsWith(phone_book[i])){
                answer = false;
                break;
            }
        }
        /*
		String check ="안녕 하이하이";
		
		System.out.println(check.startsWith("안녕"));	//true
		System.out.println(check.startsWith("안녕 "));//true 공백인식
		System.out.println(check.startsWith("하이"));	//false
		
		System.out.println(check.endsWith("하이")); 	//true
		System.out.println(check.endsWith(" 하이하이"));//true 공백인식
		System.out.println(check.endsWith("녕 하"));	//false
		*/
        return answer;
    }
}