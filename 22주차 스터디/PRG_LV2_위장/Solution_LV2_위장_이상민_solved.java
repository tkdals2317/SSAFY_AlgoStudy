import java.util.*;
import java.io.*;
class Solution_LV2_위장_이상민_solved {

    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for(int i = 0; i < clothes.length; i++){
            hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1],0)+1);
        }
        
        //ex) 얼굴 4개 상의 2개 청바지 3개 일때
        //안입는 경우 까지 생각했을 때 경우의 수는 얼굴 5개 상의 3개 청바지 4개
        //5*3*4 = 60
        for(String key : hm.keySet()){
            answer *= hm.get(key)+1; 
        }
        //하나는 입어야되므로 최종 결과 값에 1을 빼준다
        return answer-1;
    }
}