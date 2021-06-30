package algorithm.programmers;

import java.util.*;

class Solution {
    static long answer;
    static ArrayList<Long> numbers = new ArrayList<>();
    static ArrayList<Character> ops = new ArrayList<Character>();
    static boolean[] visited = new boolean[3];
    static char[] tempOp = new char[3];
    static char[] op = {'*', '+', '-'};
    
    public long solution(String expression) {
        answer = 0;
        String num = "";
        for(int i = 0; i < expression.length(); i++) {
            char temp = expression.charAt(i);
            if(temp >= '0' && temp <= '9') { //character가 숫자면 숫자를 임시 저장하는 num에 저장
                num += temp;
            } else { //연산자를 만나면 이때까지 저장한 숫자를 numbers list에 넣고 num 초기화, ops list에 연산자 넣음
                numbers.add(Long.parseLong(num));
                num = "";
                ops.add(temp);
            }
        }
        //마지막 숫자는 반복문이 끝나고 처리
        numbers.add(Long.parseLong(num));
        
        perm(0);
        
        return answer;
    }
    
    public void perm(int n) {
    	//연산자 3개까지 처리를 다 했다면
        if(n == 3) {
            solve();
            return;
        }
        
        for (int i=0; i<3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tempOp[n] = op[i];
                perm(n + 1);
                visited[i] = false;
            }
        }
    }
    
    public void solve() {
        ArrayList<Long> solveNums = new ArrayList<>();
    	ArrayList<Character> solveOps = new ArrayList<Character>();
    	//숫자 리스트와 연산자 리스트에 저장했던걸 다 넣어준다
        solveNums.addAll(numbers);
        solveOps.addAll(ops);
        
        for(int i=0; i<tempOp.length; i++) {
            for(int j=0; j<solveOps.size(); j++) {
            	//우선순위순으로 연산자를 만나게 된다면
                if(solveOps.get(j) == tempOp[i]) {
                    Long num1 = solveNums.get(j);
                    Long num2 = solveNums.get(j+1);
                    Long ans = calc(num1, num2, tempOp[i]);
                    
                    //계산후에는 계산처리 된 두 숫자를 뒤에서부터 하나씩 처리
                    solveNums.remove(j+1);
                    solveNums.remove(j);
                    //연산자도 제거
                    solveOps.remove(j);
                    
                    //연산된 결과를 해당위치에 다시 넣기
                    solveNums.add(j, ans);
                    //숫자 2개 삭제하고 1개를 넣음, 고로 1개 삭제를 했으니 한번 더 돌기위해 -1
                    j--;
                }
            }
        }
        
        answer = Math.max(answer, Math.abs(solveNums.get(0)));
        
    }
    
    public Long calc(Long num1, Long num2, char op) {
    	switch (op) {
			case '*': {
				return num1 * num2;
			}
			case '+': {
				return num1 + num2;
			}
			case '-': {
				return num1 - num2;
			}
    	}
    	return (long)0;
    }
    
}