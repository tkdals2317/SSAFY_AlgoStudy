import java.io.*;
import java.util.*;

class Solution_LV2_괄호회전하기_이상민_solved {

	public static void main(String[] args) throws Exception {
		String s = "}]()[{";
		int answers = solution(s);
		System.out.println(answers);
	}

	private static int solution(String s) {
		int answer = 0;
		ArrayList <Character> input = new ArrayList<>();
        //문자 길이
		int length =  s.length();
		//어레이리스트에 문자열 한글자씩 추가
        for(int i = 0; i < length; i++){
            input.add(s.charAt(i));
        }
        for(int i = 0; i < length; i++){
            Stack<Character> stack = new Stack<>();
            for(int j = 0; j < length; j++){
            	//비어있을 경우 무조건 스택에 쌓기
                if(stack.isEmpty()) {
                    stack.push(input.get(j));
                    continue;
                }
                //올바른 괄호 시 스택에서 제거
                if(stack.peek()=='['&&input.get(j)==']'){
                    stack.pop();
                    continue;
                }else if(stack.peek()=='{'&&input.get(j)=='}'){
                    stack.pop();
                    continue;
                }else if(stack.peek()=='('&&input.get(j)==')'){
                    stack.pop();
                    continue;
                }
                //올바른 괄호가 아닐 시 스택에 삽입
                stack.push(input.get(j));
                //System.out.println(stack);
            }
            //스택이 비어있다면 올바른 괄호 문자열
            if(stack.size()==0) answer++;
            //한칸씩 회전
            input.add(input.remove(0));
        }
        return answer;

	}
	
}