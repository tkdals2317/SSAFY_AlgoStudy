import java.io.*;
import java.util.*;

public class prg_전화번호목록_Lv2 {

	static public boolean solution(String[] phone_book) {
		// 1. 해쉬 안쓰고 풀기.
//		Arrays.sort(phone_book);
//		for (int i = 0; i < phone_book.length - 1; i++) {
//			if (phone_book[i + 1].startsWith(phone_book[i])) {
//				return false;
//			}
//		}
//		return true;
		// 2. 해쉬 써서 풀기
		HashMap<String, Integer> hm = new HashMap<>();
		for(int i=0;i<phone_book.length;++i) {
			hm.put(phone_book[i], i+1);//해쉬맵에 전화번호다넣어주고
		}
		for(String str : phone_book){//한번씩돌면서
            for(int index = 0; index < str.length(); index++){//길이별로 검색
                String temp = str.substring(0, index); //1글자,2글자,...이런식               
                if( hm.containsKey( temp ) ){//한개라도 포함이되어있다? 바로 false
                    return false;
                }
            }
        }
		return true;
	}

	public static void main(String[] args) {
		String[] phone_book = { "12", "123", "1235", "567", "88" };
		System.out.println(solution(phone_book));
	}

}
