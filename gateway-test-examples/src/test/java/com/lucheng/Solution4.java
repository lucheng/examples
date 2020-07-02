package com.lucheng;

public class Solution4 {
	private int res;

	public int maxAncestorDiff(TreeNode root) {
		dsf(root, root.val, root.val);
		return res;
	}

	private void dsf(TreeNode node, int max, int min) {
		if (node == null) {
			return;
		}
		max = Math.max(node.val, max);
		min = Math.min(node.val, min);

		res = Math.max(res, Math.max(Math.abs(node.val - max), Math.abs(node.val - min)));

		dsf(node.left, max, min);
		dsf(node.right, max, min);
	}

	public static void main(String[] args) {
		Solution4 s = new Solution4();

		TreeNode root = new TreeNode(8);
		TreeNode root2 = new TreeNode(3);
		TreeNode root3 = new TreeNode(10);
		TreeNode root4 = new TreeNode(1);
		TreeNode root5 = new TreeNode(6);
		TreeNode root6 = new TreeNode(14);

		root.left = root2;
		root.right = root3;
		root2.left = root4;
		root2.right = root5;

//		TreeNode root = new TreeNode(2);
//		TreeNode root2 = new TreeNode(0);
//		TreeNode root3 = new TreeNode(1);
//		root.right = root2;
//		root2.left = root3;
		

		System.out.println(s.maxAncestorDiff(root));
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
