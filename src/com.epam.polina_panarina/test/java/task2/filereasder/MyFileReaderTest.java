package task2.filereasder;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class MyFileReaderTest {
    MyFileReader myFileReader = new MyFileReader();
    String FILE_PATH = "src/com.epam.polina_panarina/test/myFile.txt";

    @Test
    public void linesFromFile() throws Exception {
        //MyFileReader myFileReader = new MyFileReader();
        List<String> l1 = myFileReader.linesFromFile("src/com.epam.polina_panarina/test/myFile.txt");
        List<String> l2 = myFileReader.linesFromFile2("src/com.epam.polina_panarina/test/myFile.txt");

        Assert.assertEquals(l1, l2);
    }

}