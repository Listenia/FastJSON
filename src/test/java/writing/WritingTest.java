package writing;

import fun.listenia.fastjson.WriterJSON;
import fun.listenia.fastjson.extra.serialize.ArraySerializer;
import fun.listenia.fastjson.extra.serialize.ObjectSerializer;
import fun.listenia.fastjson.extra.serialize.Serializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WritingTest {

    public static void main(String[] args) throws InterruptedException {
        int iters = 1_000_000;

        Thread.sleep(1_000);
        for (int i = 0; i < 100_000; i++) {
            legacy();
            evol();
        }

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
        System.out.println("Evol hard: " + (end2-start2));

        System.out.println("--");

        double diff = ((double) end-start) / (end2-start2);
        System.out.println("Diff: " + diff);

        double diff2 = ((double) end-start) / (end3-start3);
        System.out.println("Diff hard: " + diff2);

        System.out.println("--");

        System.out.println("Legacy: " + legacy());
        System.out.println("Evol: " + evol());
        System.out.println("EvolHard: " + evolHard());

    }

    public static String legacy () {
        JSONObject obj = new JSONObject();
        obj.put("str", "hello");
        obj.put("double", 0.8D);
        obj.put("boolean", true);

        JSONObject sub = new JSONObject();
        sub.put("key", "value");
        obj.put("sub", sub);

        JSONArray array = new JSONArray();
        array.put(15);
        array.put(0.1D);
        array.put("Test");
        array.put("\\\"Test");
        obj.put("array", array);

        return obj.toString();
    }

    public static String evol () {
        Map<String, Object> sub = new HashMap<>();
        sub.put("key", "value");

        List<Object> list = new ArrayList<>();
        list.add(15);
        list.add(0.1D);
        list.add("Test");
        list.add("\\\"Test");

        Map<String, Object> map = new HashMap<>();
        map.put("str", "hello");
        map.put("double", 0.8D);
        map.put("boolean", true);
        map.put("obj", sub);
        map.put("array", list);

        return WriterJSON.writeObject(map);
    }

    public static String evolHard () {
        ObjectSerializer ser = Serializer.createObject();
        ser.put("str", "hello");
        ser.put("double", 0.8D);
        ser.put("boolean", true);

        ObjectSerializer sub = Serializer.createObject();
        sub.put("key", "value");
        ser.put("obj", sub);

        ArraySerializer array = Serializer.createArray();
        array.add(15);
        array.add(0.1D);
        array.add("Test");
        array.add("\\\"Test");
        ser.put("array", array);

        return ser.toString();
    }


}
