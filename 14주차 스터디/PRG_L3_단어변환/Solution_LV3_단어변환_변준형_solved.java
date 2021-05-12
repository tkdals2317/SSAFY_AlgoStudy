import java.util.*;

class Solution {
    private static int n;
    private static String[] vocas;
    private static boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 10;
        
        vocas = words;
        visit = new boolean[words.length + 1];
        
        boolean flag = true;
        if (!test(target)) {
            answer = 0;
        } else {
            for (int i=0; i<vocas.length; i++) {
                if (check(begin, vocas[i])) {
                    //begin -> vocas[i] 변환 가능할 때 탐색 시작
                    Arrays.fill(visit, false);
                    //System.out.println(vocas[i]);
                    int result = dfs(i, vocas[i], target, 1);
                    answer = Math.min(answer, result);
                }
            }
        }
        
        
        return answer;
    }
    
    public int dfs(int start, String word, String target, int cnt) {
        
        if (word.equals(target)) {
            //탐색한 문자와 target이 같으면 끝낸다.
            return cnt;
        }
        
        if (visit[start]) {
            //방문처리 된곳을 찾아오면 다시 되돌려보낸다.
            return cnt;
        }
        
        //해당 원소 방문처리
        visit[start] = true;
        
        int num = 0;
        for (int i=0; i<vocas.length; i++) {
            if (i != start && !visit[i] && check(word, vocas[i])) {
                //방문 x
                //같은 문자 x
                //단어가 2개이상 같아야함
                //System.out.println("start :: " + start + " , i :: " + i + " , word :: " + word + " , vocas[i] :: " + vocas[i]);
                num = dfs(i, vocas[i], target, cnt+1);
            }
        }
        return num;
        
    }
    
    public boolean check(String str1, String str2) {
        
        int cnt = 0;
        for (int i=0; i<str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            
            if (c1 != c2) cnt++;
        }
        
        if (cnt == 1) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean test(String target) {
        
        for (int i=0; i<vocas.length; i++) {
            if (target.equals(vocas[i])) {
                return true;
            }
        }
        return false;
    }
}