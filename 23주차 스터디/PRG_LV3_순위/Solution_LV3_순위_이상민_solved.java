import java.io.*;
import java.util.*;

class Solution_LV3_순위_이상민_solved {
    //각 선수 별 승패를 기록할 클래스
	static class Node{
        int win;
        int lose;
    }
	//경기 결과를 통해 탐색할 그래프를 어레이리스트 배열로 구현
    static ArrayList<Integer> [] list;
    //선수들의 승패를 저장할 배열
    static Node[] player;
    //방문처리 배열
    static boolean [] visited;
	
	public static void main(String[] args) throws Exception {
		int n = 5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		
		int answers = solution(n, results);
		System.out.println(answers);
	}

	private static int solution(int n , int[][] results) {
		int answer = 0;
        //어레이 리스트 배열 초기화 및 선수들의 승패를 기록할 Node 배열 초기화
		list = new ArrayList[n+1];
        player = new Node[n+1];
        for(int i = 0; i < n+1; i++){
            list[i] = new ArrayList<Integer>();
            player[i] = new Node();
        }
        //경기 결과를 통해 리스트에 저장
        for(int i = 0; i < results.length; i++){
            int win = results[i][0];
            int lose = results[i][1];
            //현재 이긴 선수에 해당하는 인덱스에 진 선수 인덱스 기록
            list[win].add(lose);
        }
        
        for(int i = 1; i < n+1; i++){
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n+1];
            //i번째 선수를 큐에 삽입
            queue.offer(i);
            visited[i] = true;
            
            while(!queue.isEmpty()){
                int winner = queue.poll();
                //i번째 선수가 진 선수들을 통해 그래프 탐색
                for(int loser : list[winner]){
                	//이미 확인한 선수라면 건너 뛰기
                    if(visited[loser]) continue;
                    //방문처리
                    visited[loser] = true;
                    //큐에 담기
                    queue.offer(loser);
                    //i번째 선수의 승수 카운팅
                    player[i].win++;
                    //진 선수의 패수 카운팅
                    player[loser].lose++;
                }
            }            
        }      
        for(Node p : player){
            //System.out.println(player[i]);
        	//현재 선수가 모든 선수에 대해 승패가 나온 경우
            if(p.win+p.lose==n-1) answer++;
        }
        return answer;

	}
	
}