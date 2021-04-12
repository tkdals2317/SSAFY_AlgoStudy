import java.util.*;
import java.io.*;

class Solution_LV3_가장먼노드_이상민_solved {
    static boolean [] visited;
    static ArrayList<Integer> [] list;
    static int max = Integer.MIN_VALUE;
    static int []dArr;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        dArr = new int[n+1];
        for(int i = 0; i<=n; i++){
            list[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < edge.length; i++){
            int from = edge[i][0];
            int to = edge[i][1];
            list[from].add(to);
            list[to].add(from);
        }
        bfs(1);
        for(int i = 1; i<=n; i++){
            if(max==dArr[i]) answer++; 
        }
        return answer;
    }
    
    public static void bfs(int start){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {start, 0});
        visited[start] = true;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int curno = curr[0];
            int cDepth = curr[1];
            //System.out.println(curno+" "+ cDepth);
            dArr[curno] = cDepth;
            max = Math.max(max, cDepth);
            for(int i = 0; i < list[curno].size(); i++){
                if(visited[list[curno].get(i)]) continue;
                queue.offer(new int[] {list[curno].get(i), cDepth+1});
                visited[list[curno].get(i)] = true;
            }                       
        }
        return;
    }
}