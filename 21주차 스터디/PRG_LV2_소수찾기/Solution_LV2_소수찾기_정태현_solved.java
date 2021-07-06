package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution2 {
    static boolean[] visited;
    static String[] num;
    static String[] selNum;
    static int answer;
    static Set<Integer> set;
    public int solution(String numbers) {
        set = new HashSet();
        int n = numbers.length();
        num = numbers.split("");
        visited = new boolean[n];
        
        //되는대로 뽑아야 하기 때문에 각 뽑을 개수를 정하고 순열 돌림
        for(int i=1; i<=n; i++) {
            selNum = new String[i];
            perm(0, i, n);
        }
        
        return set.size();
    }
    
    public void perm(int start, int end, int length) {
        //개수만큼 뽑으면 숫자로 소수 체크
    	if(start == end) {
            isPrime();
            return;
        }
        
        //순열
        for(int i=0; i<length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selNum[start] = num[i]; //이때 뽑은 걸 넣자
                perm(start + 1, end, length);
                visited[i] = false;
            }
        }
    }
    
    public void isPrime() {
    	//정수를 소수로 만드는 것보다 int로의 변환으로 짧게 해결
        String str = "";
        
        for(int i=0; i<selNum.length; i++) {
            str += selNum[i];
        }
        int number = Integer.parseInt(str);
        
        //1이나 0이면 소수가 아니라 return
        if(number == 1 || number == 0) {
            return;
        }
        
        boolean primeCheck = false;
        
        for(int i=2; i<number; i++) {
            if(number % i == 0) {
                primeCheck = true;
                break;
            }
        }
        
        if(!primeCheck) set.add(number);
    }
}