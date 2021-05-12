import java.io.*;
import java.util.*;

public class Main_bj_10988_팰린드롬인지확인하기_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str=br.readLine();
		
		int ans=1;
		int len=str.length();
		for(int i=0;i<len/2;i++) {	//앞과 뒤에서 부터 수 확인
			if(str.charAt(i)!=str.charAt(len-i-1)) {
				ans=0;
				break;
			}
		}
		
		System.out.println(ans);
		
		br.close();
	}

}
