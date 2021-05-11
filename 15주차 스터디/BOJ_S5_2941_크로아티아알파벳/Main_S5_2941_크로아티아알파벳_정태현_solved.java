package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S5_2941_크로아티아알파벳_정태현_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		String s = br.readLine();
		
		//입력 문자열 길이
		int len = s.length();
		int cnt = 0;
		
		//str 배열 길이 만큼 돌아서 해당 문자열을 포함하는지 체크
		for (int i = 0; i < str.length; i++) {
			//같은 문자열이 여러개 들어가 있을 수 있으니 무한으로 체크, ex) c=c=
			while(true) {
				if(s.contains(str[i])) {
					//한번 체크가 끝난건 무의미한 문자열로 바꿔준다
					//replaceFirst => 맨 처음에 발견한 것을 바꿔줌
					s = s.replaceFirst(str[i], "0");
					//len에서 체크하는 문자열 길이 만큼 빼준다
					len = len - str[i].length();
					//그 빼준 글자가 한글자이기 때문에 cnt++
					cnt++;
				} else break;
			}
		}
		
		//체크 안된 남아있는 글자 수와 글자 빼준만큼(cnt) 더해서 출력
		System.out.println(len+cnt);
	}
}
