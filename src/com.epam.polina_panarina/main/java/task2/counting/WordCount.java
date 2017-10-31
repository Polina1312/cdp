package task2.counting;

import task2.filereasder.MyFileReader;
import task2.threads.ThreadSearching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {

    protected int wordQuantity;
    final Random random = new Random();

    public int wordCount(String path, String word) throws IOException {

        List<String> allLines = MyFileReader.linesFromFile2(path);
        int count = 0;
        Pattern p = Pattern.compile(word, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        for (String it : allLines
                ) {
            Matcher m = p.matcher(it);
            while (m.find()) count++;
        }

        return count;
    }

    public synchronized void wordCountOneWord(String someString, String word) throws IOException {
        wordQuantity = 0;
        Pattern p = Pattern.compile(word, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(someString);
        while (m.find()) wordQuantity++;
    }

    double threadsQuantity(String path) throws IOException {
        int collectionSize = MyFileReader.linesFromFile2(path).size();
        int thrdQuantity = 0;
        int i = 0;
        double tempValuePlusOne = collectionSize - Math.pow(10, i);
        double tempValue = 0;
        while (tempValuePlusOne > 0) {
            tempValue = collectionSize - Math.pow(10, i);
            tempValuePlusOne = collectionSize - Math.pow(10, i + 1);
            i++;
        }
        if (Math.abs(tempValuePlusOne) < tempValue) {
            thrdQuantity = i == 0 ? 1 : i;
        } else {
            thrdQuantity = (i - 1) <= 0 ? 1 : i - 1;
        }
        return Math.pow(thrdQuantity, 2);
    }


    public int count(String path, String word, double threads) throws IOException {
        List<String> allLines = MyFileReader.linesFromFile2(path);
        List<ThreadSearching> threadSearchingList = new ArrayList<ThreadSearching>();
        int threadsQ = (int) threads;
        for (String it : allLines) {
            if (threadSearchingList.size() == threadsQ) {
               ThreadSearching thsearch =  threadSearchingList.get(random.nextInt(threadsQ));

                   thsearch.notify();
                   try {
                       thsearch.wait();
                   } catch (InterruptedException e) {
                       System.out.println("Thread execution error");
                   }

            }
            ThreadSearching threadsSearching = new ThreadSearching(it, word);

            threadSearchingList.add(threadsSearching);
            threadsSearching.start();
            try {
                threadsSearching.wait();

            } catch (InterruptedException e) {
                System.out.println("Thread execution error");
            }
        }
        return wordQuantity;
    }
}
