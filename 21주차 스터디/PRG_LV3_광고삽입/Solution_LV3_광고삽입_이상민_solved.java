import java.util.*;
import java.io.*;
class Solution_LV3_광고삽입_이상민_solved {
    static int convert(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int hh = Integer.parseInt(st.nextToken())*60*60;
        int mm = Integer.parseInt(st.nextToken())*60;
        int ss = Integer.parseInt(st.nextToken());
        return hh+mm+ss;
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        //영상 시간, 광고시간 초 단위로 변환
        int playSec = convert(play_time);
        int advSec = convert(adv_time);
        
        //전체 시간 축 배열(0~99:0~59:0~59 => 100*60*60)
        int [] totalSec = new int[100*3600];
        
        for(String log : logs){
            StringTokenizer st = new StringTokenizer(log,"-");
            //시청 시간 시작시간과 종료시간 초변환
            int startSec = convert(st.nextToken());
            int endSec = convert(st.nextToken());
            //시작시간과 종료시간 사이에 숫자 누적
            for(int i = startSec; i < endSec; i++){
                totalSec[i] += 1;
            }
        }
        //총 광고시간 동안 시청자수
        long currSum = 0;
        
        //처음 0부터 광고시간만큼 초기화
        for(int i = 0; i < advSec; ++i){
            currSum += totalSec[i];
        }
        //최대 시청자수
        long maxSum = currSum;
        //최대 시청자수가 시작되는 시간
        int maxIdx = 0;
        
        //한칸씩 이동하면서 누적 시청자수 구하기
        for(int i = advSec; i < playSec; i++){
            //누적 시청자수 갱신 = 현재 시청자수 + 앞으로 한칸 갔을때의 시청자수 - 맨앞 한칸 댕겨서 시청자수 
            currSum = currSum + totalSec[i] - totalSec[i-advSec];
            if(currSum > maxSum){
                maxSum = currSum;
                maxIdx = i - advSec + 1;
            }
        }
        // ex) 10000 일때 h = 10000/3600 = 2, m = 10000/60
        System.out.println(maxIdx / 3600);
        System.out.println((maxIdx / 60) % 60);
        System.out.println(maxIdx % 60);
        return String.format("%02d:%02d:%02d", maxIdx / 3600, (maxIdx / 60) % 60, maxIdx % 60);
    }
}