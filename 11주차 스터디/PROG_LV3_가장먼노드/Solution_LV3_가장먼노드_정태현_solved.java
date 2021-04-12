package algorithm.programmers;

import java.util.*;

class Solution_LV3_가장먼노드_정태현_solved {
    static boolean[][] node;
    static boolean[] visited;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        node = new boolean[n+1][n+1]; //int 하면 메모리 초과
        visited = new boolean[n+1]; //방문
        // boolean flag = false; //더 이상 갈 노드가 있는지 체크용
        
        for(int i=0; i<edge.length; i++) {
            node[edge[i][0]][edge[i][1]] = true;
            node[edge[i][1]][edge[i][0]] = true;
        }
        
        answer = bfs(n);
        
        return answer;
    }
    public static int bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> nNode = new ArrayList<>();
        
        q.offer(1);
        visited[1] = true;
        
        int ans = 0;
        while(true) {
            ans = q.size(); //아래 while로 하나씩 더 나가기 전에 ans 값에 큐사이즈 저장
            while(!q.isEmpty()) {
                int cur = q.poll();

                for(int i=1; i<=n; i++) {
                    if(node[cur][i] && !visited[i]) { //연결되어 있고 안갔으면
                        //flag = true;
                        nNode.add(i);
                        visited[i] = true;
                    }
                }
            }
            // if(!flag) break;
            if(nNode.isEmpty()) break;
            
            for(int i=0; i<nNode.size(); i++) {
                q.offer(nNode.get(i));
            }
            // flag = false;
            nNode.clear();
        }
        
        return ans;
    }
}