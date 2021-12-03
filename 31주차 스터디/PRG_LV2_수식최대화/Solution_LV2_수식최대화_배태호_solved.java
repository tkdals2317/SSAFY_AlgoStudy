package PG;

import java.util.*;

public class Soulition_programmers_level2_수식최대화 {
	static char[] opers = { '*', '+', '-' };
	static long answer = 0;

	public static void main(String[] args) {
		String expression = "50*6+3*2";
		/////////////////////////////////////////////////////
		boolean visited[] = new boolean[3];
		ArrayList<Long> nums = new ArrayList<Long>();
		ArrayList<Character> oper = new ArrayList<Character>();
		
		String num = "";
		for (int i = 0; i < expression.length(); i++) // 
			if (expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') { // 계산부호 만나면
				nums.add(Long.parseLong(num));
				oper.add(expression.charAt(i));
				num = "";
			} else num += expression.charAt(i); // 숫자일때
		
		nums.add(Long.parseLong(num)); // 마지막에 num 추가
			
		per(0, visited, new char[3], nums, oper); // 순열 계산
		System.out.println(answer);
	}

	private static void per(int idx, boolean[] visited, char[] op, ArrayList<Long> nums, ArrayList<Character> oper) {
		// TODO Auto-generated method stub
		if (idx >= 3) {
			ArrayList<Long> cNums = new ArrayList<>(nums);	// 리스트 복사
            ArrayList<Character> cOps = new ArrayList<Character>(oper);
           
            for(int i=0;i<op.length;i++)
                for(int j=0; j< cOps.size(); j++) // 우선순위 연산자 부터 계산
                    if(op[i] == cOps.get(j)){ // 우선순위에 부합하는 연산자 있으면
                        Long res = calc(cNums.remove(j), cNums.remove(j), op[i]); // 리스트에서 제거해서 계산하고 다시 집어넣기
                        cNums.add(j, res);
                        cOps.remove(j--);
                    }
            
            answer = Math.max(answer, Math.abs(cNums.get(0))); //크면 answer 교체
			return;
		}
		
		for (int i = 0; i < 3; i++)  // 연산자 우선순위 결정 ( 순열 )
			if (!visited[i]) {
				visited[i] = true;
				op[idx]=opers[i];
				per(idx + 1, visited, op, nums, oper);
				visited[i] = false;
			}
	}

	private static Long calc(Long n1, Long n2, char op) {
		// TODO Auto-generated method stub
		if (op == '*') return n1 * n2;
		else if (op == '+') return n1 + n2;
		else if (op == '-') return n1 - n2;
        return (long) 0;
	}
}
