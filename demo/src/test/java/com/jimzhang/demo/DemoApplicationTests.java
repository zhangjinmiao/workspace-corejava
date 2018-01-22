package com.jimzhang.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 插入400万次，add(obj)：ArrayList 耗时50ms，LinkedList 耗时280ms, ArrayList 效率高
	 * 插入 5 万次，add(0,obj)，ArrayList 耗时 250 ms，LinkedList 耗时3 ms, LinkedList 效率高
	 */
	@Test
	public void testAdd(){
		Object obj = new Object();

		ArrayList arrayList = new ArrayList();
		long start = System.currentTimeMillis();
		System.out.println(start);
		for (int i = 0; i < 50000; i++) { // 循环500万次
//			arrayList.add(obj);
			arrayList.add(0,obj);
		}
		long end = System.currentTimeMillis();
		System.out.println(end);
		long sub = end - start;
		System.out.println( "ArrayList耗时：" + sub);

		LinkedList linkedList = new LinkedList();
		long start1 = System.currentTimeMillis();
		System.out.println(start1);
		for (int i =0 ; i < 50000; i++) {
//			linkedList.add(obj);
			linkedList.add(0,obj);
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1);
		long sub1 = end1 - start1;
		System.out.println("LinkedList耗时：" + sub1);

	}


	/**
	 * 				头部		中间		尾部
	 * ArrayList	994ms		509ms		3ms
	 * LinkedList	4ms			3347ms		3ms
	 */
	@Test
	public void testRemove(){
		Object obj = new Object();

		ArrayList arrayList = new ArrayList();
		LinkedList linkedList = new LinkedList();
		for (int i = 0; i < 100000; i++) { // 循环10万次
			arrayList.add(obj);
			linkedList.add(obj);
		}

		long start = System.currentTimeMillis();
		while (arrayList.size() > 0) {
//			arrayList.remove(0); // 从头开始删除
//			arrayList.remove(arrayList.size() >> 1); // 从中间开始删除
			arrayList.remove(arrayList.size() -1); // 从尾部开始删除
		}
		long end = System.currentTimeMillis();
		long sub = end - start;
		System.out.println("ArrayList耗时：" + sub);


		long start1 = System.currentTimeMillis();
		while (linkedList.size() > 0) {
//			linkedList.remove(0); // 从头开始删除
//			linkedList.remove(linkedList.size() >> 1); // 从中间开始删除
			linkedList.remove(linkedList.size() -1); // 从尾部开始删除
		}
		long end1 = System.currentTimeMillis();
		long sub1 = end1 - start1;
		System.out.println("LinkedList耗时：" + sub1);
	}

	/**
	 * 		不指定初始容量		指定
	 * 		77ms				18ms
	 */
	@Test
	public void testCapacity(){
		Object obj = new Object();

		ArrayList arrayList = new ArrayList();
//		ArrayList arrayList = new ArrayList(5000000);
		long start = System.currentTimeMillis();
		for (int i=0;i < 1000000;i++){ // 500万
			arrayList.add(obj);
		}
		long end = System.currentTimeMillis();
		long sub = end - start;
		System.out.println("ArrayList耗时：" + sub);
	}
}
