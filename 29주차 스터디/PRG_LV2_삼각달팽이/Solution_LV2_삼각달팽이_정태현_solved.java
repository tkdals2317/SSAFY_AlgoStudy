package algorithm.programmers;

class Solution {
    public int[] solution(int n) {        
        int[][] arr = new int[n][n];
        //시작하면서부터 연산을 수행하기 때문에 row는 -1부터
        int r = -1, c = 0; 
        int num = 1;
        
        //아래, 오른쪽, 대각 순서
        //높이가 n인 삼각형은 아래, 오른쪽, 대각 순으로 n번 수행됨
        for(int i=0; i<n; i++) {
            //방향 순서대로 n번, n-1번, n-2번... 1씩 빼면서 수행됨
            for(int j=i; j<n; j++) {
                //아래
                if(i%3==0) r++;
                //오른쪽
                else if(i%3==1) c++;
                //대각
                else if(i%3==2) {
                    r--;
                    c--;
                }
                arr[r][c] = num++;
            }
        }
        
        //ex) n=5
        //5번아래, 4번오른쪽 ... 1번ㅇㅇ
        //n + n-1 + n-2 + ... 1
        int[] answer = new int[(n*(n+1))/2];
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j]==0) break;
                answer[idx++] = arr[i][j];
            }
        }
        
        return answer;
    }
}