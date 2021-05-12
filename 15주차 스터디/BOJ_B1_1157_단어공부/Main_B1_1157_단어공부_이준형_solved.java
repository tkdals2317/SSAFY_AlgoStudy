import java.io.*;
import java.util.*;

public class Main_bj_1157_단어공부_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str=br.readLine();
		int[] bae=new int[30];
		int max=0;
		for(int i=0;i<str.length();i++) {
			int tmp=str.charAt(i)-'A';	//대문자 형변환
			if(tmp>26)	//소문자 인지 판단
				tmp+='A'-'a';
			bae[tmp]++;	//해당위치 값 증가
			max=Math.max(max, bae[tmp]);	//최대값 갱신
		}
		
		int count=0;
		int idx=-1;
		for(int i=0;i<26;i++) {
			if(max==bae[i]) {	//최댓값 찾으면 증가
				count++;
				idx=i;
			}
		}
		if(count==1)	//최대 값이 유일하면 증가
			System.out.println((char)('A'+idx));
		else
			System.out.println("?");
		
		br.close();
	}

}
