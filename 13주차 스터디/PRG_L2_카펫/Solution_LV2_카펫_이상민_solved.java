import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;

        int brow = 0;
        int bcol = 0;
        for(int i = 1; i<=yellow; i++){
            int yrow = i;
            if(yellow%yrow==0){
                int ycol = yellow/i;
                brow = yrow+2;
                bcol = ycol+2;
                if((brow*bcol)-yellow==brown) break;                
            }
        }
        answer[0] = Math.max(brow,bcol);
        answer[1] = Math.min(brow,bcol);
        //System.out.println(Arrays.toString(answer));
        return answer;
    }
}