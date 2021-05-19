import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_S3_15649_N과M1_변준형_solved {
    static boolean[] visit;
    static int[] set;
    static int[] arr;
    
    /*
    static char[] arr = { 'a', 'b', 'c', 'd', 'e' };
    static int r = 3;
    static boolean[] visit;
    static char[] set; 
    */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		arr = new int[a];
		for (int i = 0; i < a; i++) {
			arr[i] = i+1;
		}
        set = new int[b];
        visit = new boolean[a];
        nPr(0,a,b);
	}
    public static void nPr(int len, int a, int b) {// 순열
        if(len==b) {
        	for(int val : set)
        		System.out.print(val + " ");
        	System.out.println();
            return;
        }
        for(int i=0;i<a;i++) {
            if(!visit[i]) {
                set[len]=arr[i];
                visit[i]=true;
                nPr(len+1,a,b);
                visit[i]=false;
            }
        }
    }
    /*
     set = new char[r];
     visit = new boolean[arr.length];
     System.out.println("==순열==");
     nPr(0);
     System.out.println("==중복 순열==");
     nPir(0);
     System.out.println("==조합==");
     nCr(0,0);
     System.out.println("==중복 조합==");
     nHr(0,0);
     setList = new ArrayList<>();
     System.out.println("==부분 집합==");
     subset(0,0);
    }
 
    public static void nCr(int len, int k) { // 조합
        if (len == r) {
            System.out.println(Arrays.toString(set));
            return;
        }
        for (int i = k; i < arr.length; i++) {
            set[len] = arr[i];
            nCr(len + 1, i + 1);
        }
    }
 
    public static void nHr(int len, int k) { // 중복조합
        if (len == r) {
            System.out.println(Arrays.toString(set));
            return;
        }
        for (int i = k; i < arr.length; i++) {
            set[len] = arr[i];
            nHr(len + 1, i);
        }
    }
    
    public static void nPr(int len) {// 순열
        if(len==r) {
            System.out.println(Arrays.toString(set));
            return;
        }
        for(int i=0;i<arr.length;i++) {
            if(!visit[i]) {
                set[len]=arr[i];
                visit[i]=true;
                nPr(len+1);
                visit[i]=false;
            }
        }
    }
 
    public static void nPir(int len) {// 중복순열
        if(len==r) {
            System.out.println(Arrays.toString(set));
            return;
        }
        for(int i=0;i<arr.length;i++) {
            set[len]=arr[i];
            nPir(len+1);
        }
    }
 
    static ArrayList<Character> setList;
    public static void subset(int len, int k) {// 부분집합
        System.out.println(setList);
        if(len==arr.length) {
            return;
        }
        for(int i=k;i<arr.length;i++) {
            setList.add(arr[i]);
            subset(len+1,i+1);
            setList.remove(setList.size()-1);
        }
     */
     
    
}
