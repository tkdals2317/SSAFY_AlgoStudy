import java.util.*;
import java.io.*;
class Solution_LV2_소수찾기_이상민_solved {
    static char [] input;
    static boolean [] visited;
    static int length; 
    static HashMap<Integer, Integer> map = new HashMap<>();
    public int solution(String numbers) {
        int answer = 0;
        
        length = numbers.length();
        input = numbers.toCharArray();
        visited = new boolean[length];

        //몇개를 뽑을지 결정
        for(int i = 1; i <= length; i++){
            StringBuilder sb = new StringBuilder();
            perm(0, i, sb);
        }
        //저장된 숫자들 소수체크
        for(Integer num : map.keySet()){ 
             if(checkPrime(num)) answer++;             
        }
        
        return answer;
    }

    static void perm(int cnt, int M, StringBuilder sb){ 
        if(cnt == M){
            map.put(Integer.parseInt(sb.toString()), 0);
            return;
        }
        //순열
        for(int i = 0; i < length; i++){
            if(visited[i]) continue;
            sb.append(input[i]);
            visited[i] = true;
            perm(cnt+1, M, sb);
            sb.deleteCharAt(cnt);
            visited[i] = false;
        }
    }
    static boolean checkPrime(Integer num){
        boolean isPrime = true;
        //0과 1은 소수가 아님
        if(num == 0 || num ==1) return false;
        for(int i = 2; i < num; i++){
            if(num%i==0) return false;
        }
        //위의 내용을 통과했다면 소수
        return isPrime;
    }
}