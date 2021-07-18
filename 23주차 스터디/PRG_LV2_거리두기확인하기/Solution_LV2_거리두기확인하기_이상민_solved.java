import java.io.*;
import java.util.*;

class Solution_LV2_거리두기확인하기_이상민_solved {
	static char[][] map;
	static boolean [][] visited;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = { 0, 1, 0,-1};
	
	public static void main(String[] args) throws Exception {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
							 {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, 
							 {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
							 {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, 
							 { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" }};
		
		int[] answers = solution(places);
		System.out.println(Arrays.toString(answers));
	}

	private static int[] solution(String[][] places) {
		int[] answer = new int[places.length];
        for(int i = 0; i < places.length; i++){
            map = new char[5][5];
            visited = new boolean[5][5];
            //map 입력 
            for(int j = 0; j < places[i].length; j++){
                map[j] = places[i][j].toCharArray();
                //System.out.println(Arrays.toString(map[j]));
            }
            //System.out.println();
            
            //거리두기를 확인 할 boolean 변수
            boolean isSafe = true;
            for(int r = 0; r < 5; r++){
                for(int c = 0; c < 5; c++){
                	//응시자가 있고 만약 이전까지 거리두기를 잘 지켰다면
                    if(map[r][c]=='P'&&isSafe){
                    	//반환값을 통해 거리두기 잘지켜지는지 변수 갱신
                        isSafe = bfs(r,c);
                    }
                }
            }
            //잘지켜졌다면 1
            if(isSafe) answer[i] = 1;
            //안지켜졌다면 0
            else answer[i] = 0;
        }
        return answer;
	}
	static boolean bfs(int r, int c){
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
      
		visited = new boolean[5][5];
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        int distance = 0;
        
        while(!queue.isEmpty()&&distance<2){
            
        	int size = queue.size();
            //for문을 한번 돌때마다 거리 1 증가
        	for(int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for(int d = 0; d < 4; d++){
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];
                    //범위체크 && 방문체크 && 파티션 체크
                    if(nr>=0&&nr<5&&nc>=0&&nc<5&&!visited[nr][nc]&&!(map[nr][nc]=='X')){
                        //만약 다른 응시자가 범위 안에 있다면 false 반환
                    	if(map[nr][nc]=='P') return false;
                    	//아니라면 bfs 계속 진행
                        queue.offer(new int[]{nr,nc});
                        visited[nr][nc] = true;
                    }                    
                }                
            }
        	//거리 증가
            distance++;
        }
        return true;
    }
}