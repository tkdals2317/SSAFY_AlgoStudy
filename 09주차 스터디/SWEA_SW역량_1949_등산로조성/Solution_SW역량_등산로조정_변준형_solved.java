import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution_SW역량_등산로조정_변준형_solved {
 
    static int T,N,K,answer;
    static int[][] map;
    static boolean[][] visited;
    static boolean use; 
    static ArrayList<int[]> maxHeight;
    static int[] dr= {1,-1,0,0};
    static int[] dc= {0,0,-1,1};
    static int startR, startC;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        T = Integer.parseInt(in.readLine());
         
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(in.readLine(), " ");
             
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            map = new int[N][N];
            visited = new boolean[N][N];
            maxHeight = new ArrayList<int[]>();
            answer=Integer.MIN_VALUE;
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            //로직 
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
             
            // 가장 높은 봉우리 좌표 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(max == map[i][j]) maxHeight.add(new int[] {i,j});
                }
            }
             
            for(int[] start : maxHeight) {
                startR = start[0];
                startC = start[1];
                visited[startR][startC] = true;
                dfs(startR, startC, 1);
                visited[startR][startC] = false;
            }
             
            System.out.println("#"+t+" "+answer);
        }
 
    }
 
    private static void dfs(int r, int c, int cnt) {
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
             
            if(!check(nr, nc) || visited[nr][nc] || (startR==nr && startC==nc)) continue;
             
            if(map[nr][nc] < map[r][c]) {
                visited[nr][nc] = true;
                dfs(nr, nc, cnt+1);
                visited[nr][nc] = false;
                // 다음 칸이 클 때 
            } else if(map[nr][nc] >= map[r][c]
                    && !use && map[nr][nc]-K < map[r][c] && map[r][c] >= 1) {
 
                use = true;
                int tempHeight = map[nr][nc];
                map[nr][nc] = map[r][c]-1;
                visited[nr][nc] = true;
                 
                dfs(nr,nc, cnt+1);
                 
                use = false;
                map[nr][nc] = tempHeight;
                visited[nr][nc] = false;
            }
        }
        answer = Math.max(answer, cnt);
        return;
    }
 
    private static boolean check(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }
}