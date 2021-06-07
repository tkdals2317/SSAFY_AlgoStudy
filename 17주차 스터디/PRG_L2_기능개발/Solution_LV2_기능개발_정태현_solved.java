package algorithm.programmers;

import java.util.*;
import java.io.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        //배포지점 체크
        int cur = 0;
        while(true) {
            for(int i=0; i<speeds.length; i++) {
                progresses[i] += speeds[i];
            }
            
            //다음 배포할 차례의 작업이 배포준비가 되었는가
            if(progresses[cur]>=100) {
                int cnt = 1;
                for(int j=cur+1; j<speeds.length; j++) {
                    //if: 100을 넘겨서 cnt++;
                    //else: 아니라면 break하고 다음 배포로
                    if(progresses[j]>=100) {
                        cnt++;
                    } else {
                        break;
                    }
                }
                answer.add(cnt);
                cur = cur + cnt;
            }
            
            if(cur >= speeds.length) break;
        }
        
        
        return answer;
    }
}