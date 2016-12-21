package datastucture.tree;

import java.io.File;

/**
 * 列出一个目录下所有的文件文件和他们的大小
 *
 * @author Administrator
 * @since 2016-12-21 20:46
 */

public class FileSystemPrint {

    public void print(File file) {
        // 打印文件名
        System.out.print(file.getName());
        if (file.isFile()) {
            // 如果是标准文件，打印其大小
            System.out.print("    ");
            System.out.println(file.length());
        } else if (file.isDirectory()) {
            // 如果是目录文件，打印换行，然后打印目录下的文件
            System.out.println();
            String[] fileNameList = file.list();
            for (int i = 0; i < fileNameList.length; i++) {
                print(new File(file.getPath() + File.separator + fileNameList[i]));
            }
        } else {
            System.out.println("可疑文件！");
        }
    }

}
