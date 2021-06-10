import java.util.*;
import java.util.Map.Entry;
class Solution_LV1_완주하지못한선수_이상민_solved_Hash {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        //이 문제는 결국 엄청 많은 문자열을 어떻게 효율적으로 비교할 것인지에 대한 것
        //해싱(Hashing)은 문자열을 빠르게 검색하고 비교할 수 있도록 해주는 기법
        

        HashMap<String, Integer> hm = new HashMap<>();
        //HashMap의 key는 중복이 없으므로 여러 이름을 넣을 수 없다
        //=> hm.getOrDefault(key, 0) : key가 이미 존재한다면 value를 반환, 없다면 0을 반환
        //=> 중복된 이름의 수를 셀 수 있음
        for(String name : participant) hm.put(name, hm.getOrDefault(name, 0)+1);
        
        //해쉬는 중복된 값이 있으면 마지막으로 들어온 값으로 갱신됨, 완주한 사람은 value-1 해서 다시 넣어줌
        //만약 같은 이름의 사람이 2명이고 완주자 목록에 있다면 
        //key : name, value : 2 => key : name, value : 1 (1명은 완주하지 못함)
        //만약 같은 이름의 사람이 1명일 때 그 사람이 completion배열에 있으면
        //key : name, value : 1 => key : name, value : 0 (완주)
        for(String name : completion) hm.put(name, hm.get(name)-1);
        
        //방법 1. hm.keySet() : 해쉬맵의 key 전체를 반환
        // for(String key : hm.keySet()){
        //     //hm.get(key) : 해쉬맵에서 key 해당 하는 value 값 가져오기
        //     //만약 value가 0이라면 완주
        //     //value가 0이 아니라면 완주 X
        //     if(hm.get(key)!=0){
        //         answer=key;
        //         break;
        //     }
        // }
        
        //방법 2. hm.keySet() : 해쉬맵의 entry 객체 전체를 반환
        for(Entry<String, Integer> entry : hm.entrySet()){
            if(entry.getValue() > 0){
                answer=entry.getKey();
                break;
            }
        }
        
        //map에 하나를 put 할 때마다 map 안에 같은 문자열이 있는지를 검색해야하니 느릴 것 같지만,
        //해싱 기법의 특성 상 검색 및 문자열 비교가 매우 빠르기 때문에 일반적인 방법보다 훨씬 빠름
        return answer;
    }
}