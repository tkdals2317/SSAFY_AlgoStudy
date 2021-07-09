import java.util.HashMap;

class Solution {
	static int ans=1;
    public int solution(String[][] clothes) {
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<clothes.length; i++)
			map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 1)+1);
        
		map.forEach((key, value) -> {	ans*=value; });
        return ans-1;
    }
}