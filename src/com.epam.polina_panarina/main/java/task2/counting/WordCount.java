package task2.counting;

import task2.filereasder.MyFileReader;
import task2.threads.ThreadSearching;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {

    public int wordQuantity = 0;
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

    public synchronized void wordCountOneWord(ArrayList<String> someString, String word) throws IOException {
        //wordQuantity = 0;
        for (String it : someString
                ) {
            Pattern p = Pattern.compile(word, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(it);
            while (m.find()) {
                wordQuantity = wordQuantity + 1;
            }
            ;
        }
    }

    public double threadsQuantity(String path) throws IOException {
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
            thrdQuantity = i == 0 ? 1 : i - 1;
        } else {
            thrdQuantity = (i - 1) <= 0 ? 1 : i;
        }
        return Math.pow(thrdQuantity, 2);
    }


    public void count(String path, String word, double threads) throws IOException {
        ArrayList<String> allLines = MyFileReader.linesFromFile2(path);
        int threadsQuantity = (int) threadsQuantity(path);
        int[] threadsNumbers = arrayOfThreadsQuantity(threadsQuantity);
        HashMap<String, Integer> threadNumbersRange = hashMapOfThreadsAndNumbers(allLines, threadsNumbers);

        for (int i = 1; i <= threadsQuantity; i++) {
            int c = wordQuantity;
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
        //return wordQuantity;
    }


    public int[] arrayOfThreadsQuantity(int threadsQuantity) {
        int[] threadsNumbers = new int[threadsQuantity];
        for (int k = 0; k < threadsQuantity; k++) {
            threadsNumbers[k] = k + 1;
        }
        return threadsNumbers;
    }

    public HashMap<String, Integer> hashMapOfThreadsAndNumbers(ArrayList<String> array, int[] threads) {
        HashMap<String, Integer> threadNumbersRange = new HashMap<String, Integer>();
        for (int thr = 0; thr < threads.length; thr++) {
            for (int num = thr; num < array.size(); num = num + threads.length) {
                threadNumbersRange.put(array.get(num), threads[thr]);
            }
        }
        return threadNumbersRange;
    }

}
