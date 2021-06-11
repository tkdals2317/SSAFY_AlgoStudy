import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<completion.length; i++)
            if(!map.containsKey(completion[i])) map.put(completion[i],1);
		    else map.put(completion[i], map.get(completion[i])+1);
		
		for(int i=0; i<participant.length; i++)
			if(!map.containsKey(participant[i])) return participant[i];
			else if(map.get(participant[i])<=1) map.remove(participant[i]);
			else map.put(participant[i], map.get(participant[i])-1);
        
        return "";
    }
}