import java.util.*;
import java.io.*;
class Solution_LV3_합승택시요금_이상민_solved {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;        
        int [][] distance = new int[n+1][n+1];
        
        final int INF = 9999999;
        //자기 자신 제외하고 INF로 초기화
        for(int i = 1; i<=n ; i++){
            for(int j = 1; j<=n ; j++){
                if(i==j) continue;
                distance[i][j] = INF;             
            }
        }
        //인접 행렬
        for(int i = 0; i < fares.length; i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int weight = fares[i][2];
            distance[from][to] = weight; 
            distance[to][from] = weight; 
        }
        
        //플루이드와샬로 전체 정점에 대한 최소거리 구하기
        for(int k = 1; k <= n; k++){ //경유지
            for(int i = 1; i <= n; i++){ //출발지
                for(int j = 1; j <= n; j++){ //도착지
                    if(distance[i][j] > distance[i][k]+distance[k][j]){
                       distance[i][j] = distance[i][k]+distance[k][j];
                    }
                }
            }
        }
                
        // for(int i = 1; i <= n; i++){
        //     for(int j = 1; j <= n; j++){
        //         System.out.print(distance[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        
        //경유지(즉 합승으로 같이 간 정점)를 들렸을 경우가 그냥 따로 갔을때보다 작다면 갱신 
        answer = distance[s][a]+distance[s][b];
        for(int k = 1; k <= n ; k++){
            answer = Math.min(answer, distance[s][k]+distance[k][a]+distance[k][b]);
        }
        
        return answer;
    }
}