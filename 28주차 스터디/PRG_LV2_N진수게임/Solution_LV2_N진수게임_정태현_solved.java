package algorithm.programmers;

import java.io.*;
import java.util.*;


class Solution {
    static String[] code = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public String solution(int n, int t, int m, int p) {
        // String answer = "";
        
        // 게임용 숫자 0++
        int num = 0;
        
        // 구할려는 차례까지 넣어줄 SB
        StringBuilder ans = new StringBuilder();
        
        // 구하려는 숫자 * 게임 인원
        // 명당 숫자 1개
        while(ans.length() < t*m) {
            //진수화
            ans.append(toNcode(num, n));
            num++;
        }
        
        // 튜브가 말해야되는 숫자 모음
        StringBuilder answer = new StringBuilder();
        
        for(int i=0; i<ans.length(); i++) {
            if(answer.length()==t) break;
            
            // m명 게임, p번째에
            // 0부터 체크 => p-1
            // 0* 1 2* 3 4* 5 6* 7 8*
            if(i % m == p-1) {
                answer.append(ans.charAt(i));
            }
        }
        
        //런타임 에러
        // return answer;
        
        return answer.toString();
    }
    
    //num 숫자 -> n 진수
    public String toNcode(int num, int n) {
        StringBuilder sb = new StringBuilder();
        
        //0일때 0을 넣어주기 위해 do-while 사용
        do {
            sb.append(code[num % n]); // 나머지 append
            num = num / n;
        } while(num != 0);
        
        //왼쪽에서부터 append 했으니까
        return sb.reverse().toString();
    }
}