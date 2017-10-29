package task2.counting;

import task2.filereasder.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {


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

}
