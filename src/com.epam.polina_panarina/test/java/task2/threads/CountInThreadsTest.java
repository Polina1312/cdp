package task2.threads;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class CountInThreadsTest {
    CountInThreads countInThreads = new CountInThreads();
    String FILE_PATH = "src/com.epam.polina_panarina/test/myFile.txt";

    @Test
    public void count() throws Exception {
        int c = countInThreads.count(FILE_PATH, "hello", 2);

        Assert.assertTrue(c==2);
    }

}