
public class Solution_LV2_타켓넘버_정소영_solved {
}

class Solution {
	static int answer=0;
	public int solution(int[] numbers, int target) {
		dfs(numbers, numbers.length, target, 0, 0);
		return answer;
	}
	
	static void dfs(int[] numbers, int len, int target, int cnt, int sum){
		
		if(cnt==len){
			if(sum==target) answer++;
			return;
		}
		
		dfs(numbers, len, target, cnt+1, sum+numbers[cnt]);
		dfs(numbers, len, target, cnt+1, sum-numbers[cnt]);
	}
}