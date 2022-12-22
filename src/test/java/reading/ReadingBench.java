package reading;

import fun.listenia.fastjson.ReaderJSON;
import org.json.JSONObject;
import writing.WritingTest;

import java.util.Map;

public class ReadingBench {

    public static void main(String[] args) throws InterruptedException {

        String json = WritingTest.evol();

        Thread.sleep(1_000);
        for (int i = 0; i < 100_000; i++) {
            legacy(json);
            evol(json);
        }

        int iters = 1_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            legacy(json);
        }
        long end = System.currentTimeMillis();
        System.out.println("legacy: " + (end-start));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            evol(json);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Evol: " + (end2-start2));


        System.out.println("--");

        double diffEvol = ((double) end-start) / (end2-start2);
        System.out.println("Diff: " + diffEvol);


        System.out.println("--");
        System.out.println("Legacy: " + legacy(json));
        System.out.println("Evol: " + evol(json));


    }

    public static Map<String, Object> evol (String json) {
        return ReaderJSON.readRawObject(json);
    }

    public static Map<String, Object> legacy (String json) {
        return new JSONObject(json).toMap();
    }

}
