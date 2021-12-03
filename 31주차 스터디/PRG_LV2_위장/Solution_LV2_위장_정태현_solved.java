package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        // System.out.println();
        
        for(int i=0; i<clothes.length; i++) {
            //키를 가지고 있지 않으면 그냥 넣기
            if(!map.containsKey(clothes[i][1])) {
                //2를 넣는 이유는 0, 1 두 경우를 위해
                map.put(clothes[i][1], 2);
            } else { //키를 가지고 있으면 ++
                int temp = map.get(clothes[i][1]);
                temp++;
                map.put(clothes[i][1], temp);
            }
        }
        
        //키 순회하면서 곱
        for(String key: map.keySet()) {
            System.out.println(map.get(key));
            answer *= map.get(key);
        }
        answer--;
        
        return answer;
    }
}