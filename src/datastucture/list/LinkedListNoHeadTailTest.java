package datastucture.list;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LinkedListNoHeadTailTest {

    @org.junit.Test
    public void testAdd() throws Exception {
        //                ArrayList<Integer> list = new ArrayList<Integer>();
        //                for (int i = 0; i < 5; i++) {
        //                    list.add(i);
        //                }
        //
        //                System.out.println(list.get(list.size()));
        //
        //                System.out.println(list.toString());
        //
        //                list.add(list.size()+1, 5);
        //                System.out.println(list.toString());

        LinkedListNoHeadTail<Integer> list1 = new LinkedListNoHeadTail<Integer>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        //        System.out.println(list1.toString());
        //        list1.add(11,10);
        //        System.out.println(list1.toString());
        //        list1.add(-1, 0);
        //
        //        System.out.println(list1.toString());
        //
        //
        list1.remove(list1.size() - 1);
        System.out.println(list1.toString());
        list1.remove(0);
        System.out.println(list1.toString());
        list1.remove(3);
        System.out.println(list1.toString());

    }
}