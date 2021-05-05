import java.util.*;
import java.io.*;

class Solution {
    static int count;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(numbers, 1, target, numbers[0]);
        dfs(numbers, 1, target, -numbers[0]);
        //System.out.println(numbers.length);
        answer = count;
        return answer;
    }
    public void dfs(int [] numbers, int cnt, int target, int result){
        if(cnt==numbers.length){
            if(result==target){
                //System.out.println(result);
                count++;
            }
            return;
        }
        dfs(numbers, cnt+1, target, result+numbers[cnt]);
        dfs(numbers, cnt+1, target, result-numbers[cnt]);
    }
}
