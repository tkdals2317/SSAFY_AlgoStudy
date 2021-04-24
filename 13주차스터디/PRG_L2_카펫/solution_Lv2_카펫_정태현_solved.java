package algorithm.programmers;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        brown = (brown - 4) / 2;
        for(int i = 1; i <= brown/2; i++) {
            if(i*(brown-i)==yellow) {
                answer[0] = (brown-i) + 2;
                answer[1] = i + 2;
                break;
            }
        }
        return answer;
    }
}