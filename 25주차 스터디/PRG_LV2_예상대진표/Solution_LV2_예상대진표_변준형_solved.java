
class Solution_LV2_예상대진표_변준형_solved {
    public int solution(int n, int a, int b) {
		int cnt = 1;
		while(true) {
			if((a % 2 == 0 && a - 1 == b) || (a % 2 != 0 && a + 1 == b)) break;
			
			a = a % 2 == 0 ? a/2 : a/2+1;
			b = b % 2 == 0 ? b/2 : b/2+1;
			cnt++;
		}
		
        return cnt;
    }
}