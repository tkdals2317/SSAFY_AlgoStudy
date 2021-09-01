package algorithm.programmers;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int l = 0, r = 0;
        
        //일단 어느쪽이 왼쪽인지 오른쪽인지 판별
        if(a > b) {
            l = b;
            r = a;
        } else {
            l = a;
            r = b;
        }
        
        while(true) {
            //종료조건
            if(l%2==1 && r==l+1) {
                break;
            }
            
            //1 과 2는 그 다음라운드 1번 포트로
            if(l%2==0) l = l / 2;
            else l = (l / 2) + 1;
            
            if(r%2==0) r = r / 2;
            else r = (r / 2) + 1;
            
            answer++;
        }

        return answer;
    }
}