package programmers;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int x=yellow;x>=1;x--){
            if(yellow%x==0){
                int y=yellow/x;
                if(brown==(2*x+2*y+4)){
                    answer[0]=x+2;
                    answer[1]=y+2;
                    return answer;
                }
            }
        }        
        
        return answer;
    }
}
