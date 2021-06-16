import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);   //  참가자 정렬
        Arrays.sort(completion);    //  완주자 정렬
        for(int i = 0; i < completion.length; i++){     //  완주자 수만큼
            if(!participant[i].equals(completion[i])){  //  다르면 반환
                answer = participant[i];
                break;
            } else{
                answer = participant[i+1];
            }
        }
        return answer;
    }
}

