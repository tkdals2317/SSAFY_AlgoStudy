import java.io.*;
import java.util.*;

public class Main_bj_1157_단어공부_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str=br.readLine();
		int[] bae=new int[30];
		int max=0;
		for(int i=0;i<str.length();i++) {
			int tmp=str.charAt(i)-'A';
			if(tmp>26)
				tmp+='A'-'a';
			bae[tmp]++;
			max=Math.max(max, bae[tmp]);
		}
		
		int count=0;
		int idx=-1;
		for(int i=0;i<26;i++) {
			if(max==bae[i]) {
				count++;
				idx=i;
			}
		}
		if(count==1)
			System.out.println((char)('A'+idx));
		else
			System.out.println("?");
		
		br.close();
	}

}
