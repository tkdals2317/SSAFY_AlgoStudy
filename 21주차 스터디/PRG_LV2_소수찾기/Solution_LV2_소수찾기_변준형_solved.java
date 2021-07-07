import java.util.*;

class Solution_LV2_소수찾기_변준형_solved{
    public static boolean visit[];
    public static HashSet<Integer> set = new HashSet<Integer>();
    public static String nums;
    public int solution(String numbers) {
        int answer = 0;
        nums = numbers;
        visit = new boolean[nums.length()];
        Arrays.fill(visit,false);
        
        //1. 종이조각으로 만들 수 있는 수 저장
        for(int i=0;i<nums.length();i++){
            visit[i]=true;
            dfs(i,String.valueOf(nums.charAt(i)));   
            visit[i]=false;
        }
        
        //2. 소수 판별
        Iterator iter = set.iterator();
        while(iter.hasNext()){
            boolean flag= false;
            Integer n = (Integer)iter.next();
            if(n == 1 || n==0)
                flag=true;
            for(int i =2;i<n;i++){
                    if(n%i==0)
                        flag=true;
            }
            if(!flag){
                System.out.println(n);
                answer++;
            }
        }
        System.out.println(set);
        return answer;
    }
    
    public void dfs(int idx, String num) {
        //종료조건
        //자리수 이상?
        if(num.length()>nums.length()){
            return;
        }
        set.add(Integer.parseInt(num));
        
        for(int i=0;i<nums.length();i++){
            if(visit[i])
                continue;
            
            visit[i]=true;
            dfs(i,num+nums.charAt(i));
            visit[i]=false;
            
        }
        
    }
}
