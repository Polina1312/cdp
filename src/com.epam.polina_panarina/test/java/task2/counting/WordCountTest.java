package task2.counting;

import org.junit.Assert;
import org.junit.Test;
import task2.filereasder.MyFileReader;

import java.util.ArrayList;

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
        double a = wordCount.threadsQuantity(FILE_PATH);
        Assert.assertTrue(4.0 == a);
    }


    @Test
    public void wordCountOneWord() throws Exception {
        ArrayList<String> s = new ArrayList<String>();
        s.add("Jioi hello fff hello hello");
        s.add("hello");
        wordCount.wordCountOneWord(s, "hello");
        Assert.assertEquals(4, wordCount.wordQuantity);
    }

    @Test
    public void count() throws Exception {
       wordCount.count(FILE_PATH, "hello", 2);

        Assert.assertTrue(wordCount.wordQuantity==2);
    }
}