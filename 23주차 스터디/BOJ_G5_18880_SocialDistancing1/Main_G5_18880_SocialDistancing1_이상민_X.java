import java.util.*;
import java.io.*;
public class Main_G5_18880_SocialDistancing1_이상민_X {
	static int N;
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int D;
		public Node(int start, int end, int D) {
			this.start = start;
			this.end = end;
			this.D = D;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.D, o.D);
		}
		
		public String toString() {
			return start+" ~ "+ end + " D = "+ D; 
		}
		
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_18880_SocialDistancing1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] str  = br.readLine().toCharArray();
		//0이 3개 이상 연속나와야함
		//위 조건을 만족하고 가장 짧은 곳 두군데에 소를 놓는다
		//System.out.println(str);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		for(int i = 0; i < N; i++){
			if(str[i]=='1') list.add(i+1);
		}
		list.add(N);
		//System.out.println(list);
		PriorityQueue<Node> diffList = new PriorityQueue<Node>();
		for(int i = 0; i < list.size()-1; i++) {
			int start = list.get(i);
			int end = list.get(i+1);
			int D = end-start;
			if(D>3) diffList.add(new Node(start, end, D));
		}
		int first = diffList.poll().start+1;
		str[first] = '1';
		if(diffList.peek().start-first>3) diffList.offer(new Node(first, diffList.peek().start, diffList.peek().start-first));
		str[diffList.poll().start+1] = '1';
		
		boolean isZero = false;
		int zCnt = 0;
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(str[i]=='1') {
				isZero = false;
				zCnt = 0;
			}else if(str[i]=='0'&&!isZero) {
				max = Math.max(max, ++zCnt);
				isZero = true;
			}else if(str[i]=='0'&&isZero){
				max = Math.max(max, ++zCnt);
			}
		}
		
		System.out.println(max);
		br.close();
	}
}
