import java.util.*;
import java.io.*;
class Solution_LV2_Ä«Ä«¿ÀÇÁ·»ÁîÄÃ·¯¸µºÏ_ÀÌ»ó¹Î_solved {
    static boolean [][] visited;
    static int numberOfArea;
    static int maxSizeOfOneArea;
    static int di[] = {-1, 0, 1, 0};
    static int dj[] = { 0, 1, 0,-1};
    static int M, N;
    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]&&picture[i][j]>0){
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j, picture));
                    numberOfArea++;
                }
            }
        }        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    static int bfs(int si, int sj, int[][] picture){
        ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
        queue.add(new int[]{si, sj});
        visited[si][sj] = true;
        int color = picture[si][sj];
        int cnt=0;
        while(!queue.isEmpty()){
            int [] curr = queue.poll();
            cnt++;
            for(int d = 0; d < 4; d++){
                int ni = curr[0]+di[d];
                int nj = curr[1]+dj[d];
                if(ni>=0&&ni<M&&nj>=0&&nj<N&&!visited[ni][nj]&&picture[ni][nj]==color){
                    queue.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
        System.out.println(color+" "+cnt);
        return cnt;  
    } 
}