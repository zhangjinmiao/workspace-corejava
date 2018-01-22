Java实现单链表反转

参考：http://blog.csdn.net/guyuealian/article/details/51119499

## 单链表结点结构
![](http://img.blog.csdn.net/20160411102601848?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

- data域：存储数据元素信息的域称为数据域；
- next域：存储直接后继位置的域称为指针域，它是存放结点的直接后继的地址（位置）的指针域（链域）。
- data域+ next域：组成数据ai的存储映射，称为结点；

注意：
    
    1. 链表通过每个结点的链域将线性表的n个结点按其逻辑顺序链接在一起的。
    2. 每个结点只有一个链域的链表称为单链表（Single Linked List）。
     所谓的链表就好像火车车厢一样，从火车头开始，每一节车厢之后都连着后一节车厢。
     
要实现单链表存储，首先是创建一结点类，见 `Node.java`


## 实现反转
###1. 递归反转
在反转当前节点之前先反转后续节点。这样从头结点开始，层层深入直到尾结点才开始反转指针域的指向。

简单的说就是从尾结点开始，逆向反转各个结点的指针域指向
![](http://img.blog.csdn.net/20160411103016811?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

- head：是前一结点的指针域（PS：前一结点的指针域指向当前结点）
- head.getNext()：是当前结点的指针域（PS：当前结点的指针域指向下一结点）
- reverseHead：是反转后新链表的头结点（即原来单链表的尾结点）


###2. 遍历反转
基本思路是：将当前节点cur的下一个节点 cur.getNext()缓存到temp后，然后更改当前节点指针指向上一结点pre。也就是说在反转当前结点指针指向前，先把当前结点的指针域用tmp临时保存，以便下一次使用

从前往后反转各个结点的指针域的指向。

![](http://img.blog.csdn.net/20160411103425114?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

- pre：上一结点
- cur: 当前结点
- tmp: 临时结点，用于保存当前结点的指针域（即下一结点）