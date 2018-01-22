package com.jimzhang.core.集合.listInversion;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 结点-用于保存数据的结点
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-02 13:42
 */
public class Node {

    private int Data;// 数据域
    private Node Next;// 指针域

    public Node(int data) {
        Data = data;
    }

    public int getData() {
        return Data;
    }

    public void setData(int data) {
        Data = data;
    }

    public Node getNext() {
        return Next;
    }

    public void setNext(Node next) {
        Next = next;
    }
}
