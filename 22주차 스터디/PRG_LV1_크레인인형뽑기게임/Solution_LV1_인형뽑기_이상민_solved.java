import java.io.*;
import java.util.*;
class Solution_LV1_인형뽑기_이상민_solved {
    static ArrayDeque <Integer> stack; //뽑은 인형을 담을 스택
    public int solution(int[][] board, int[] moves) {
        int answer = 0;     
        stack = new ArrayDeque<Integer>();

        for(int i = 0; i < moves.length; i++){
            //뽑을 위치
            int pos = moves[i]-1;
            //인형 뽑기
            int pick = draw(board, pos);
            //아무것도 안뽑았을땐 아무 일도 일어나지 않는다
            if(pick!=0){
                //스택이 차있고 마지막에 들어온 인형이과 지금 뽑은 인형과 같다면
                if(!stack.isEmpty()&&stack.peek()==pick){
                    //기존의 인형 빼내기
                    stack.pop();
                    //두개의 인형이 사라짐
                    answer+=2;
                    continue;
                }
                //인형 스택에 집어넣기
                stack.push(pick);
            }
            
        }
        
        return answer;
    }
    static public int draw(int[][] board, int pos){
        int puppet = 0;
        //아래로 한칸씩 이동하면서 인형이 있는지 검사
        for(int i = 0; i < board.length; i++){
            if(board[i][pos]>0){
                puppet = board[i][pos];
                board[i][pos] = 0;
                break;
            }
        }
        return puppet;
    } 
    
    static public void print(int[][] board){
        for(int i = 0; i<board.length; i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }
}