import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_1717_집합의표현_변준형_solved {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int N = Integer.parseInt(st.nextToken());			//	숫자
		 int M = Integer.parseInt(st.nextToken());			//	실행횟수
		 
		 parent = new int[N+1];
		 for (int i = 1; i <= N; i++) {						//	부모를 자기자신으로 만들기
			 parent[i] = i;
	        }
		 
		 for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());	//	0이면 합집합 1이면 부모 같은지 확인
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());		//	두 수
			
			if(command == 0) {
				union(a, b);
			}else {
				boolean result = SameParent(a,b);
				if(result)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}
	
    // x의 부모를 찾는 연산
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
	// y의 부모를 x의 부모로 변경 (x > y 일 경우, 반대)
    public static void union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	if (x != y) {
    		if (x < y) {
    			parent[y] = x;
    		} else {
    			parent[x] = y;
    		}
    	}
	}
    
    // x와 y의 부모가 같은지 확인
	public static boolean SameParent(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return true;
		}
		return false;
	}
}
