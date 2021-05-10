import java.util.*;
import java.io.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        char [] cArr = s.toCharArray();
        //System.out.println(Arrays.toString(cArr));
        //첫 글자가 소문자일 경우 대문자로 변경
        if(cArr[0]>=97&&cArr[0]<=122) cArr[0] = (char)(cArr[0]-32);
        //뒤에서부터 거꾸로 검사
        for(int i = cArr.length-1; i > 0 ; i--){
            //이전 글자가 공백이고 현재 글자가 소문자일시
            if(cArr[i-1]==' '&& cArr[i]>=97 && cArr[i]<=122){
                //현재 글자를 대문자로 변환
                cArr[i] = (char)(cArr[i]-32);
            }
        }
        System.out.println(cArr);
        //for(int i = 0; i < cArr.length ; i++){
        //    sb.append(cArr[i]);
        //}
        //String answer = sb.toString();
        //char 배열을 String으로 변환하는 함수 copyValueOf();
        String answer = String.copyValueOf(cArr);
        return answer;
    }
}