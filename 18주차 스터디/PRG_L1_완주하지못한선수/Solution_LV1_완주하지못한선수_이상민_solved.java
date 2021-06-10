import java.io.*;
import java.util.*;
class Solution_LV1_완주하지못한선수_이상민_solved {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        //두 개의 배열 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);
        //정렬했을 시에 만약 같은 인덱스의 이름이 다르다면 동명이인
        //단 한명의 선수만 완주를 못하므로 둘 중 한 사람은 완주를 하지 못하는 경우로 판단
        for(int i = 0; i < completion.length; i++) {
            //만약 completion[i]와 participant[i]이 같지 않다면 participant[i] 이름이 두명
            if(!(completion[i].equals(participant[i]))){
                answer = participant[i];
                break;
            }
        }
        //completion과 participant의 길이보다 무조건 1 작다 
        //고로 completion의 길이만큼 돌렸을 때 participant의 남은 한사람이 완주를 못한 사람이 됨
        if(answer.equals("")){
            //정렬된 participant배열의 마지막 인덱스의 사람이 완주를 못한 사람이 됨
            answer = participant[participant.length-1];
        }
        return answer;
    }
}