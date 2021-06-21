import java.io.*;
import java.util.Map.Entry;
import java.util.*;
class Solution_LV2_메뉴리뉴얼_이상민_solved {
    // 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성
    // 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
    // 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합
    
    static HashMap<String, Integer> hm;
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<String>();
        // 각 메뉴 별 카운팅?
        // 조합?
        // 해쉬?
        
        //메뉴 오름차순 정렬(XY나 YX나 같은 값이기 때문!) 
        //순서가 중요하지 않고 순차적으로 만드는 조합을 사용하므로
        for(int i=0; i<orders.length;i++){
            char[] cArr = orders[i].toCharArray();
            Arrays.sort(cArr);
            orders[i] = String.valueOf(cArr);
        }
        
        for(int i=0; i<course.length; i++){
            hm = new HashMap<String, Integer>();
            for(int j=0; j<orders.length; j++){
                //조합을 만들 수 있는 수가 주문한 총 요리수 보다 작아야 조합을 짤 수 있다
                if(course[i]<=orders[j].length()){
                    //System.out.println(orders[j].length() + " "+course[i]);
                    //조합을 구성할 StringBuilder
                    StringBuilder sb = new StringBuilder();
                    comb(orders[j], 0, course[i], sb);
                }
            }
            //현재 코스를 구성하는 단품메뉴들의 갯수로 만든 조합 중 가장 많이 주문된 음식조합의 횟수 구하기
            int max = Integer.MIN_VALUE;
            for(String key : hm.keySet()){
                System.out.println(key+ " "+ hm.get(key));
                max = Math.max(max, hm.get(key));
            }
            //여러개 일 수 있으므로 
            for(Entry<String, Integer> entry : hm.entrySet()){
                //최소 두명 이상의 손님으로 부터 주문한 조합
                if(max>=2 && entry.getValue()==max){
                    answer.add(entry.getKey());
                }
            }
        }
        //오름차순 정렬
        Collections.sort(answer);
        System.out.println(answer);
        return answer;
    }
    //현재 주문(order)으로 코스요리를 구성하는 단품메뉴들의 갯수(courseCnt)로 만들 수 있는 요리 조합(sb) 만들어보기
    static void comb(String order, int cnt, int courseCnt, StringBuilder sb){
        if(sb.length() == courseCnt){
            //System.out.println(sb.toString());
            //해시에 추가해줄 때 getOrDefault로 갯수 카운팅 해주기
            hm.put(sb.toString(), hm.getOrDefault(sb.toString(), 0)+1);
            return;
        }
        for(int i = cnt; i<order.length(); i++){
            //선택
            sb.append(order.charAt(i));
            comb(order, i+1, courseCnt, sb);
            //비선택
            sb.deleteCharAt(sb.length()-1);
        }
    }
}