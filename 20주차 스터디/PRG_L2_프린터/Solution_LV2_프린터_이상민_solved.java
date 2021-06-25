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
                    System.out.println(queue.poll()+" "+i);
                    answer++;
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