import java.util.*;
public class Main{
public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String n = scan.next();
        int m = scan.nextInt();
        int [] a = new int[10]; //고장난 버튼을 표시할 배열
        
        for(int i=0; i<m; i++)  //고장난 버튼엔 -1을 넣는다.
            a[scan.nextInt()] = -1;
 
        if(n.equals("100")) 
            System.out.println(0);
        else {
            int min = Integer.MAX_VALUE;
            String v = "";//0 ~ 1000000까지의 숫자를 문자열화 하기위한~
            String closer = ""; //리모컨으로 입력할 수 있는 n과 가장 가까운 채널
            
            for(int i = 0; i<1000000; i++) {
                boolean isOk = true;
                v = i+""; //숫자를 문자열로 저장
                for(int j=0; j<v.length(); j++) { //고장난 버튼이 포함되어 있는지 검사
                    if(a[v.charAt(j)-'0'] == -1) { 
                        isOk = false;
                        break;
                    }
                }
                if(isOk) { //v에 고장난 버튼이 없으면 가장 가까운 채널을 찾는다. n-v로  + 또는 - 버튼을 몇번 눌러야하는지 계산
                    if(min > Math.abs(Integer.parseInt(n)-Integer.parseInt(v))) { 
                        min = Math.abs(Integer.parseInt(n)-Integer.parseInt(v));
                        closer = v; 
                    }
                }
            }
            
            int result1 = Math.abs(Integer.parseInt(n)-100); //result1에는 현재채널인 100에서 +와-만으로 n까지 갈때의 횟수를 저장
            if(closer.equals("")) //가장 가까운 수가 비어있으면 그냥 result1출력
                System.out.println(result1);
            else { //result2에는 n-closer로 closer에서 n까지 가기위한 횟수에 closer를 누르기위한 버튼 입력 횟수인 closer의 길이를 더한다.
                int result2 = Math.abs(Integer.parseInt(n)-Integer.parseInt(closer))+closer.length(); 
                if(result1>result2)
                    System.out.println(result2);
                else
                    System.out.println(result1);
            }
        }
    }
}

