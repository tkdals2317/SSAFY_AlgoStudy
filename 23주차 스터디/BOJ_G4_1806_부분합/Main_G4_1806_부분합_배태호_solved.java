package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_1806_부분합 {
	    public static void main(String[] args) throws IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int s = Integer.parseInt(st.nextToken());
	    	
	    	int[] input = new int [n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int i=0; i<n; i++)
	    		input[i]=Integer.parseInt(st.nextToken());
	    	
	    	int left = 0; // 왼쪽 포인터
	    	int right = 0; // 오른쪽 포인터
	    	int answer = Integer.MAX_VALUE;
	    	int sum = 0;  // 합계
	    	while (true) 
	            if (sum >= s) { // 합계가 s 보다 크면 
	                sum -= input[left];
	                answer = Math.min(answer, (right - left)); // 부분합 최소 길이 갱신
	                left++; // 왼쪽 포인터 오른쪽으로 이동
	            } else if (right == n) break; //끝 도달
	             else {
	            	 sum += input[right]; //합계 추가
	            	 right++;// right포인터 오른쪽으로 이동
	             }
	    	
	    	System.out.println(answer==Integer.MAX_VALUE?0:answer);
	    }
	}