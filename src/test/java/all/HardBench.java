package all;

import fun.listenia.fastjson.ReaderJSON;
import org.json.JSONArray;
import org.json.JSONObject;
import writing.WritingTest;

import java.util.Map;

public class HardBench {
    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1_000);
        for (int i = 0; i < 100_000; i++) {
            legacy();
            evol();
        }

        int iters = 1_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            legacy();
        }
        long end = System.currentTimeMillis();
        System.out.println("legacy: " + (end-start));



        long start2 = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            evol();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Evol: " + (end2-start2));



        long start3 = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            evol();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Evol hard: " + (end3-start3));


        System.out.println("--");

        double diffEvol = ((double) end-start) / (end2-start2);
        System.out.println("Diff: " + diffEvol);

        double diffEvolHard = ((double) end-start) / (end3-start3);
        System.out.println("Diff hard: " + diffEvolHard);

        System.out.println("--");
        System.out.println("Legacy: " + legacy());
        System.out.println("Evol: " + evol());
        System.out.println("EvolHard: " + evolHard());


    }

    public static Map<String, Object> evol () {
        String json = WritingTest.evol();
        return ReaderJSON.readRawObject(json);
    }

    public static Map<String, Object> evolHard () {
        String json = WritingTest.evolHard();
        return ReaderJSON.readRawObject(json);
    }

    public static Map<String, Object> legacy () {

        JSONObject sub = new JSONObject();
        sub.put("key", "value");

        JSONArray list = new JSONArray();
        list.put(15);
        list.put(0.1D);
        list.put("Test");
        list.put("\\\"Test");

        JSONObject obj = new JSONObject();
        obj.put("str", "hello");
        obj.put("double", 0.8D);
        obj.put("boolean", true);
        obj.put("obj", sub);
        obj.put("array", list);

        String json = obj.toString();

        return new JSONObject(json).toMap();
    }
}
