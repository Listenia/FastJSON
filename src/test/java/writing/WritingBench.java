package writing;

import fun.listenia.fastjson.WriterJSON;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WritingBench {


    public static void main(String[] args) {

        int iters = 10_000_000;

        Map<String, Object> map = new HashMap<>();
        Map last = map;

        for (int i = 0; i < 10; i++) {
            Map current = new HashMap();
            current.put(i + "", i + "");

            last.put("sub", current);
            last = current;
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            legacy(map);
        }
        long end = System.currentTimeMillis();
        System.out.println("legacy: " + (end-start));



        long start2 = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            evol(map);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Evol: " + (end2-start2));

        double diff = ((double) end-start) / (end2-start2);
        System.out.println("Diff: " + diff);

    }

    public static String evol (Map<String, Object> map) {
        return WriterJSON.writeObject(map);
    }

    public static String legacy (Map<String, Object> map) {
        return new JSONObject(map).toString();
    }


}
