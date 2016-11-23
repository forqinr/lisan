package test;

import java.util.ArrayList;
import java.util.ListIterator;

import datastucture.list.MyLinkedList;

/**
 * test
 *
 * @author Administrator
 * @since 2016-11-22 23:05
 */

public class Test {

    public static void main(String[] args) {
        // 测试链表的listIterator
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }

        System.out.println(lst.size());

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i));
        }

        System.out.println("--------------------------------------------");

        ListIterator<Integer> itr = lst.listIterator();
        //for (int i = -5; i < 0; i++) {
            itr.next();
            itr.add(99);
            itr.next();
        //}

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i));
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        ArrayList<Integer> listJdk = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            listJdk.add(i);
        }

        ListIterator<Integer> iterator=listJdk.listIterator();
        iterator.add(99);
        while (iterator.hasNext()){
            iterator.next();
        }
        iterator.add(900);
        for (int i = 0; i < listJdk.size(); i++) {
            System.out.println(listJdk.get(i));
        }

        //while (itr.hasNext()) {
        //    System.out.println(itr.next());
        //}
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        //System.out.println(lst.size());
        //while (itr.hasPrevious()) {
        //    System.out.println(itr.previous());
        //}
        //System.out.println("###############################################");
        //for (int i = 0; i < 5; i++) {
        //    System.out.println(itr.next());
        //}
        //itr.set(99);
        ////System.out.println(itr.next());
        //for (int i = 0; i < lst.size(); i++) {
        //    System.out.println(lst.get(i));
        //}
        //
        //System.out.println("++++++++++++++++++++++++++++++++++");
        //while (itr.hasNext()){
        //    itr.next();
        //}
        //
        //for (int i = 0; i < 3; i++) {
        //    System.out.println(itr.previous());
        //}
        //itr.set(456);
        //for (int i = 0; i < lst.size(); i++) {
        //    System.out.println(lst.get(i));
        //}


        //// 测试实现了ListIterator的MyArrayList类
        //MyArrayList<Integer> arrayList = new MyArrayList<Integer>();
        //for (int i = 0; i < 10; i++) {
        //    arrayList.add(i);
        //}
        //
        //ListIterator<Integer> listIterator = arrayList.listIterator();
        //
        //int i = 0;
        //
        //while (listIterator.hasNext() && i < 5) {
        //    listIterator.next();
        //    i++;
        //}
        //
        //System.out.println("listIterator.next() = " + listIterator.next());
        //System.out.println("listIterator.previous() = " + listIterator.previous());

        //System.out.println();
        //
        //System.out.println("##################################");
        //
        //while (listIterator.hasPrevious()) {
        //    listIterator.previous();
        //}


        // previrous
        //listIterator.remove();

        // next
        //listIterator.next();
        //listIterator.remove();


        //System.out.println("******************************************");
        //listIterator.add(-1);
        //System.out.println(listIterator.next());
        //
        //System.out.println(listIterator.previous());
        //listIterator.add(-2);
        //
        //System.out.println(listIterator.next());
        //
        //while (listIterator.hasNext()) {
        //    listIterator.next();
        //}
        //
        //listIterator.add(99);
        //listIterator.add(98);
        //listIterator.add(97);
        //
        //while (listIterator.hasNext()) {
        //    listIterator.next();
        //}
        //
        //System.out.println();
        //
        //while (listIterator.hasPrevious()) {
        //    System.out.printf(listIterator.previous() + " ");
        //}

        // 测试排序的单链表
        //SingleLinkedSortedList<Integer> sortedList = new SingleLinkedSortedList<Integer>();
        //
        //sortedList.add(5);
        //sortedList.add(1);
        //sortedList.add(9);
        //sortedList.add(6);
        //sortedList.add(2);
        //sortedList.add(4);
        //sortedList.add(8);
        //sortedList.add(0);
        //
        //sortedList.print();
        //System.out.println(sortedList.size());
        //
        //sortedList.remove(8);
        //
        //System.out.println(sortedList.size());
        //
        //sortedList.print();
        //
        //sortedList.add(3);
        //System.out.println(sortedList.size());
        //
        //sortedList.print();
        //
        //sortedList.add(-1);
        //System.out.println(sortedList.size());
        //
        //sortedList.print();
        //
        //sortedList.add(99);
        //System.out.println(sortedList.size());
        //
        //sortedList.print();
    }


}
