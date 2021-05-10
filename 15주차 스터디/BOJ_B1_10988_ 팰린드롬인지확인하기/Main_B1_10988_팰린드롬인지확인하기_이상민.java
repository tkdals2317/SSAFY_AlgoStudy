import java.util.*;
import java.io.*;
public class Main_B1_10988_팰린드롬인지확인하기_이상민 {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_10988.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		//홀수일 경우 반으로 나눌 시 검색할때 한개가 모자르므로 1을 더해줌
		int length = word.length()+1;
		//기본적으로 1로 초기화
		int sol = 1;
		for (int i = 0; i < length/2; i++) {
			//양방향에서 검사하여 서로 다른게 생기면 sol을 0으로 바꿔주고 for문 탈출
			if(word.charAt(i)!=word.charAt(word.length()-1-i)) {
				sol = 0;
				break;
			}
		}	
		System.out.println(sol);			
		br.close();
	}
}
