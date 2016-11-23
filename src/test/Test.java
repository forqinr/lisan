package test;

import datastucture.list.SingleLinkedSortedList;

/**
 * test
 *
 * @author Administrator
 * @since 2016-11-22 23:05
 */

public class Test {

    public static void main(String[] args) {
        SingleLinkedSortedList<Integer> sortedList = new SingleLinkedSortedList<Integer>();

        sortedList.add(5);
        sortedList.add(1);
        sortedList.add(9);
        sortedList.add(6);
        sortedList.add(2);
        sortedList.add(4);
        sortedList.add(8);
        sortedList.add(0);

        sortedList.print();
        System.out.println(sortedList.size());

        sortedList.remove(8);

        System.out.println(sortedList.size());

        sortedList.print();

        sortedList.add(3);
        System.out.println(sortedList.size());

        sortedList.print();

        sortedList.add(-1);
        System.out.println(sortedList.size());

        sortedList.print();

        sortedList.add(99);
        System.out.println(sortedList.size());

        sortedList.print();
    }


}
