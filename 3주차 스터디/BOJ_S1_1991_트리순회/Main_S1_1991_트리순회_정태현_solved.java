package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S1_1991_solved {
	static int[][] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		tree = new int[N][2];
		for(int tc=0; tc<N; tc++) {
			int p;
			String s = br.readLine();
			p = s.charAt(0) - 'A';
			tree[p][0] = s.charAt(2) - 'A'; //left child
			tree[p][1] = s.charAt(4) - 'A'; //right child
		}
		preo(0); //0 = A
		System.out.println();
		ino(0);
		System.out.println();
		posto(0);
		System.out.println();
	}
	
	public static void preo(int r) {
		if(r == -19) return; //.이면 return
		System.out.print((char)(r+'A')); // ex) 65 => A로 출력
		preo(tree[r][0]); // Left
        preo(tree[r][1]); // Right
	}
	
	public static void ino(int r) {
		if(r == -19) return; //.이면 return
		ino(tree[r][0]); // Left
		System.out.print((char)(r+'A')); // ex) 65 => A로 출력
        ino(tree[r][1]); // Right
	}
	
	public static void posto(int r) {
		if(r == -19) return; //.이면 return
		posto(tree[r][0]); // Left
        posto(tree[r][1]); // Right
        System.out.print((char)(r+'A')); // ex) 65 => A로 출력
	}
}
