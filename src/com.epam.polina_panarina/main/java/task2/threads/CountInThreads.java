package task2.threads;


import task2.counting.WordCount;
import task2.filereasder.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountInThreads {
    WordCount wordCount = new WordCount();

    public int count(String path, String word, double threads) throws IOException {
        ArrayList<String> allLines = MyFileReader.linesFromFile2(path);
        int threadsQuantity = (int) wordCount.threadsQuantity(path);
        int[] threadsNumbers = wordCount.arrayOfThreadsQuantity(threadsQuantity);
        HashMap<String, Integer> threadNumbersRange = wordCount.hashMapOfThreadsAndNumbers(allLines, threadsNumbers);

        for (int i = 1; i <= threadsQuantity; i++) {
            ArrayList<String> numberFromArray = new ArrayList<String>();
            for (Map.Entry entry : threadNumbersRange.entrySet()) {
                if (i == (Integer) entry.getValue()) {
                    numberFromArray.add(entry.getKey().toString());
                }
            }
            ThreadSearching threadsSearching = new ThreadSearching(numberFromArray, word);
            threadsSearching.start();
            try {
                threadsSearching.join();
            } catch (InterruptedException e) {
                System.out.println("Thread execution error");
            }
        }
        return wordCount.wordQuantity;
    }
}
