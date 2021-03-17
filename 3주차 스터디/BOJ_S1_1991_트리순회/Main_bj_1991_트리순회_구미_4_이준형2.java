package baekjun;
import java.io.*;
import java.util.*;

public class Main_bj_1991_트리순회_구미_4_이준형2 {

	static class Node {
		char data;
		Node left;
		Node right;

		public Node() {
			super();
		}

		public Node(char data) {
			super();
			this.data = data;
		}
	}

	static class Tree {
		// 루트 노드 생성
		Node Root;

		public void create(char data, char left_data, char right_data) {
			if (Root == null) { // 루트노드가 없을때
				if (data != '.')
					Root = new Node(data);
				if (left_data != '.')
					Root.left = new Node(left_data);
				if (right_data != '.')
					Root.right = new Node(right_data);
			}
			// 루트노드가 있을 경우 탐색하여서 넣기
			else
				search(Root, data, left_data, right_data);

		}

		//재귀로 구현
		void search(Node node, char data, char left_data, char right_data) {
			if(node==null) return;
			//노드가 찾는데이터일 경우
			else if(node.data==data) {
				if(left_data!='.')node.left=new Node(left_data);
				if(right_data!='.')node.right=new Node(right_data);
			}
			//아니면 계속 찾도록
			else {
				search(node.left, data, left_data, right_data);
				search(node.right, data, left_data, right_data);
			}
			
		}
		
		void printTree_1(Node node) {
			System.out.print(node.data);
			if(node.left!=null)
				printTree_1(node.left);
			if(node.right!=null)
				printTree_1(node.right);
		}
		void printTree_2(Node node) {
			if(node.left!=null)
				printTree_2(node.left);
			System.out.print(node.data);
			if(node.right!=null)
				printTree_2(node.right);
		}
		void printTree_3(Node node) {
			if(node.left!=null)
				printTree_3(node.left);
			if(node.right!=null)
				printTree_3(node.right);
			System.out.print(node.data);
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());


		Tree tree=new Tree();

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			tree.create(a, b, c);
		}

		tree.printTree_1(tree.Root);
		System.out.println();
		tree.printTree_2(tree.Root);
		System.out.println();
		tree.printTree_3(tree.Root);
		
		br.close();
	}



}
