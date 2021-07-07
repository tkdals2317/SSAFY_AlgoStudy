import java.io.*;
import java.util.*;

public class Main_S1_13335_트럭_변준형_solved {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<Integer> truck = new LinkedList<Integer>();	//	트럭 저장 큐
        Queue<Integer> bridge = new LinkedList<Integer>();	//	다리위 트럭 저장 큐
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());			//	n : 트럭 수
        int w = Integer.parseInt(st.nextToken());			//	w : 다리길이
        int L = Integer.parseInt(st.nextToken());			//	L : 최대하중

        int sec=0,tmp_w=0;									//	sec : 초, tmp_w : 다리위 트럭 무게
        
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++)
        	truck.add(Integer.parseInt(st.nextToken()));
        
        //계산
        while(!truck.isEmpty()) {
        	int t =truck.remove();
        	while(true) {
        		if(bridge.isEmpty()) {						//다리 비었을 때
            		bridge.add(t);
            		tmp_w+=t;
            		sec++;
            		break;
            	}
            	else if(bridge.size() == w)					//다리 꽉찼을 때
            		tmp_w-=bridge.remove();
            	else {
            		if(t+tmp_w <= L) {						//무게 조건 맞을 때
            			bridge.add(t);
            			tmp_w+=t;
            			sec++;
            			break;
            		}
            		else {									//무게 조건 안 맞을 때
            			bridge.add(0);						//조건 안맞아서 지나칠 때 무게에 영향안주는 0을 넣어준다.
            			sec++;
            		}
            	}
        	}
        	
        }
        
       System.out.println(sec+w);
    }
} 