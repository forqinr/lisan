package datastucture.list;

import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MyLinkedListTest {

    @org.junit.Test
    public void testSplice() throws Exception {
        MyLinkedList<Integer> thisList = new MyLinkedList<Integer>();
        for (int i = 0; i < 8; i++) {
            thisList.add(i);
        }

        MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
        lst.add(55);
        lst.add(66);
        lst.add(77);
        lst.add(88);

        Iterator<Integer> iterator = thisList.iterator();
//        while (iterator.hasNext())
            iterator.next();
        //        iterator.next();
        //        System.out.println(iterator.next());

        thisList.splice(iterator, lst);
        for (int i = 0; i < thisList.size(); i++) {
            System.out.printf(" " + thisList.get(i));
        }

        System.out.println();

        System.out.println("lst's size = " + lst.size());
    }
}