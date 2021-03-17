package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_10828_스택_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
		Stack<Integer> stack=new Stack<Integer>();
		
		int t=Integer.parseInt(br.readLine());
		for(int tc=0;tc<t;tc++) {
			String str=br.readLine();
			StringTokenizer st=new StringTokenizer(str," ");
			switch (st.nextToken()) {
			case "push":
				stack.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if(stack.isEmpty())
					System.out.println(-1);
				else
					System.out.println(stack.pop());
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if(stack.isEmpty())
					System.out.println(1);
				else
					System.out.println(0);
				break;
			case "top":
				if(stack.isEmpty())
					System.out.println(-1);
				else
					System.out.println(stack.peek());
				break;
			}
			
		}
		br.close();
	}

}
