import java.util.*;
import java.io.*;
public class Main_S5_3613_JavaVsCpp_이상민 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String var = br.readLine();
		
		//java의 경우 첫 문자만 소문자 다음 단어부터는 대문자, 모든 단어는 붙여쓴다
		if(var.charAt(0)>=97&&var.charAt(0)<=122&&!var.contains("_")) {
			System.out.println(converToCpp(var));
		//C++의 경우 모두 소문자이고 단어와 단어를 구분하기 위해 '_'를 사용
		}else if(checkUnderBar(var)&&checkUpper(var)) {
			System.out.println(converToJava(var));
		}else {
			System.out.println("Error!");
		}
			
		br.close();
	}

	static boolean checkUpper(String var) {
		//소문자와 다른 기호로 구성되있는 경우 false 리턴
		for (int i = 0; i < var.length(); i++) {
			if(var.charAt(i)=='_') continue;
			if(var.charAt(i)<91 || var.charAt(i)>122) {
				return false;
			}
		}
		//소문자와 '_'로만 구성 되있다면 true 리턴
		return true;
	}
	
	private static boolean checkUnderBar(String var) {
		//첫번째와 마지막 알파벳이 '_'면 error!!
		if(var.charAt(0)=='_' || var.charAt(var.length()-1)=='_') return false;
		//'_'가 연달아 나와도 error!!
		for (int i = 0; i < var.length()-1; i++) {
			if(var.charAt(i)=='_' && var.charAt(i) == var.charAt(i+1)) {
				return false;
			}
		}
		return true;
	}

	private static String converToJava(String var) {	
		//char Array로 변경
		char [] cArr= var.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cArr.length; i++) {
			//'_'를 발견할 시 
			if(cArr[i]=='_') {
				//다음 알파벳을 대문자로 변경
				cArr[i+1] = (char)(cArr[i+1]-32);
			}else {
				//일반적인 경우 sb에 추가
				sb.append(cArr[i]);				
			}
		} 
		return sb.toString();
	}

	//Java to Cpp
	private static String converToCpp(String var) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < var.length(); i++) {
			//대문자일 경우
			if(var.charAt(i)>=65&&var.charAt(i)<91) {
				//'_'와 소문자로 변경한 알파벳으로 append
				sb.append("_").append((char)(var.charAt(i)+32));
			//일반적인 경우
			}else {
				//그냥 그대로 append
				sb.append(var.charAt(i));
			}
		}
		return sb.toString();
	}


}
