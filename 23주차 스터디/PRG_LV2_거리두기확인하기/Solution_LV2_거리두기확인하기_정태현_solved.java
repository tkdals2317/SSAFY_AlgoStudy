package algorithm.programmers;

import java.io.*;
import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0}; //상우하좌
    static int[] dc = {0, 1, 0, -1};
    static char[][] arr;
    static boolean[][] visited;
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for(int n=0; n<places.length; n++) {
            arr = new char[5][5];
            visited = new boolean[5][5];
            
            //char 배열로 읽기
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    arr[i][j] = places[n][i].charAt(j);
                }
            }
            
            boolean check = true;
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                	//한줄단위로 읽기 때문에 check가 없으면 fail
                    if(arr[i][j]=='P' && check) {
                        check = solve(i, j);
                    }
                }
                //false면 다음 줄 읽을 필요없이 break
                if(!check) break;
            }
            
            if(check) answer[n] = 1;
            else answer[n] = 0;
        }
        
        return answer;
    }
    
    public boolean solve(int r, int c) {
        Queue<Pos> q = new LinkedList<Pos>();
        visited = new boolean[5][5];
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        
        int depth = 0;
        //depth가 2이상이면 break(맨해튼거리 2)
        while(!q.isEmpty() && depth<2) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Pos cur = q.poll();
                for(int j=0; j<dr.length; j++) {
                    int nr = cur.r + dr[j];
                    int nc = cur.c + dc[j];
                    
                    if(nr<0 || nc<0 || nr >= 5 || nc >= 5) continue;
                    
                    
                    
                    if(!visited[nr][nc] && arr[nr][nc]!='X') {
                        if(arr[nr][nc]=='P') return false;
                        q.offer(new Pos(nr, nc));
                        visited[nr][nc] = true;
                    }
                }       
            }
            
            depth++;
        }
        
        return true;
    }
    
    static class Pos {
        int r, c;
        
        public Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}