import java.io.*;
import java.util.*;

public class Main_bj_3613_javavsc_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str=br.readLine();
		StringBuilder sb=new StringBuilder();
		
		int flag=0;	//어떤 형태인지 판단 C++ -1 java 1
		for(int i=0;i<str.length();i++) {
			char tmp=str.charAt(i);
			if(tmp=='_') {
				flag=-1;
				break;
			}else if(tmp<='Z'&&tmp>='A') {
				flag=1;
				break;
			}
		}
		
		boolean ans=true;	//오류 중지 조건
		for(int i=0;i<str.length();i++) {
			char tmp=str.charAt(i);
			if(tmp=='_') {	//_가 나왓을경우
				if(flag==1||i==0) {	//언어조건,처음
					ans=false;
					break;
				}
				i++;
				if(i>=str.length()||str.charAt(i)=='_'||str.charAt(i)<='Z') {	//길이조건,문자조건
					ans=false;
					break;
				}
				tmp=str.charAt(i);
				sb.append((char)(tmp-'a'+'A'));
			}else if(tmp<='Z'&&tmp>='A') {	//대문자
				if(flag==-1||i==0) {	//언어조건,처음
					ans=false;
					break;
				}
				sb.append('_').append((char)(tmp-'A'+'a'));
			}else {	//소문자인 경우
				sb.append(tmp);
			}
			
		}
		
		if(ans==true)
			System.out.print(sb);
		else
			System.out.println("Error!");
		
		br.close();
	}

}
