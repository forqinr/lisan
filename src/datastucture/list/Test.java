package datastucture.list;

import sort.InertionSort;

import java.util.Iterator;
import java.util.Queue;

/**
 * ceshi
 *
 * @author Administrator
 * @since 2016-11-19 11:57
 */

public class Test {

    //    public static void main(String[] args) {
    //        MyArrayList<Integer> lst = new MyArrayList<Integer>();
    //        for (int i = 0; i < 100; i++) {
    //            lst.add(i);
    //        }
    //
    //        for (int i = 0; i < lst.size(); i++) {
    //            System.out.printf(lst.get(i) + ",");
    //            if (i % 20 == 0) {
    //                System.out.println();
    //            }
    //        }
    //        System.out.println();
    //        System.out.println("--------------------------------");
    //
    //        Iterator<Integer> iterator=lst.iterator();
    //        while (iterator.hasNext()){
    //            System.out.printf(iterator.next() + ",");
    //        }
    //        System.out.println();
    //        System.out.println("#####################################");
    //        Iterator<Integer> iterator2=lst.iterator();
    //        while (iterator2.hasNext()){
    //            if (iterator2.next()%2==0)
    //                iterator2.remove();
    //        }
    //
    //        Iterator<Integer> iterator3=lst.iterator();
    //        while (iterator3.hasNext()){
    //            System.out.printf(iterator3.next() + ",");
    //        }
    //    }

    // 测试计算器类
    //    public static void main(String[] args) {
    //        MyArrayStack<Integer> stack = new MyArrayStack<Integer>();
    //        for (int i = 0; i < 20; i++) {
    //            stack.push(i);
    //        }
    //
    //        for (int i = 0; i < 21; i++) {
    //            System.out.println(stack.pop());
    //        }
    //    }

    //    public static void main(String[] args) {
    //        Calculator calculator = new Calculator();
    //        String suanshi = "1+2*3/(5+1)+100/25+2^3";
    //        System.out.println(calculator.caculate(suanshi));
    //    }

    // 测试队列
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyArrayQueue<Integer>();
        for (int i = 0; i < 8; i++) {
            System.out.println("i = " + i);
            queue.enqueue(i);
        }

        System.out.println("---------------------------------------");

        System.out.println(queue.dequeue());
        queue.dequeue();

        queue.enqueue(9);
        queue.enqueue(19);
        queue.enqueue(119);
        queue.enqueue(129);
//        queue.enqueue(139);

        System.out.println("######################");
        System.out.println("2.3.4.5.6.7.9.19.");

        while (queue.peek() != null) {
            System.out.printf(queue.dequeue() + ",");
        }
    }
}
