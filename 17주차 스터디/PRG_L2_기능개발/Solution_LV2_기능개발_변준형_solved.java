import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0; i < progresses.length; i++){
            if((100-progresses[i]) % speeds[i] == 0){
                temp.add((100-progresses[i]) / speeds[i]); 
            }else{
                temp.add(((100-progresses[i]) / speeds[i]) + 1);
            } 
        } 
        // 리스트 temp를 비교해 배포마다 몇 개씩 되는지
        int cnt = 1;
        int ck = temp.get(0);
        for(int j=1; j < temp.size(); j++){
            if(ck >= temp.get(j)){ 
                cnt += 1; 
            }else{
                result.add(cnt);
                ck = temp.get(j);
                cnt = 1; 
            } if(j == (temp.size()-1)){
                result.add(cnt);
            } 
        } 
        // 리스트를 배열로 바꿔 출력 
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i); } return answer; 
    }
}