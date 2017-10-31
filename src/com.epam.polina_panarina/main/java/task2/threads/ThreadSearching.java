package task2.threads;

import task2.counting.WordCount;

import java.io.IOException;
import java.util.ArrayList;


public class ThreadSearching extends Thread {
    WordCount wordCount = new WordCount();
    protected ArrayList<String> someString;
    protected String word;

    public ThreadSearching( ArrayList<String>  someString, String word) {
        this.someString = someString;
        this.word = word;
    }

    public void run() {
        try {
            Thread.sleep(2);
            wordCount.wordCountOneWord(someString, word);
        } catch (InterruptedException e) {
            e.getMessage();
            System.out.println("Thread was interrupted");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
