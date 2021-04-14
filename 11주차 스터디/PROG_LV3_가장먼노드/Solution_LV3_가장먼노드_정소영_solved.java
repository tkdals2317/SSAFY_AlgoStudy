
public class Solution_LV3_가장먼노드_정소영_solved {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/*


import java.util.*;

class Solution {
    static int answer;
    
    public int solution(int n, int[][] edge) {
        answer = 0;
        
       
        
        int[] list = new int[n+1];
        boolean[][] map = new boolean[n+1][n+1];
        boolean[] visited = new boolean[n+1];
        
        for(int i=0;i<edge.length;i++){
            int a = edge[i][0];
            int b = edge[i][1];
            map[a][b] = map[b][a] = true;
        }
        
        repeat2(1, n, list, visited, Integer.MIN_VALUE, map);
        return answer;
    }
    
    
    
    
    static void repeat2(int start, int n, int[] list, boolean[] visited, int max, boolean[][] map){
        

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            
            int tmp = q.poll();
            
            
            for(int i=1;i<=n;i++){
                if(map[tmp][i] && !visited[i]){
                    
                    
                    
                    list[i] = list[tmp] + 1;
                    visited[i] = true;
                    
                    if(list[i]>max){
                        answer = 1;
                        max = list[i];
                    } else if(list[i]==max){
                        answer++;
                    }
                    
                    q.add(i);
                    
                }
            } 
        }
    }
}

*/