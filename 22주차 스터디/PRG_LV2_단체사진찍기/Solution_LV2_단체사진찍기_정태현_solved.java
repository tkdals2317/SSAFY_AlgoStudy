package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution1 {
    static boolean[] visited;
    static char[] output;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer, n;
    static String[] data;
    
    public int solution(int n, String[] data) {
        answer = 0;
        output = new char[8]; //순열용 배열
        visited = new boolean[8]; //순열 방문처리용
        
        //solution의 파라미터를 전역으로
        this.data = data;
        this.n = n;
        
        perm(0);
        return answer;
    }
    
    public void perm(int cnt) {
        if(cnt == 8) {
            // solve method => answer++;
        	// 조건 다 맞아서 true이면 answer+1
            if(solve()) answer++;
        }
        
        for(int i=0; i<8; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[cnt] = friends[i]; //해당 번째의 카카오친구 순서배치
                perm(cnt + 1);
                visited[i] = false;
            }
        }
    }
    
    public boolean solve() {
        //data의 조건들 체크
        for(int i=0; i<n; i++) {
            boolean flag = false; //조건 체크용
            int idx1 = 0, idx2 = 0; //조건문의 알파벳 2개의 인덱스
            int num = data[i].charAt(4) - '0'; //조건문 값
            //일단 인덱스 찾기
            for(int j=0; j<8; j++) {
                if(output[j] == data[i].charAt(0)) {
                    idx1 = j;
                }
                if(output[j] == data[i].charAt(2)) {
                    idx2 = j;
                }
            }
            
            //차이가 조건문 값과 조건문 부등호의 조건에 맞으면 true
            if(data[i].charAt(3) == '=') {
                if(Math.abs(idx2 - idx1) == num + 1) {
                    flag = true;
                } else flag = false;
            } else if(data[i].charAt(3) == '<') {
                if(Math.abs(idx2 - idx1) < num + 1) flag = true;
                else flag = false;
            } else if(data[i].charAt(3) == '>') {
                if(Math.abs(idx2 - idx1) > num + 1) flag = true;
                else flag = false;
            }
            
            //false면 나머지 조건 볼 필요없이 가지치기
            if(!flag) return false;
        }
        
        return true;
    }
}