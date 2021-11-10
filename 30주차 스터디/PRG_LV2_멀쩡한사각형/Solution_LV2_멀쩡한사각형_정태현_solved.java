package algorithm.programmers;

class Solution {
    public long solution(long w, long h) {
        long answer = 1;
        
        long gcd = gcd(w, h);
        
        answer = (w * h) - (((w / gcd) + (h / gcd) - 1) * gcd);
        
        return answer;
    }
    
    public long gcd(long a, long b){
      if(a % b == 0)
          return b;
      return gcd(b,a%b);
  }
}