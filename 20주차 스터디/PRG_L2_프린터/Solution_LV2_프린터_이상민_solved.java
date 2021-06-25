import java.util.*;
import java.io.*;
class Solution_LV2_프린터_이상민_solved {
    static class Word implements Comparable<Word>{
        int num;
        int priority;
        public Word(int num, int priority){
            this.num = num;
            this.priority = priority;
        }
        public String toString(){
            return "["+this.num+", "+this.priority+"]";
        }
        
        public int compareTo(Word o){
        	//중요도가 높은 순서로 queue에서 나옴(순서 주의) 
            return Integer.compare(o.priority, this.priority);
        }

    } 
        
    public int solution(int[] priorities, int location) {
        int answer = 0;

        System.out.println(Arrays.toString(priorities));
        PriorityQueue<Word> queue = new PriorityQueue<Word>();
        
        for(int i=0; i<priorities.length; i++){
            queue.offer(new Word(i, priorities[i]));
        }
    
        while(!queue.isEmpty()){
            System.out.println(queue);
            for(int i=0; i<priorities.length; i++){
            	
                if(queue.peek().priority==priorities[i]){
                	//i의 priorities와 중요도가 같으면 출력 
                    System.out.println(queue.poll()+" "+i);
                    //소요된 시간 증가
                    answer++;
                    //만약 요청한 문서의 번호와 방금 큐에서 뺀 인덱스가 일치하다면 종료
                    if(location==i){
                        queue.clear();
                        break;
                    }
                }
                
            }
        }
        return answer;
    }
}