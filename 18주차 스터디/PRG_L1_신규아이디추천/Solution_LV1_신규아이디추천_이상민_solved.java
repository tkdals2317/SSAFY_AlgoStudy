import java.io.*;
import java.util.*;
class Solution_LV1_신규아이디추천_이상민_solved {
	public String solution(String new_id) {

        //step 1. 대문자를 소문자로 변환
        new_id = new_id.toLowerCase();
        System.out.println("STEP 1 : "+new_id);
        //step 2. !, @, #, * 문자 제거
        //^는 not을 뜻함 즉 뒤에 나오는 것들은 제외하고라는 뜻,
        //a-z는 알파벳
        //[0-9]는 숫자
        //\\-_.를 제외하고 모두 ""로 변환
        new_id = new_id.replaceAll("[^a-z[0-9]\\-_.]*","");
        System.out.println("STEP 2 : "+new_id);
        //step 3. '...'과 '..'를 '.'로 변경
        //\\.는 '.'을 뜻함
        //{2,} 2개 이상이면 "."으로 바꾼다
        new_id = new_id.replaceAll("\\.{2,}",".");
        System.out.println("STEP 3 : "+new_id);
        //step 4. '.'가 맨 앞 또는 맨 뒤에 있다면 제거
        //^[]는 문자열의 시작 단어가 []안에 있는 단어로 시작한다면
        //|는 or
        //[]$는 문자열의 끝 단어가 []안에 있는 단어로 끝난다면 
        new_id = new_id.replaceAll("^[.]|[.]$","");
        System.out.println("STEP 4 : "+new_id);
        //step 5. 빈 문자열이면 'a'를 추가
        if(new_id.length()==0){
            new_id += 'a';
        }
        System.out.println("STEP 5 : "+new_id);
        
        //step 6. 16자 이상이면 15자를 제외하고 제거
        if(new_id.length()>15){
            new_id = new_id.substring(0,15);
            new_id = new_id.replaceAll("[.]$","");
        }
        System.out.println("STEP 6 : "+new_id);
        
        //step 7. new_id가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될때까지 끝에 붙임
        if(new_id.length()<=2){
            for(int i = new_id.length(); i< 3; i++){
                new_id += new_id.charAt(new_id.length()-1);
            }
        }
        System.out.println("STEP 7 : "+new_id);
        
        //System.out.println(new_id);
        return new_id;
    }
}