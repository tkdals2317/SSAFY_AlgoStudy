import java.io.*;
import java.util.*;
class Solution_LV2_순위검색_이상민_X {
    
    //언어 : cpp, java, python, - 
    //직군 : backend, frontend, -
    //경력 : junior, senior, - 
    //소울푸드 : chicken, pizza, - 
    //코딩테스트 점수 : 1~100,000
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++){
            
            StringTokenizer st = new StringTokenizer(query[i], " ");
            String language = st.nextToken();
            st.nextToken();
            String position = st.nextToken();
            st.nextToken();
            String career = st.nextToken();
            st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            //System.out.println(i+"번째 쿼리 : "+language+", "+position+", "+career+", "+food+", "+score);
            
            int cnt = 0;
            for(int j = 0; j < info.length; j++){
                boolean b = false;
                st = new StringTokenizer(info[j]);
                String alanguage = st.nextToken();
                String aposition = st.nextToken();
                String acareer = st.nextToken();
                String afood = st.nextToken();
                int ascore = Integer.parseInt(st.nextToken());
                
                if(language.equals("-")||language.equals(alanguage)){
                    b = true;
                }else continue;
                if(position.equals("-")||position.equals(aposition)){
                    b = true;
                }else continue;
                if(career.equals("-")||career.equals(acareer)){
                    b = true;
                }else continue;
                if(food.equals("-")||food.equals(afood)){
                    b = true;
                }else continue;
                if(score <= ascore){
                    b = true;
                }else continue;
                
                if(b){
                    cnt++;
                    //System.out.print("지원자 "+j+" : "+alanguage+", "+aposition+", "+acareer+", "+afood+", "+ascore);
                    //System.out.println(" 합격");
                }
                
            }
            answer[i] = cnt;
        }
        return answer;
    }
}