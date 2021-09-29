package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution {
    static int[][] arr;
    static int[] answer;
    static int min;
    public int[] solution(int rows, int columns, int[][] queries) {
        // int[] answer = {};
        answer = new int[queries.length];
        arr = new int[rows][columns];
        fill(rows, columns);
        
        for(int i=0; i<queries.length; i++) {
            int r1 = queries[i][0] - 1;
            int c1 = queries[i][1] - 1;
            int r2 = queries[i][2] - 1;
            int c2 = queries[i][3] - 1;
            min = arr[r1][c1];
            rotate(r1, c1, r2, c2);
            answer[i] = min;
        }
        
        return answer;
        
    }
    
    public void fill(int r, int c) {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                //해당 위치의 row * column 수 + 해당위치의 column + 1
                arr[i][j] = (i * c) + j + 1;
            }
        }
    }
    
    public void rotate(int r1, int c1, int r2, int c2) {
        //미리 해당 칸 저장
        int temp = arr[r1][c1];
        // min = temp;
        //좌
        for(int i=r1; i<r2; i++) {
            arr[i][c1] = arr[i+1][c1];
            if(arr[i][c1] < min) min = arr[i+1][c1];
        }
        //하
        for(int i=c1; i<c2; i++) {
            arr[r2][i] = arr[r2][i+1];
            if(arr[r2][i] < min) min = arr[r2][i];
        }
        //우
        for(int i=r2; i>r1; i--) {
            arr[i][c2] = arr[i-1][c2];
            if(arr[i][c2] < min) min = arr[i][c2];
        }
        //상
        //arr[r1][c1]에는 좌측부터 민 값이 들어가 있기 때문에 그 앞칸까지 땡긴다
        for(int i=c2; i>c1+1; i--) {
            arr[r1][i] = arr[r1][i-1];
            if(arr[r1][i] < min) min = arr[r1][i];
        }
        //미리 저장해놓았던 값을 저장
        arr[r1][c1+1] = temp;
     }
}