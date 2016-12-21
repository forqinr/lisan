package datastucture.tree;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;

public class FileSystemPrintTest {

    @Test
    public void testPrint() throws Exception {
        File file = new File("E:\\book");
        FileSystemPrint fp = new FileSystemPrint();
        fp.print(file);
    }
}