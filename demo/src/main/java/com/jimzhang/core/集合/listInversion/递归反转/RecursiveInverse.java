package com.jimzhang.core.集合.listInversion.递归反转;

import com.jimzhang.core.集合.listInversion.Node;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 递归反转
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-02 13:59
 */
public class RecursiveInverse {

    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getData() + " ");
            h = h.getNext();
        }
        // 调用反转方法
        head = Reverse(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
    }

    /**
     * 递归，在反转当前节点之前先反转后续节点
     */
    public static Node Reverse(Node head) {
        // head看作是前一结点，head.getNext()是当前结点，reverseHead 是反转后新链表的头结点
        if (head == null || head.getNext() == null) {
            return head;// 若为空链或者当前结点在尾结点，则直接还回
        }
        Node reverseHead = Reverse(head.getNext());// 先反转后续节点head.getNext()
        head.getNext().setNext(head);// 将当前结点的指针域指向前一结点
        head.setNext(null);// 前一结点的指针域令为null;
        return reverseHead;// 反转后新链表的头结点
    }
}
