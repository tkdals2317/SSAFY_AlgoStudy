import java.util.*;

class Solution {
    
    static class pos{
        int i;
        int j;
        public pos(int i,int j){
            this.i=i;
            this.j=j;
        }
		@Override
		public String toString() {
			return "pos [i=" + i + ", j=" + j + "]";
		}
    }
    
    static int[][] map; //맵
    static int[] visit; //방문체크
    static int N;   //맵 크기
    static int max_num; //가장큰값
        
    public int solution(int n, int[][] edge) {
        N=n;    //정점 개수
        
        map=new int[n+1][n+1];  //맵 생성
        for(int t=0;t<edge.length;t++){
            int i=edge[t][0];
            int j=edge[t][1];
            map[i][j]=1;
            map[j][i]=1;
        }
        
        max_num=Integer.MIN_VALUE;
        
        find();

        for(int k=1;k<=N;k++){
            System.out.print(visit[k]+" ");
        }System.out.println();
        
        System.out.println(max_num);

        int answer=0;
        for(int k=1;k<=N;k++){
            if(visit[k]==max_num)
                answer++;
        }

        return answer;
    }
    
    //다른방식 bfs 활용
    static void find(){
        ArrayDeque<pos> queue = new ArrayDeque<pos>();
        queue.offer(new pos(1,1));
        visit=new int[N+1]; //방문체크용
        int num=1;
        visit[1]=num; //체크
        
        while(!queue.isEmpty()){
            num++;
            int size=queue.size();
            for(int s=0;s<size;s++){
                pos tmp=queue.poll();
                int i=tmp.i;    //출발점
                int j=tmp.j;    //도착점
                System.out.println(i+" "+j);
                
                for(int k=1;k<=N;k++){
                    if(map[j][k]==1&&visit[k]==0){
                        visit[k]=num;
                        max_num=Math.max(max_num,num);
                        queue.offer(new pos(j,k)); 
                    }
                }
            } 
        }

    }
    
}