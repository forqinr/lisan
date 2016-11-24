package datastucture.list;

public class LinkedListLazyDeletionTest {

    @org.junit.Test
    public void testSize() throws Exception {
        LinkedListLazyDeletion<Integer> list = new LinkedListLazyDeletion<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.print(true);

        list.add(10);
        list.add(-1, 0);
        list.print(true);

        list.add(99, 5);
        list.print();
        System.out.println("**************remove test **************");
        for (int i = list.size() - 1; i > -1; i--) {
            if (i % 2 == 1) {
                list.remove(i);
            }
            //            System.out.println("size : " + list.size());
        }
        list.print();
        list.print(false);

        list.add(999, 1);
        list.print();

        System.out.println("-------------set test -------------");
        list.print(false);

        list.set(100, 3);
        list.print(false);
        list.print();
    }
}