class Solution {
    static int ans;
    
    public int solution(int[] numbers, int target) {
        ans=0;
        
        check(0,0,numbers,target);
               
        return ans;
    }
    
    
    public void check(int idx,int num,int[] numbers,int target){
        if(idx==numbers.length){
            if(target==num)
                ans++;
            return;
        }

        check(idx+1,num+numbers[idx],numbers,target);
        check(idx+1,num-numbers[idx],numbers,target);

    }
}