package task2.counting;

import org.junit.Assert;
import org.junit.Test;
import task2.filereasder.MyFileReader;

import static org.junit.Assert.*;


public class WordCountTest {



    WordCount wordCount = new WordCount();
    String FILE_PATH = "src/com.epam.polina_panarina/test/myFile.txt";

    @Test
    public void wordCount() throws Exception {
        wordCount.wordCount(FILE_PATH, "hello");
        Assert.assertEquals(2, wordCount.wordCount(FILE_PATH, "hello"));
    }

    @Test
    public void threadsQuantity() throws Exception {
        wordCount.threadsQuantity(FILE_PATH);
        Assert.assertEquals(2, wordCount.threadsQuantity(FILE_PATH));
    }


//    @Test
//    public void wordCountOneWord() throws Exception {
//        wordCount.wordCountOneWord("Jioi hello fff hello hello", "hello");
//        Assert.assertEquals(3, wordCount.wordCountOneWord("Jioi hello fff hello hello","hello"));
//    }

    @Test
    public void count() throws Exception {
        double threadsQ = wordCount.threadsQuantity(FILE_PATH);
        int c = wordCount.count(FILE_PATH, "hello", threadsQ);

        Assert.assertTrue(c==2);
    }
}