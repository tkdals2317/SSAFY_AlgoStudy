package programmers;

public class Soulition_programmers_level3_광고삽입 {

	public static void main(String[] args) {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

		////////////////////////////////////////////////////////
		String answer = "";
        long[] arr = new long[convertTime(play_time) + 1]; // 초단위로 배열 생성
        
        for(int i = 0; i < logs.length; i++){
            String[] sArr = logs[i].split("-"); // 시작시간, 종료시간 구분
            
            int start = convertTime(sArr[0]); // 시작시간 초로 변환
            int end = convertTime(sArr[1]);	// 종료시간 초로 변환
            
            for (int j = start; j < end; j++) // 광고 재생했던 횟수 증가
                arr[j]++;
        }
        
        for(int i = 1; i < arr.length; i++)  //전체 누적 시청 시간  ( 누적합 )
        	arr[i] += arr[i - 1];
        
        int adtime = convertTime(adv_time); // 총 광고시간 몇초인지 계산
//        System.out.println(adtime+"adtime");
        long max = arr[adtime - 1];
        int maxTime = 0;
        
        for(int i = adtime; i < arr.length; i++) // 사람들이 가장 많이 분 시간 탐색
            if(max < arr[i] - arr[i - adtime]){ //adtime동안 가장 많이 본 구간 탐색
            	max = arr[i] - arr[i - adtime];
                maxTime = i - adtime + 1;
            }
        
        System.out.println(convertTime(maxTime));
//        return convertTime(maxTime);

	}
    static int convertTime(String time){ // 시간 분 초 -> 초로 변환 
        String[] sArr = time.split(":");
        return 3600 * Integer.valueOf(sArr[0]) + 60 * Integer.valueOf(sArr[1]) + Integer.valueOf(sArr[2]);
    }
    
    static String convertTime(int time){ // 초단위 시간 -> 시 분 초로 변환
//    	System.out.println(time+" time");
        int hour = time / 3600;
        time %= 3600;
        int min = time / 60;
        time %= 60;
        int sec = time;
        
        StringBuilder sb = new StringBuilder("");
        if(hour < 10) sb.append("0");
        sb.append(Integer.valueOf(hour));
        sb.append(":");
        
        if(min < 10) sb.append("0");
        sb.append(Integer.valueOf(min));
        sb.append(":");
        
        if(sec < 10) sb.append("0");
        sb.append(Integer.valueOf(sec));
        return sb.toString();
    }
}
