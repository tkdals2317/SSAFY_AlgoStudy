
import java.util.*;
import java.io.*;
class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        //각 기능별 개발 완료까지 걸리는 소요시간을 저장할 배열 
        int [] days = new int[progresses.length];
        
        for(int i= 0; i<progresses.length; i++){
            int diff = 100-progresses[i];
            int cday = 0;
            //현재 기능 완료에 소요되는 시간 구하기((100-현재진행률) % 소요시간)  
            if(diff%speeds[i]==0){ 
                cday = (diff/speeds[i]);
            //나머지가 있다면 100%를 넘기지 못하므로 하루가 더 있어야지 100%를 넘긴다
            }else{ 
                //ex : (100-30)%30 = 3 나머지 10 그러므로 3일하고 1일을 더 개발해야 완료할 수 있다, 총 4일 
                cday = (diff/speeds[i])+1;
            }
            days[i] = cday;
            System.out.print(cday+" ");
        }
        System.out.println();
        
        ArrayList<Integer> answer = new ArrayList<Integer>();
        //첫번째꺼는 무조건 1로 초기화
        int count = 1;
        //인덱스 1부터 시작
        for(int i = 1; i < days.length; i++){
            //현재 기능(i번째 기능)에 드는 소요시간이 이전 기능보다 작다면  
            if(days[i-count] >= days[i]){
                //이전 기능을 개발 완료 했을 시 이미 현재 기능이 개발완료 된 상태이므로 count 증가
                count++;
            //현재 기능(i번째 기능)에 드는 소요시간이 이전 기능보다 크다면
            }else{
                //이전 기능을 개발 완료 했을 시 이미 현재 기능이 개발이 덜된 상태
                //이전까지 개발완료된 기능의 갯수를 answer에 추가
                answer.add(count);
                //현재 기능을 기준으로 다시 count를 세야하므로 count를 1로 초기화
                count=1;
            }
        }
        //마지막 progresses가 남아있으므로 count를 answer에 추가
        answer.add(count);
        System.out.println(answer);
        return answer;
    }
}