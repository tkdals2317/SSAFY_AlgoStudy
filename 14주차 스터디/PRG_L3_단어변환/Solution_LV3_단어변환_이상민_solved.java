import java.util.*;
import java.io.*;
class Solution_LV3_단어변환_이상민_solved {
    static boolean [] visited;
    static class Word{
        String word;
        int level; 
        public Word(String word, int level){
            this.word = word;
            this.level = level;
        }
    }    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        ArrayDeque<Word> queue = new ArrayDeque<Word>();
        
        //시작단어 큐에 삽입
        queue.offer(new Word(begin, 0));
        int level = 0;
        while(!queue.isEmpty()){      
            Word curr = queue.poll();
            System.out.println(curr.word);
            if(curr.word.equals(target)) {
                answer = curr.level;
                break;
            }
            //전체 단어들에 대해 현재 단어와 비교
            for(int i=0; i<words.length; i++){
                if(!visited[i] && spellCheck(curr.word, words[i])){
                    visited[i] = true;
                    queue.add(new Word(words[i], curr.level + 1));
                }
            }
        } 
        return answer;
    }
    //한번에 한개의 단어만 바꿀 수 있으므로 다른 스펠링이 하나인 것만 true 리턴 두개 이상 일시 false리턴
    static boolean spellCheck(String cur, String next){
        int count = 0;
        for(int i=0; i<cur.length(); i++){
            //다른 스펠링이 있는 경우 카운팅 증가
            if(cur.charAt(i) != next.charAt(i))
                count++;
            //만약 다른 스펠링이 두개 이상이면 변경될 수 있는 단어 후보에서 탈락
            if(count > 1) 
                return false;
        }
        //스펠링이 하나만 차이인 경우에만 true리턴
        return true;
    }    
    
}