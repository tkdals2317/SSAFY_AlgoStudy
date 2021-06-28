import java.util.*;
import java.io.*;
class Solution_LV2_수식최대화_이상민_solved {
    //2차원 배열로 나올 수 있는 조합 만들기
    static String [] operperms = {"*-+","*+-","-+*","-*+","+-*","+*-"};
    
    public long solution(String expression) {
        //최대치는 절대값이므로 -1로 초기화
        long answer = -1;
        
        ArrayList<Long> operands = new ArrayList<Long>();
        ArrayList<Character> operator = new ArrayList<Character>();
        //연산자만 따로 저장
        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            if(c=='*'||c=='+'||c=='-'){
              operator.add(c);  
            };
        }
        //숫자만 따로 저장
        StringTokenizer st = new StringTokenizer(expression, "*-+");
        while(st.hasMoreTokens()){
            operands.add(Long.parseLong(st.nextToken()));
        }
        
        System.out.println(operands);
        System.out.println(operator);
        //조합 별로 값 구하기
        for(int i = 0; i<6; i++){
            System.out.println(operperms[i]);
            //연산결과중 최대값을 정답에 삽입
            answer = Math.max(answer, getResult(operands, operator, operperms[i]));
        }

        return answer;
    }
    static long getResult(ArrayList<Long> operands, ArrayList<Character> operator, String operperms){
        //원본을 유지하기위해 복사할 어레이리스트 생성
        ArrayList<Long> operandsTemp = new ArrayList<Long>();
        ArrayList<Character> operatorTemp = new ArrayList<Character>();
        //리스트 DeepCopy
        for(int i = 0; i<operands.size(); i++){
            operandsTemp.add(operands.get(i));
        }
        for(int i = 0; i<operator.size(); i++){
            operatorTemp.add(operator.get(i));
        }
        //연산자 순서대로 반복문
        for(int i=0; i<3; i++){
            //연산자 리스트에 현재 인덱스(i)의 연산자가 존재하는 경우 
            while(operatorTemp.contains(operperms.charAt(i))){
                //연산자의 위치 구하기
                int operIdx = operatorTemp.indexOf(operperms.charAt(i));
                //연산자 제거를 하면서 연산자도 뽑아내기
                char oper = operatorTemp.remove(operIdx);
                //연산 결과를 저장할 변수
                long res = 0;
                //피연산자 두개를 제거하면서 뽑아내기
                long num1 = operandsTemp.remove(operIdx);
                long num2 = operandsTemp.remove(operIdx);
                
                System.out.println(operperms.charAt(i)+" "+operIdx);
                //만약 현재 연산자가 *라면
                if(oper=='*'){
                    res = num1 * num2;
                //만약 현재 연산자가 -라면    
                }else if(oper=='-'){
                    res = num1 - num2;
                //만약 현재 연산자가 +라면    
                }else{
                    res = num1 + num2;
                }                
                //삭제했던 피연산자 자리에 연산결과를 삽입
                operandsTemp.add(operIdx, res);
            }
        }
        System.out.println(operandsTemp);
        //최종적으로 리스트에는 모든 연산을하고 한개의 값만 남는다. 절대값으로 반환!
        return Math.abs(operandsTemp.get(0));
    }
}