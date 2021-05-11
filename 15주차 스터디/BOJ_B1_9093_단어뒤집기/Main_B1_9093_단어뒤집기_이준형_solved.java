import java.io.*;
import java.util.*;

public class Main_bj_9093_단어뒤집기_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int num=Integer.parseInt(br.readLine());
		
		Stack<Character> stack;
		StringBuilder sb = new StringBuilder();
		for(int n=0;n<num;n++) {
			stack=new Stack();
			String str=br.readLine();
			
			for(int i=0;i<str.length();i++) {
				char tmp=str.charAt(i);
				if(tmp==' ') {
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}sb.append(" ");
				}else {
					stack.push(tmp);
				}
			}
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb);
		
		
		br.close();
	}

}
