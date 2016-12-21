package datastucture.tree;

import java.io.File;

/**
 * 书上提供的打印文件程序
 *
 * @author Administrator
 * @since 2016-12-21 21:35
 */

public class FileList {
    public void list(File f) {
        list(f, 0);
    }

    public void list(File f, int depth) {
        printName(f, depth);
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File i : files)
                list(i, depth + 1);
        }
    }

    void printName(File f, int depth) {
        String name = f.getName();
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        if (f.isDirectory()) {
            System.out.println("Dir: " + name);
        } else {
            System.out.println(f.getName() + "^" + f.length());
        }
    }

    public static void main(String args[]) {
        FileList L = new FileList();
        File f = new File("E:\\book");
        L.list(f);
    }
}
