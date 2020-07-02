package com.lucheng.arithmetic.search;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SinglyLinkedList {
	/**
	 * 单链表节点
	 * 
	 * @param <T>
	 */
	private static class SingleNode<T> {
		public SingleNode<T> next;
		public T data;

		public SingleNode(T data) {
			this.data = data;
		}

		public T getNextNodeData() {
			return next != null ? next.data : null;
		}
	}

	/**
	 * 判断是否有环 快慢指针法
	 * 
	 * @param node
	 * @return
	 */
	public static boolean hasLoopV1(SingleNode headNode) {

		if (headNode == null) {
			return false;
		}

		SingleNode p = headNode;
		SingleNode q = headNode.next;

		// 快指针未能遍历完所有节点
		while (q != null && q.next != null) {
			p = p.next; // 遍历一个节点
			q = q.next.next; // 遍历两个个节点

			// 已到链表末尾
			if (q == null) {
				return false;
			} else if (p == q) {
				// 快慢指针相遇，存在环
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		SingleNode<Integer> node1 = new SingleNode<>(1);
		SingleNode<Integer> node2 = new SingleNode<>(2);
		SingleNode<Integer> node3 = new SingleNode<>(3);
		SingleNode<Integer> node4 = new SingleNode<>(4);
//		SingleNode<Integer> node5 = new SingleNode<>(5);
//		SingleNode<Integer> node6 = new SingleNode<>(6);
//		node5.next = node6;
//		node4.next = node5;
		node3.next = node4;
		node2.next = node3;
		node1.next = node2;
		
		SingleNode<Integer> slow = node1;
		SingleNode<Integer> fast = node1;

		while(fast!=null && fast.next!=null) {
			fast = fast.next.next;
			if(fast!=null) {
				slow = slow.next;
			}
		}
		System.out.println(slow.data);
	}
}
