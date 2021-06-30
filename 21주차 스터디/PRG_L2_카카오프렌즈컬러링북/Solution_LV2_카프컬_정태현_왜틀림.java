package algorithm.programmers;

import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0}; //상우하좌
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int numberOfArea = 0;
    static int maxSizeOfOneArea = 0;
    public int[] solution(int m, int n, int[][] picture) {
        // int numberOfArea = 0;
        // int maxSizeOfOneArea = 0;
    	// 전역 변수를 함수 내부에서 초기화 안해줘서 틀렸었다
    	numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
            	// 색칠된 숫자고 방문하지 않았다면
                if(picture[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j, m, n, picture);
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void bfs(int r, int c, int m, int n, int[][] picture) {
        Queue<Pos> q = new LinkedList<Pos>();
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        
        //현재 칸은 count된 상태  ∴ cnt = 1
        int cnt = 1;
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            for(int i=0; i<dr.length; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if(nr < 0 || nc < 0 || nr >= m || nc >= n)  {
                    continue;
                }
                
                //보려는 칸이 시작칸과  색깔이 같다(같은 영역) && 방문하지않았음
                if(picture[nr][nc] == picture[r][c] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                    cnt++;
                }
            }
        }
        
        numberOfArea++;
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
    }
    
    static class Pos {
        int r, c;
        
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}