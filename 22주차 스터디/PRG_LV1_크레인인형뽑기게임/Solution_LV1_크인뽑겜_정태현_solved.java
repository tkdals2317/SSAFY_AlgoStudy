package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> s = new Stack<Integer>();
        for(int i=0; i<moves.length; i++) {
            int doll = 0; //인형번호
            moves[i]--; //배열 번호 맞추기
            for(int j=0; j<board.length; j++) {
            	//내려가면서 인형 탐색
                if(board[j][moves[i]]!=0) {
                    doll = board[j][moves[i]]; 
                    board[j][moves[i]] = 0; //인형 뽑았으니 초기화
                    break;
                }
            }
            
            if(doll == 0) break; //인형없으면 스택 건드리지말고 그냥 break
            
            //스택 비면 그냥 넣고 break
            if(s.size() == 0) {
            	s.push(doll);
            	break;
            }
            
            //맨 위꺼와 같다면 팝시키고 +2
            if(doll == s.peek()) {
                s.pop();
                answer += 2;
            } else {
                s.push(doll);
            }
        }
        return answer;
    }
}