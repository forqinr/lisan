package datastucture.list;

import org.junit.*;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @org.junit.Test
    public void testReverseIterator() throws Exception {
        MyArrayList<Integer> list = new MyArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.print();

        Iterator<Integer> iterator = list.reverseIterator();
        while (iterator.hasNext()) {
            if (iterator.next()==3) {
                iterator.remove();
            }
        }

        list.print();

        System.out.println(list.size());
    }

    @Test
    public void testModCount() throws Exception {
        MyArrayList<Integer> list = new MyArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.print();
        
        Iterator<Integer> iterator=list.iterator();
        if (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        iterator.remove();

        iterator.remove();

    }
}