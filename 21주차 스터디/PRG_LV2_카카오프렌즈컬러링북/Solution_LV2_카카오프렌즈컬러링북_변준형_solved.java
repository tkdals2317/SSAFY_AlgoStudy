import java.util.*;

class Solution_LV2_카카오프렌즈컬러링북_변준형_solved {
    static int picCnt;
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        boolean[][] visited = new boolean[m][n];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(picture[i][j] > 0 && !visited[i][j]) {
                    picCnt = 0;
                    answer[0]++;
                    dfs(m, n, picture, i, j, picture[i][j], visited);
                    pq.add(picCnt);
                }
            }
        }
        answer[1] = pq.poll();
        
        return answer;
    }
    void dfs(int m, int n, int[][] picture, int x, int y, int num, boolean[][] visited) {
        if(visited[x][y] || picture[x][y] != num) return;
        picCnt++;
        visited[x][y] = true;
        
        if(0 <= x-1) {
            dfs(m, n, picture, x-1, y, num, visited);
        }
        if(x+1 < m) {
            dfs(m, n, picture, x+1, y, num, visited);
        }
        if(0 <= y-1) {
            dfs(m, n, picture, x, y-1, num, visited);
        }
        if(y+1 < n) {
            dfs(m, n, picture, x, y+1, num, visited);
        }
    }
}